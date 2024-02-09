package org.adaschool.proyectoReservas.application.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T14:11:47-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class IBookingMapperImpl implements IBookingMapper {

    @Override
    public Booking toEntity(BookingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.id( dto.id() );
        booking.bookingDate( dto.bookingDate() );
        booking.bookingHour( dto.bookingHour() );
        booking.description( dto.description() );
        booking.stateReservation( dto.stateReservation() );
        booking.user( userDtoToUser( dto.user() ) );

        return booking.build();
    }

    @Override
    public BookingDto toDto(Booking entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        LocalDate bookingDate = null;
        LocalTime bookingHour = null;
        String description = null;
        EStateReservation stateReservation = null;
        UserDto user = null;

        id = entity.getId();
        bookingDate = entity.getBookingDate();
        bookingHour = entity.getBookingHour();
        description = entity.getDescription();
        stateReservation = entity.getStateReservation();
        user = userToUserDto( entity.getUser() );

        BookingDto bookingDto = new BookingDto( id, bookingDate, bookingHour, description, stateReservation, user );

        return bookingDto;
    }

    @Override
    public List<Booking> toEntityList(List<BookingDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Booking> list = new ArrayList<Booking>( dtoList.size() );
        for ( BookingDto bookingDto : dtoList ) {
            list.add( toEntity( bookingDto ) );
        }

        return list;
    }

    @Override
    public List<BookingDto> toDtoList(List<Booking> entityBooking) {
        if ( entityBooking == null ) {
            return null;
        }

        List<BookingDto> list = new ArrayList<BookingDto>( entityBooking.size() );
        for ( Booking booking : entityBooking ) {
            list.add( toDto( booking ) );
        }

        return list;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.id() );
        user.name( userDto.name() );
        user.lastName( userDto.lastName() );
        user.email( userDto.email() );
        user.password( userDto.password() );
        user.enable( userDto.enable() );
        user.document( userDto.document() );
        user.roles( userDto.roles() );

        return user.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean enable = false;
        String document = null;
        ERoles roles = null;

        id = user.getId();
        name = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        enable = user.isEnable();
        document = user.getDocument();
        roles = user.getRoles();

        UserDto userDto = new UserDto( id, name, lastName, email, password, enable, document, roles );

        return userDto;
    }
}
