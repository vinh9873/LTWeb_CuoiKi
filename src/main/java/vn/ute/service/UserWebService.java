package vn.ute.service;

import java.util.List;
import java.util.stream.StreamSupport;
import vn.ute.entity.UserWeb;
import vn.ute.repository.UserWebRepository;
import vn.ute.util.SecCtxHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserWebService {

    @Autowired
    UserWebRepository repo;

    @Autowired
    PasswordEncoder pwEncoder;

    public UserWeb createUser(UserWeb user) {
        user.setPassword(pwEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // Do not update password on this method because that would
    // cause the new password to be re-encoded, use old password
    // instead
    public UserWeb updateUser(UserWeb user) {
        var entity = repo.findById(user.getId()).orElseThrow();
        user.setPassword(entity.getPassword());
        return repo.save(user);
    }

    public List<UserWeb> findAll() {
        var iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public void deleteUserById(int id) {
        repo.deleteById(id);
    }

    public UserWeb findCurrentUser() {
        var userId = SecCtxHolderUtils.getUserId();
        return repo.findById(userId).orElseThrow();
    }
}
