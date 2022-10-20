package com.diary.diary.service;

import com.diary.diary.entity.RoleEntity;
import com.diary.diary.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public void addRole(String name) {
        RoleEntity role = new RoleEntity();
        role.setName(name);
        roleRepository.save(role);
    }
}
