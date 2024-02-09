package org.adaschool.proyectoReservas.domain.repository;

import org.adaschool.proyectoReservas.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    Page<User> findAll(Pageable pageable);
    Optional<User> findUserByEmail(String email);
}
