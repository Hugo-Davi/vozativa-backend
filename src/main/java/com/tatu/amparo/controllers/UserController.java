package com.tatu.amparo.controllers;

import com.tatu.amparo.dto.user.UserCredentials;
import com.tatu.amparo.dto.user.UserHeader;
import com.tatu.amparo.models.collections.User;
import com.tatu.amparo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "user")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "(Admin) Get all users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @Operation(summary = "Get user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.get(id));
    }

    @Operation(summary = "(ADMIN) Update user")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update (@PathVariable("id") String id, @RequestBody User user){

        if (!this.userService.existById(id)){
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        this.userService.update(user);

        return ResponseEntity
                .ok(user);
    }

    @Operation(summary = "Update logged user")
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateMe (@RequestBody User user, JwtAuthenticationToken token){

        if (!this.userService.existById(token.getName())){
            return ResponseEntity.notFound().build();
        }
        user.setId(token.getName());
        this.userService.update(user);

        return ResponseEntity
                .ok(user);
    }

    @Operation(summary = "Update the user Birthday, Name, Description or Address")
    @RequestMapping(value = "/header", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserHeader> updateMyUserHeader (@RequestBody UserHeader userHeader, JwtAuthenticationToken token){

        if (!this.userService.existById(token.getName())){
            return ResponseEntity.notFound().build();
        }
        this.userService.updateUserHeader(token.getName(), userHeader);

        return ResponseEntity
                .ok(userHeader);
    }

    @Operation(summary = "Update logged user CPF, E-Mail or Phone Number")
    @RequestMapping(value = "/credential", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCredentials> updateMyUserCredentials (@RequestBody UserCredentials userCredentials, JwtAuthenticationToken token){

        if (!this.userService.existById(token.getName())){
            return ResponseEntity.notFound().build();
        }
        this.userService.updateUserCredentials(token.getName(), userCredentials);

        return ResponseEntity
                .ok(userCredentials);
    }

    @Operation(summary = "(ADMIN) Delete user by id")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable String id) {
        User user = this.userService.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        this.userService.delete(id);

        return ResponseEntity
                .ok(user);
    }

    @Operation(summary = "Get the logged user information")
    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getMe (JwtAuthenticationToken token){

        System.out.println(token.getName());
        if(token.getName() == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(this.userService.get(token.getName()));
    }
}