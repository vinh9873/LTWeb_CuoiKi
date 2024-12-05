package vn.ute.service.security;

import java.util.List;
import vn.ute.dto.UserRole;
import vn.ute.dto.UsersDetails;
import vn.ute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.ute.dto.UsersDetails;
import vn.ute.repository.UserRepository;

@Service
public class UserWebDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmailAddress(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UsersDetails(user.getName(), user.getPassword(), List.of(new UserRole(user.getRole())));
    }

}
