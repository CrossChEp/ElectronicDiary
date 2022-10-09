package com.diary.diary.repository;

import com.diary.diary.entity.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimetableEntity, Long> {
}
