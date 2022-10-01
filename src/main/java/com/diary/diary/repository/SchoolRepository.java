package com.diary.diary.repository;

import com.diary.diary.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
    SchoolEntity findByNumber(long number);
}
