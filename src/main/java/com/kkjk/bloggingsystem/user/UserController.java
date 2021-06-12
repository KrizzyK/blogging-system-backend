package com.kkjk.bloggingsystem.user;


import com.kkjk.bloggingsystem.security.TokenProvider;
import com.kkjk.bloggingsystem.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final UserService userService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody UserLoginRequestDto loginUser) throws AuthenticationException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);

            return ResponseEntity.ok(new AuthToken(token));

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponseDto> saveUser(@RequestBody UserRegisterRequestDto user) {
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userService.save(user));
        } catch (EmailAlreadyTakenException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/admin/promoteUserToRedactor", method = RequestMethod.POST)
    public ResponseEntity<Void> promoteUserToRedactor(@RequestParam UUID userId) {
        try{
            userService.promoteUserToRedactor(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/admin/demoteUserToRedactor", method = RequestMethod.POST)
    public ResponseEntity<Void> demoteUserToRedactor(@RequestParam UUID userId) {
        try{
            userService.demoteUserToRedactor(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/admin/promoteUserToAdmin", method = RequestMethod.POST)
    public ResponseEntity<Void> promoteUserToAdmin(@RequestParam UUID userId) {
        try{
            userService.promoteUserToAdmin(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        try{
            userService.changePassword(changePasswordRequestDto);
            return ResponseEntity.ok().build();
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserBasicInfoDto>> getAllUsers() {
        try{

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.getAllUsers());
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/adminping", method = RequestMethod.GET)
    public String adminPing() {
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
        return "Any User Can Read This";
    }

    @PreAuthorize("hasRole('REDACTOR')")
    @RequestMapping(value = "/redactorping", method = RequestMethod.GET)
    public String redactorPing() {
        return "Any redactor Can Read This";
    }

    @RequestMapping(value = "/anyoneping", method = RequestMethod.GET)
    public String anyonePing() {
        return "Any User Can Read This";
    }
}
