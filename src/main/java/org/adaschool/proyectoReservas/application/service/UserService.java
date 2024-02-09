package org.adaschool.proyectoReservas.application.service;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.lasting.EMessage;
import org.adaschool.proyectoReservas.application.mapper.IUserMapper;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.adaschool.proyectoReservas.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository, IUserMapper mapper) {

    public void createUser(UserDto userDto) {
        User user = mapper().toEntity(userDto);
        userRepository.save(user);
    }

    public UserDto findUserById(Integer idUser) throws ReservationException {
        User user = userRepository.findById(idUser).orElseThrow(()-> new ReservationException(EMessage.ID_NOT_FOUND));
        return mapper.toDto(user);
    }

    public List<UserDto> listAllUsers(Integer offset, Integer limit) throws ReservationException {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> users = userRepository.findAll(pageable);
        if (users.getContent().isEmpty()) {
            throw new ReservationException(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(users.getContent());
    }

    public void updateUser(Integer idUser, UserDto userDto) throws ReservationException {
        userRepository.findById(idUser).orElseThrow(()-> new ReservationException(EMessage.USER_NOT_FOUND));
        User user = mapper.toEntity(userDto);
        userRepository.save(user);
    }





}
