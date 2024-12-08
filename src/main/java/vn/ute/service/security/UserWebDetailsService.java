package vn.ute.service.security;

import java.util.List;
import vn.ute.dto.UserRole;
import vn.ute.dto.UserWebDetails;
import vn.ute.repository.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserWebDetailsService implements UserDetailsService {

    @Autowired
    UserWebRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmailAddress(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserWebDetails(
                user.getId(), user.getName(), user.getPassword(),
                List.of(new UserRole(user.getRole())), user.getIsActive());
    }

}