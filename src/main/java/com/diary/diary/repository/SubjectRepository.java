package com.diary.diary.repository;

import com.diary.diary.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findByName(String subjectName);
}
