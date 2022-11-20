package com.project.URL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.URL.entity.User;

public interface UserRepo extends JpaRepository<User, Long>
{

}
