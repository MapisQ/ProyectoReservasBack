package org.adaschool.bookingproject.domain.repository;

import org.adaschool.bookingproject.domain.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Page<Booking> findAll(Pageable pageable);
}
