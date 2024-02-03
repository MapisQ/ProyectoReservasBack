package org.adaschool.bookingproject.application.mapper;

import org.adaschool.bookingproject.application.mapper.base.IBaseMapper;
import org.adaschool.bookingproject.domain.dto.UserDto;
import org.adaschool.bookingproject.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper extends IBaseMapper {

    User toEntity(UserDto dto);
    UserDto toDto(User entity);
    List<User> toEntityList(List<UserDto> dtoList);
    List<UserDto> toDtoList(List<User> entityList);
}
