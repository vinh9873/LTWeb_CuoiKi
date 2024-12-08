package vn.ute.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;
import vn.ute.dto.UserWebDto;
import vn.ute.entity.Role;
import vn.ute.entity.UserWeb;
import vn.ute.repository.RoleRepository;
import vn.ute.repository.UserWebRepository;
import vn.ute.util.SecCtxHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserWebService {

    @Autowired
    UserWebRepository repo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder pwEncoder;

    @Autowired
    JavaMailSender mailSender;

    @Value("${server.port:8083}")
    int serverPort;

    public UserWeb createUser(UserWeb user) {
        user.setPassword(pwEncoder.encode(user.getPassword()));
        user.setIsActive(false);
        var code = UUID.randomUUID().toString();
        user.setCodeVerify(code);
        var newUser = repo.save(user);
        sendVerification(newUser.getEmailAddress(), code);
        return newUser;
    }

    // Do not update password on this method because that would
    // cause the new password to be re-encoded, use old password
    // instead
    public UserWebDto updateUser(UserWeb user) {
        var entity = repo.findById(user.getId()).orElseThrow();
        user.setPassword(entity.getPassword());
        var savedUser = repo.save(user);
        return UserWebDto.fromEntity(savedUser);
    }

    public UserWebDto findUserById(Integer id) {
        var user = repo.findById(id).orElseThrow();
        return UserWebDto.fromEntity(user);
    }

    public void deleteUserById(int id) {
        repo.deleteById(id);
    }

    public List<UserWeb> findAll(String search) {
        if (search == null || search.isBlank()) {
            var iterable = repo.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).toList();
        }
        return repo.findByNameContainingOrEmailAddressContaining(search, search);
    }

    public List<UserWeb> findAll() {
        var iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public UserWebDto findCurrentUser() {
        var id = SecCtxHolderUtils.getUserId();
        return findUserById(id);
    }

    public List<Role> findAllUserRoles() {
        var roles = roleRepo.findAll();
        return StreamSupport.stream(roles.spliterator(), false).toList();
    }

    public Role findRole(Integer id) {
        return roleRepo.findById(id).orElseThrow();
    }

    private void sendVerification(String email, String code) {
        var baseUrl = "http://%s:%d".formatted("localhost", serverPort);
        var msg = mailSender.createMimeMessage();
        var link = "<h3><a href=\"%s/verify?code=%s\" target=\"_self\">VERIFY</a></h3>"
                .formatted(baseUrl, code);
        try {
            msg.setFrom("shop@email.com");
            msg.setSubject("Confirm your registration");
            msg.setRecipients(MimeMessage.RecipientType.TO, email);
            msg.setContent("Please use this link to verify your registration: " + link, "text/html; charset=utf-8");
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return;
        }
        this.mailSender.send(msg);
    }

    public void verifyUser(String code) {
        var user = repo.findByCodeVerify(code);
        if (user == null) {
            throw new RuntimeException("Could not find verification code.");
        }
        user.setIsActive(true);
        user.setCodeVerify(null);
        repo.save(user);
    }
}