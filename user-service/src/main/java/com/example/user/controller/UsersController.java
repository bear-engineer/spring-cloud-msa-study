package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.service.UserServiceImpl;
import com.example.user.vo.Greeting;
import com.example.user.vo.RequestUser;
import com.example.user.vo.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class UsersController {
    private final UserServiceImpl userService;

    private final Greeting greeting;

    private final ModelMapper modelMapper;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
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
}
