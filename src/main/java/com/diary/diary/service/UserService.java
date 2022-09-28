package com.diary.diary.service;

import com.diary.diary.entity.RoleEntity;
import com.diary.diary.exception.UserAlreadyExistsException;
import com.diary.diary.repository.RoleRepository;
import com.diary.diary.repository.UserRepository;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.UserNotFoundException;
import com.diary.diary.model.user.UserAddModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;


    private final BCryptPasswordEncoder bCryptPasswordEncoder
            = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByLogin(username);
        if(user == null) {
            throw new UserNotFoundException("user with such login not found");
        }
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(user.getRole().toString());
        return new User(user.getLogin(), user.getPassword(), List.of(userRole));
    }

    public void addUser(UserAddModel userData) {
        UserEntity user = userRepo.findByLogin(userData.getLogin());
        if(user != null) {
            throw new UserAlreadyExistsException("user with such login already exists");
        }
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        user = new UserEntity();
        RoleEntity role = roleRepo.findByName("default");
        ModelMapper mapper = new ModelMapper();
        mapper.map(userData, user);
        user.setRole(role);
        userRepo.save(user);
    }
}
