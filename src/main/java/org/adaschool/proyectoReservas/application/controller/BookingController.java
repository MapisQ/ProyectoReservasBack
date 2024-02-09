package org.adaschool.proyectoReservas.application.controller;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.service.BookingService;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
public record BookingController(BookingService bookingService) {

    @PostMapping("/saveBooking")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{idBooking}")
    public ResponseEntity<?> searchBookingById(@PathVariable("idBooking") Integer idBooking) throws ReservationException {
        bookingService.findBookingById(idBooking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allBookings/{offset}/{limit}")
    public ResponseEntity<?> searchBookings
            (@PathVariable Integer offset,@PathVariable Integer limit) throws ReservationException {
        List<BookingDto> bookingDtoList = bookingService.listAllBookings(offset, limit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{idBooking}")
    public ResponseEntity<?> updateBooking
            (@PathVariable("idBooking") Integer idBooking, @RequestBody BookingDto bookingDto) throws ReservationException {
        bookingService.updateBooking(idBooking,bookingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
