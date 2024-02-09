package org.adaschool.proyectoReservas.application.service;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.lasting.EMessage;
import org.adaschool.proyectoReservas.application.mapper.IBookingMapper;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.adaschool.proyectoReservas.domain.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public record BookingService(BookingRepository bookingRepository, IBookingMapper mapper) {

    public void createBooking(BookingDto bookingDto) {
        Booking booking = mapper.toEntity(bookingDto);
        bookingRepository.save(booking);
    }

    public BookingDto findBookingById(Integer idBooking) throws ReservationException {
        Booking booking = bookingRepository.findById(idBooking).orElseThrow(() -> new ReservationException(EMessage.ID_NOT_FOUND));
        return mapper.toDto(booking);
    }
    public List<BookingDto> listAllBookings(Integer offset, Integer limit) throws ReservationException {
        Pageable pageable = PageRequest.of(offset,limit);
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        if (bookings.getContent().isEmpty()){
            throw new ReservationException(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(bookings.getContent());
    }

    public void updateBooking(Integer idBooking, BookingDto bookingDto) throws ReservationException {
        bookingRepository.findById(idBooking).orElseThrow(()->new ReservationException(EMessage.BOOKING_NOT_FOUND));
        Booking booking = mapper.toEntity(bookingDto);
        bookingRepository.save(booking);
    }
}
