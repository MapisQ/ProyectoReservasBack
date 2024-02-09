package org.adaschool.proyectoReservas.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingDto(
        Integer id,
        LocalDate bookingDate,
        LocalTime bookingHour,
        String description,
        EStateReservation stateReservation,
        UserDto user
) {}
