package com.diary.diary.repository;

import com.diary.diary.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    ClassEntity findByNumber(int number);
    ClassEntity findByLetter(char letter);
    ClassEntity findByNumberAndLetter(int number, char letter);
}
