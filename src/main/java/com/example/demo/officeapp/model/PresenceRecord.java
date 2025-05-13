package com.example.demo.officeapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "presence_records")
public class PresenceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean manuallyEdited;

    public PresenceRecord(User user) {
        this.user = user;
        this.entryTime = LocalDateTime.now();
    }

    public PresenceRecord(LocalDateTime entryTime, LocalDateTime exitTime, Long id, boolean manuallyEdited, User user) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.id = id;
        this.manuallyEdited = manuallyEdited;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public boolean isManuallyEdited() {
        return manuallyEdited;
    }

    public void setManuallyEdited(boolean manuallyEdited) {
        this.manuallyEdited = manuallyEdited;
    }
}