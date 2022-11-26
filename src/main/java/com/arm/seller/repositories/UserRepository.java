package com.arm.seller.repositories;

import com.arm.seller.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select U from User U where U.email=?1")
    User findUserByUsername(String username);

    @Query("select U from User U where upper(U.fname) like ?1 or upper(U.lname) like ?1")
    List<User> findUserByFnameOrLname(String name);
}
