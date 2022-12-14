package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
    Optional<UserStats> findByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
