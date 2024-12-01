package vn.ute.repository;

import org.springframework.data.repository.CrudRepository;

import vn.ute.entity.UserWeb;

public interface UserWebRepository extends CrudRepository<UserWeb, Integer> {

    UserWeb findByEmailAddress(String emailAddress);

}
