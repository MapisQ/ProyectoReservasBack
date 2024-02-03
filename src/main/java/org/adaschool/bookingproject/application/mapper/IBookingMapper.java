package org.adaschool.bookingproject.application.mapper;

import org.adaschool.bookingproject.application.mapper.base.IBaseMapper;
import org.adaschool.bookingproject.domain.dto.BookingDto;
import org.adaschool.bookingproject.domain.entity.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBookingMapper extends IBaseMapper {

    Booking toEntity(BookingDto dto);
    BookingDto toDto(Booking entity);
    List<Booking> toEntityList(List<BookingDto> dtoList);
    List<BookingDto> toDtoList(List<Booking> entityBooking);
}
