package org.adaschool.bookingproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.adaschool.bookingproject.application.lasting.EStateReservation;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingDto(
        Integer id,
        LocalDate bookingDate,
        LocalTime bookingHour,
        String description,
        EStateReservation stateReservation,
        UserDto user
) {}
