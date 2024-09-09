package com.birdi.EmployeeBackend.Services;

import com.birdi.EmployeeBackend.Model.User;
import com.birdi.EmployeeBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userrepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);


    public User createUser(User user){
        user.setRole("EMPLOYEE");
        user.setPassword(encoder.encode(user.getPassword()));
        userrepo.save(user);
        return user;
    }

    public User getByUsername(String username){
        return userrepo.findByUsername(username);
    }

    public List<User> getallusers(){
        return userrepo.findAll();
    }


}
