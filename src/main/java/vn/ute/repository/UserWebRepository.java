package vn.ute.repository;

import java.util.List;
import vn.ute.entity.UserWeb;
import org.springframework.data.repository.CrudRepository;

public interface UserWebRepository extends CrudRepository<UserWeb, Integer> {

    UserWeb findByEmailAddress(String emailAddress);

    /**
     * select * from user_web where name like '%namePattern%' or email_address like '%emailPattern%'
     */
    List<UserWeb> findByNameContainingOrEmailAddressContaining(String namePattern, String emailPattern);

}
