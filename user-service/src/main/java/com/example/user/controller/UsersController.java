package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.entity.UserEntity;
import com.example.user.service.UserServiceImpl;
import com.example.user.vo.Greeting;
import com.example.user.vo.RequestUser;
import com.example.user.vo.ResponseUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class UsersController {
    private final UserServiceImpl userService;

    private final Greeting greeting;

    private final ModelMapper modelMapper;

    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on Port %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {

        UserDto userDto = this.modelMapper.map(requestUser, UserDto.class);

        this.userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> users = this.userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();

        users.forEach(v -> result.add(this.modelMapper.map(v, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = this.userService.getUserByUserId(userId);

        ResponseUser returnValue = this.modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
