package vn.ute.service;

import java.util.List;
import java.util.stream.StreamSupport;

import vn.ute.dto.UserWebDto;
import vn.ute.entity.Role;
import vn.ute.entity.UserWeb;
import vn.ute.repository.RoleRepository;
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
    RoleRepository roleRepo;

	@Autowired
	PasswordEncoder pwEncoder;

    public UserWeb createUser(UserWeb user) {
        user.setPassword(pwEncoder.encode(user.getPassword()));
        return repo.save(user);
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

	public List<UserWeb> findByProperty(String property, Object value) {
		log.info("Find user by property start ");
		return userDAO.findByProperty(property, value);
}