package org.adaschool.proyectoReservas;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.application.mapper.IUserMapper;
import org.adaschool.proyectoReservas.application.service.UserService;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.adaschool.proyectoReservas.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IUserMapper mapperU;
    @Autowired
    private UserService userService;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1)
                .name("Juan")
                .lastName("Quito")
                .email("juanpis1150@gmail.com")
                .password("juanito")
                .enable(true)
                .document("1785963542")
                .roles(ERoles.Client)
                .build();

        userDto = new UserDto
                (1,"Juan","Quito","juanpis1150@gmail.com",
                        "juanito",true,"1785963542",ERoles.Client);
    }

    @Test
    void saveUser(){

        when(mapperU.toEntity(userDto)).thenReturn(user);
        userService.createUser(userDto);

        assertNotNull(user);
        verify(userRepository).save(user);
    }

    @Test
    void findUserById(){
        final Integer id=1;

        when(mapperU.toDto(user)).thenReturn(userDto);
        try{
            UserDto result = userService.findUserById(id);
            assertNotNull(result);
            assertNotNull(userDto);

            assertEquals(result,userDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void notFoundUserById(){
        final Integer id=1;

        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ReservationException.class,()-> userService.findUserById(id));
    }

    @Test
    void allUsers(){
        final Integer offset = 0;
        final Integer limit = 50;

        List<User> userList = Arrays.asList(user);
        List<UserDto> expectedList = Arrays.asList(mapperU.toDto(user));

        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(userList));
        when(mapperU.toDtoList(userList)).thenReturn(expectedList);

        try{
            List<UserDto> result = userService.listAllUsers(offset,limit);
            assertEquals(expectedList,result);
        }catch (ReservationException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateUser(){
        final Integer id=1;

       User userM = User.builder()
                .id(1)
                .name("Angela")
                .lastName("Quito")
                .email("mapis1004@gmail.com")
                .password("angelita")
                .enable(true)
                .document("69814365")
                .roles(ERoles.Client)
                .build();

       UserDto userDtoM = new UserDto
                (1,"Angela","Quito","mapis1004@gmail.com",
                        "angelita",true,"69814365",ERoles.Client);

       when(mapperU.toEntity(userDtoM)).thenReturn(userM);
       when(userRepository.findById(id)).thenReturn(Optional.of(userM));

       try{
           userService.updateUser(id,userDtoM);
           verify(userRepository).save(userM);
       }catch (ReservationException e) {
           throw new RuntimeException(e);
       }

    }
}
