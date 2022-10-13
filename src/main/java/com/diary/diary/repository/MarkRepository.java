package com.diary.diary.repository;

import com.diary.diary.entity.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
}
