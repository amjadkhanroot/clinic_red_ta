package com.amjadcode.authentication.repositories;

import com.amjadcode.commons.ERoleType;
import com.amjadcode.authentication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERoleType name);
}