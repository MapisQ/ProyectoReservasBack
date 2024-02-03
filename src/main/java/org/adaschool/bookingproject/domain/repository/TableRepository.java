package org.adaschool.bookingproject.domain.repository;

import org.adaschool.bookingproject.domain.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table,Integer> {
    Page<Table> findAll(Pageable pageable);
}
