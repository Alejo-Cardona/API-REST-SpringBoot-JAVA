package com.coder.API_REST_SpringBoot_JAVA.controller;

import com.coder.API_REST_SpringBoot_JAVA.models.User;
import com.coder.API_REST_SpringBoot_JAVA.service.UserService;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findUser(@PathVariable UUID id){
        return  userService.findUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al crear el usuario.");
        }

    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        Optional<User> existingUser = userService.findUser(id);

        if(!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User updatedDataUser = existingUser.get();

        Optional.ofNullable(user.getFirst_name()).ifPresent(updatedDataUser::setFirst_name);
        Optional.ofNullable(user.getLast_name()).ifPresent(updatedDataUser::setLast_name);
        Optional.ofNullable(user.getEmail()).ifPresent(updatedDataUser::setEmail);
        Optional.ofNullable(user.getPassword()).ifPresent(updatedDataUser::setPassword);
        Optional.ofNullable(user.getDni()).ifPresent(updatedDataUser::setDni);
        Optional.ofNullable(user.getResidence()).ifPresent(updatedDataUser::setResidence);

        if (user.getAge() != 0) {
            updatedDataUser.setAge(user.getAge());
        }

        User savedUser = userService.saveUser(updatedDataUser);

        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean isRemoved = userService.deleteUser(id);

        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
