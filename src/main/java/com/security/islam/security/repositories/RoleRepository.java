package com.security.islam.security.repositories;

import com.security.islam.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository  extends JpaRepository<Role,Long> {

    Role findOneById(Long id);
    Role findOneByName(String name);

}
