package org.adaschool.proyectoReservas.application.controller;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.service.UserService;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public record UserController(UserService userService) {
   @PostMapping("/saveUser")
   public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
      userService.createUser(userDto);
      return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @GetMapping("search/{idUser}")
   public ResponseEntity<?> findUserById(@PathVariable("idUser") Integer idUser) throws ReservationException {
      userService.findUserById(idUser);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @GetMapping("/allUsers/{offset}/{limit}")
   public ResponseEntity<?> allUsers
     (@PathVariable("offset") Integer offset, @PathVariable("limit") Integer limit) throws ReservationException {
      List<UserDto> userDtoList = userService.listAllUsers(offset, limit);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @PutMapping("update/{idUser}")
   public ResponseEntity<?> updateUser(@PathVariable("idUser") Integer idUser, UserDto userDto) throws ReservationException {
      userService.updateUser(idUser, userDto);
      return new ResponseEntity<>(HttpStatus.OK);
   }
}
