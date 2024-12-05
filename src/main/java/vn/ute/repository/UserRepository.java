package vn.ute.repository;

import org.springframework.data.repository.CrudRepository;

import vn.ute.entity.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

    Users findByEmailAddress(String emailAddress);

}
