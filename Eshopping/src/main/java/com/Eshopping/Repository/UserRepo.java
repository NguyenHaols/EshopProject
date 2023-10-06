package com.Eshopping.Repository;

import com.Eshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    List<User> findByUserNameAndPassword(String usernname, String password);
    User findByUserName(String usernname);
}
