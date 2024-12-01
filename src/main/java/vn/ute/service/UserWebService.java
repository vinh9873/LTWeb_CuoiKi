package vn.ute.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ute.entity.UserWeb;
import vn.ute.repository.UserWebRepository;

@Service
public class UserWebService {

    @Autowired
    UserWebRepository repo;

    public List<UserWeb> saveUsers(List<UserWeb> users) {
        var savedUsers = repo.saveAll(users);
        return StreamSupport.stream(savedUsers.spliterator(), false).toList();
    }

    public List<UserWeb> findAll() {
        var iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public void deleteUsers(List<Integer> userIds) {
        Iterable<Integer> itr = () -> userIds.iterator();
        repo.deleteAllById(itr);
    }
}
