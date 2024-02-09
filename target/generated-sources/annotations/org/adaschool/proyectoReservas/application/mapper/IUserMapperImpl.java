package org.adaschool.proyectoReservas.application.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T14:11:47-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.id() );
        user.name( dto.name() );
        user.lastName( dto.lastName() );
        user.email( dto.email() );
        user.password( dto.password() );
        user.enable( dto.enable() );
        user.document( dto.document() );
        user.roles( dto.roles() );

        return user.build();
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
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

        id = entity.getId();
        name = entity.getName();
        lastName = entity.getLastName();
        email = entity.getEmail();
        password = entity.getPassword();
        enable = entity.isEnable();
        document = entity.getDocument();
        roles = entity.getRoles();

        UserDto userDto = new UserDto( id, name, lastName, email, password, enable, document, roles );

        return userDto;
    }

    @Override
    public List<User> toEntityList(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDtoList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }
}
