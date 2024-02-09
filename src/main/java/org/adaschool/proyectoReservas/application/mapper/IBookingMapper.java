package org.adaschool.proyectoReservas.application.mapper;

import org.adaschool.proyectoReservas.application.mapper.base.IBaseMapper;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBookingMapper extends IBaseMapper {

    Booking toEntity(BookingDto dto);
    BookingDto toDto(Booking entity);
    List<Booking> toEntityList(List<BookingDto> dtoList);
    List<BookingDto> toDtoList(List<Booking> entityBooking);
}
