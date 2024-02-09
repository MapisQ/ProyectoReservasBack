package org.adaschool.proyectoReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;
import org.adaschool.proyectoReservas.application.mapper.IBookingMapper;
import org.adaschool.proyectoReservas.application.service.BookingService;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.adaschool.proyectoReservas.domain.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookingServiceTests {

    @MockBean
    private BookingRepository bookingRepository;
    @MockBean
    private IBookingMapper mapperB;
    @Autowired
    private BookingService bookingService;

    private Booking booking;
    private User user;
    private BookingDto bookingDto;
    private  UserDto userDto;

    @BeforeEach
    void setUp(){

        user = User.builder()
                .id(1)
                .name("Maria")
                .lastName("Quito")
                .email("mapis2321@gmail.com")
                .password("mapis2321")
                .enable(true)
                .document("1488523")
                .roles(ERoles.Client)
                .build();

        booking = Booking.builder()
                .id(1)
                .bookingDate(LocalDate.of(2023, 5, 10))
                .bookingHour(LocalTime.of(14, 30))
                .description("para un cumpleaños")
                .stateReservation(EStateReservation.Active)
                .user(user).build();

        userDto = new UserDto
                (1,"Maria","Quito","mapis2321@gmail.com",
                        "mapis2321",true,"1488523",ERoles.Client);
        bookingDto = new BookingDto
                (1,LocalDate.of(2023, 5, 10),
                        LocalTime.of(14, 30),
                        "para un cumpleaños",EStateReservation.Active,userDto);
    }

    @Test
    void saveBooking() {

        when(mapperB.toEntity(bookingDto)).thenReturn(booking);
        bookingService.createBooking(bookingDto);

        assertNotNull(booking);
        verify(bookingRepository).save(booking);
    }

    @Test
    void findBookingById() {
        final Integer id = 1;
        when(mapperB.toDto(booking)).thenReturn(bookingDto);

        try {
            BookingDto result = bookingService.findBookingById(id);

            assertNotNull(result);
            assertNotNull(bookingDto);
            assertEquals(bookingDto, result);
        } catch (ReservationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void notFoundBookingById(){
        final Integer id = 1;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ReservationException.class,()-> bookingService.findBookingById(id));
    }

    @Test
    void allBookings(){
        final Integer offset = 0;
        final Integer limit = 50;
        List<Booking> bookingList  = Arrays.asList(booking);
        List<BookingDto> expectedList = Arrays.asList(mapperB.toDto(booking));

        when(bookingRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(bookingList));
        when(mapperB.toDtoList(bookingList)).thenReturn(expectedList);

        try {
            List<BookingDto> result = bookingService.listAllBookings(offset,limit);
            assertEquals(expectedList, result);
        }catch (ReservationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateBooking(){
        final Integer id = 1;
        Booking bookingM = Booking.builder()
                .id(1)
                .bookingDate(LocalDate.of(2024, 1, 23))
                .bookingHour(LocalTime.of(18, 41))
                .description("para un aniversario")
                .stateReservation(EStateReservation.Active)
                .user(user).build();

        BookingDto bookingDtoM = new BookingDto
                (1,LocalDate.of(2024, 1, 23),
                        LocalTime.of(18, 41),
                        "para un aniversario",EStateReservation.Active,userDto);

        when(mapperB.toEntity(bookingDtoM)).thenReturn(bookingM);
        when(bookingRepository.findById(id)).thenReturn(Optional.of(bookingM));
        try{
            bookingService.updateBooking(id,bookingDtoM);
            verify(bookingRepository).save(bookingM);
        }catch (ReservationException e) {
            throw new RuntimeException(e);
        }
    }
}