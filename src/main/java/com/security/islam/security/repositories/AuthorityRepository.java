package com.security.islam.security.repositories;

import com.security.islam.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Set<Authority> findAllByIdIn(List<Long> ids);
}
