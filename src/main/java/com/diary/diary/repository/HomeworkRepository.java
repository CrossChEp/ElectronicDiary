package com.diary.diary.repository;

import com.diary.diary.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {
}
