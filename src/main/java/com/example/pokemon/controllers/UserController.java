package com.example.pokemon.controllers;

import com.example.pokemon.entities.User;
import com.example.pokemon.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Find user with name.")
    @GetMapping // ?username=
    @Secured("ROLE_USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "User authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find user.", content = @Content)
    })
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String username) {
        var users = userService.findAll(username);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Find user with id.")
    @GetMapping("/{id}")
    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Editor or admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find user.", content = @Content)
    })
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "User search by name detail.")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find user.", content = @Content)
    })
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {
        var user = userService.findByUserNameRegex(name);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "User search by name detail, city, username detail, reg_year.")
    @GetMapping("/filter")
    public ResponseEntity<List<User>> findByAttributes(@RequestParam(name = "name") String name,
                                                       @RequestParam(name = "city")  String city,
                                                       @RequestParam(name = "username")  String username,
                                                       @RequestParam(name = "reg_year")  String reg_year) {
        var user = userService.findByUserAttributes(name,city,username,reg_year);
        return ResponseEntity.ok(user);
    }


    @Operation(summary = "Create a user.")
    @PostMapping
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @Operation(summary = "User update by id.")
    @PutMapping("/{id}")
    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin or editor authentication is required.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @Validated @RequestBody User user)
    {
        System.out.println();
        userService.update(id, user);
    }

    @Operation(summary = "User delete by id.")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted user.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find user.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
