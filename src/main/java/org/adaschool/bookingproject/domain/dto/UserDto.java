package org.adaschool.bookingproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.adaschool.bookingproject.application.lasting.ERoles;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Integer id,
        String name,
        String lastName,
        String email,
        String password,
        boolean enable,
        String document,
        ERoles roles

) {}
