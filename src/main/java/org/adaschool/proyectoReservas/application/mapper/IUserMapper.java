package org.adaschool.proyectoReservas.application.mapper;

import org.adaschool.proyectoReservas.application.mapper.base.IBaseMapper;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper extends IBaseMapper {

    User toEntity(UserDto dto);
    UserDto toDto(User entity);
    List<User> toEntityList(List<UserDto> dtoList);
    List<UserDto> toDtoList(List<User> entityList);
}
