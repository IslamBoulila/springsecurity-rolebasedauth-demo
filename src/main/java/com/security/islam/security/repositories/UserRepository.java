package com.security.islam.security.repositories;

import com.security.islam.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUserName(String userName);
    Optional<User> findOneById(Long id);

}
