package com.diu.coop.repositories;

import com.diu.coop.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<Users, Integer>{
    Users findByUserId(int id);
    Users findByMobileNumber(String number);
}

