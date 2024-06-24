package com.example.special.lecture.application.user.db.repository;

import com.example.special.lecture.application.user.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
