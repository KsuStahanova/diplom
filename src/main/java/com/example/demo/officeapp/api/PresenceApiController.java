package com.example.demo.officeapp.api;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.officeapp.service.PresenceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.officeapp.model.PresenceRecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/presence")
@Tag(name = "Presence API", description = "Operations related to presence tracking")
public class PresenceApiController {
    private final PresenceService presenceService;

    public PresenceApiController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all presence records", security = @SecurityRequirement(name = "bearerAuth"))
    public List<PresenceRecord> getAllPresenceRecords() {
        return presenceService.getAllPresenceRecords();
    }

    @GetMapping("/between")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get presence records between dates", security = @SecurityRequirement(name = "bearerAuth"))
    public List<PresenceRecord> getPresenceRecordsBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return presenceService.getPresenceRecordsBetween(start, end);
    }
}