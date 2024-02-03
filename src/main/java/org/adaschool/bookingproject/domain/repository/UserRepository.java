package org.adaschool.bookingproject.domain.repository;

import org.adaschool.bookingproject.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    Page<User> findAll(Pageable pageable);
    Optional<User> findUserByEmail(String email);
}
