package com.syed.quizApplication.repository;

import com.syed.quizApplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*

Not required to put @Repository

JpaRepository (or any Spring Data repository) is already detected by
Spring through component scanning when you use @EnableJpaRepositories or @SpringBootApplication.
 */

@Repository
public interface UserRespositoy extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}
