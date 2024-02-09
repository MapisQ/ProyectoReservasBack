package org.adaschool.proyectoReservas.application.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;
import org.adaschool.proyectoReservas.application.lasting.EStateTable;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.dto.TableDto;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.adaschool.proyectoReservas.domain.entity.Table;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T14:11:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ITableMapperImpl implements ITableMapper {

    @Override
    public Table toEntity(TableDto dto) {
        if ( dto == null ) {
            return null;
        }

        Table.TableBuilder table = Table.builder();

        table.id( dto.id() );
        table.chairsNumber( dto.chairsNumber() );
        table.stateTable( dto.stateTable() );
        table.booking( bookingDtoToBooking( dto.booking() ) );

        return table.build();
    }

    @Override
    public TableDto toDto(Table entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        Integer chairsNumber = null;
        EStateTable stateTable = null;
        BookingDto booking = null;

        id = entity.getId();
        chairsNumber = entity.getChairsNumber();
        stateTable = entity.getStateTable();
        booking = bookingToBookingDto( entity.getBooking() );

        TableDto tableDto = new TableDto( id, chairsNumber, stateTable, booking );

        return tableDto;
    }

    @Override
    public List<Table> toEntitylist(List<TableDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Table> list = new ArrayList<Table>( dtoList.size() );
        for ( TableDto tableDto : dtoList ) {
            list.add( toEntity( tableDto ) );
        }

        return list;
    }

    @Override
    public List<TableDto> toDtoList(List<Table> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TableDto> list = new ArrayList<TableDto>( entityList.size() );
        for ( Table table : entityList ) {
            list.add( toDto( table ) );
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

    protected Booking bookingDtoToBooking(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.id( bookingDto.id() );
        booking.bookingDate( bookingDto.bookingDate() );
        booking.bookingHour( bookingDto.bookingHour() );
        booking.description( bookingDto.description() );
        booking.stateReservation( bookingDto.stateReservation() );
        booking.user( userDtoToUser( bookingDto.user() ) );

        return booking.build();
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

    protected BookingDto bookingToBookingDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        Integer id = null;
        LocalDate bookingDate = null;
        LocalTime bookingHour = null;
        String description = null;
        EStateReservation stateReservation = null;
        UserDto user = null;

        id = booking.getId();
        bookingDate = booking.getBookingDate();
        bookingHour = booking.getBookingHour();
        description = booking.getDescription();
        stateReservation = booking.getStateReservation();
        user = userToUserDto( booking.getUser() );

        BookingDto bookingDto = new BookingDto( id, bookingDate, bookingHour, description, stateReservation, user );

        return bookingDto;
    }
}
