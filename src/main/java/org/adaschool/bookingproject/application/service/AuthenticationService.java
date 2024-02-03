package org.adaschool.bookingproject.application.service;

import org.adaschool.bookingproject.application.lasting.ERoles;
import org.adaschool.bookingproject.application.mapper.IUserMapper;
import org.adaschool.bookingproject.domain.dto.AuthenticationDto;
import org.adaschool.bookingproject.domain.dto.UserDto;
import org.adaschool.bookingproject.domain.entity.User;
import org.adaschool.bookingproject.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record   AuthenticationService(
        UserRepository userRepository,
        JwtService jwtService,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        IUserMapper userMapper
) {
    public String register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setEnable(true);
        user.setRoles(ERoles.Client);
        userRepository.save(user);
        return jwtService.generateToken(user);
    }
    public String authenticate(AuthenticationDto authenticationDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.email(),authenticationDto.password())
        );
        User user = userRepository.findUserByEmail(authenticationDto.email()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
