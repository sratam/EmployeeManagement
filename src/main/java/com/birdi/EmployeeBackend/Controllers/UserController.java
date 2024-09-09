package com.birdi.EmployeeBackend.Controllers;


import com.birdi.EmployeeBackend.Model.User;
import com.birdi.EmployeeBackend.Services.JWTService;
import com.birdi.EmployeeBackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userservice.getallusers(),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userservice.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> response = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            User newuser = userservice.getByUsername(user.getUsername());
            response.put("status","success");
            response.put("token",jwtService.generateJWTtoken(user.getUsername()));
            response.put("username",user.getUsername());
            response.put("role",newuser.getRole());

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.put("status", "failed");
        response.put("message", "Invalid username or password");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
