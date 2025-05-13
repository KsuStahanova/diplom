package com.example.demo.officeapp.api;

import com.example.demo.officeapp.service.UserService;
import com.example.demo.officeapp.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to users")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get user by email", security = @SecurityRequirement(name = "bearerAuth"))
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email).orElseThrow();
    }
}