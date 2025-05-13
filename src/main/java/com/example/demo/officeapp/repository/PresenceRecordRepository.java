package com.example.demo.officeapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.officeapp.model.PresenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRecordRepository extends JpaRepository<PresenceRecord, Long> {
    List<PresenceRecord> findByUser_Id(Long userId);
    List<PresenceRecord> findByEntryTimeBetween(LocalDateTime start, LocalDateTime end);
}