package vn.ute.repository;

import vn.ute.entity.UserWeb;
import org.springframework.data.repository.CrudRepository;

public interface UserWebRepository extends CrudRepository<UserWeb, Integer> {

    UserWeb findByEmailAddress(String emailAddress);

}
