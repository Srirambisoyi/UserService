package com.Mcs.user.service.UserService.repository;

import com.Mcs.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
