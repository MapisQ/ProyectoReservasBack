package org.adaschool.proyectoReservas.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.adaschool.proyectoReservas.application.lasting.EStateTable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TableDto(
        Integer id,
        Integer chairsNumber,
        EStateTable stateTable,
        BookingDto booking
) {}
