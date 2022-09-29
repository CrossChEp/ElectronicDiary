package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.RoleEntity;
import com.diary.diary.exception.UserAlreadyExistsException;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.repository.RoleRepository;
import com.diary.diary.repository.UserRepository;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.UserNotFoundException;
import com.diary.diary.model.user.UserAddModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            throw new UsernameNotFoundException("user with such login not found");
        }
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(user.getRole().toString());
        return new User(Long.toString(user.getId()), user.getPassword(), List.of(userRole));
    }

    public UserEntity addUser(UserAddModel userData) throws UserAlreadyExistsException {
        UserEntity user = userRepo.findByLogin(userData.getLogin());
        if(user != null) {
            throw new UserAlreadyExistsException("user with such login already exists");
        }
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        user = createUser(userData);
        userRepo.save(user);
        return user;
    }

    private UserEntity createUser(UserAddModel userData) {
        UserEntity user = new UserEntity();
        createDefaultRoleIfNotExists();
        RoleEntity role = roleRepo.findByName(RoleNames.DEFAULT);
        ModelMapper mapper = new ModelMapper();
        mapper.map(userData, user);
        user.setRole(role);
        return user;
    }

    private void createDefaultRoleIfNotExists() {
        if(roleRepo.findByName(RoleNames.DEFAULT) == null) {
            RoleEntity role = new RoleEntity();
            role.setName(RoleNames.DEFAULT);
            roleRepo.save(role);
        }
    }

    public List<UserGetModel> getUsers() {
        List<UserEntity> users = userRepo.findAll();
        return convertToUserGetModelList(users);
    }

    private List<UserGetModel> convertToUserGetModelList(List<UserEntity> users) {
        return users.stream().map(this::convertUserToGetModel).toList();
    }

    public UserGetModel getUser(long userID) throws UserNotFoundException {
        UserEntity user = userRepo.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("no user with such id"));
        return convertUserToGetModel(user);
    }

    private UserGetModel convertUserToGetModel(UserEntity user) {
        return UserGetModel.toModel(user);
    }

    public UserEntity updateUser(UserUpdateModel newUserData) throws UserNotFoundException{
        UserEntity user = getCurrentUser();
        updateUserData(newUserData, user);
        userRepo.save(user);
        return user;
    }

    public UserEntity getCurrentUser() throws UserNotFoundException {
        Long userId = Long.valueOf((String) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal());
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("current user wasn't found"));
    }

    private void updateUserData(UserUpdateModel newUserData, UserEntity user) {
        if(newUserData.getPassword() != null) {
            newUserData.setPassword(hashPassword(newUserData.getPassword()));
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(newUserData, user);
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public UserEntity deleteUser() throws UserNotFoundException {
        UserEntity user = getCurrentUser();
        userRepo.delete(user);
        return user;
    }
}
