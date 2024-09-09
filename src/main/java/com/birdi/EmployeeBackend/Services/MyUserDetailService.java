package com.birdi.EmployeeBackend.Services;

import com.birdi.EmployeeBackend.Model.User;
import com.birdi.EmployeeBackend.Model.userPrinciple;
import com.birdi.EmployeeBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new userPrinciple(user);

    }
}
