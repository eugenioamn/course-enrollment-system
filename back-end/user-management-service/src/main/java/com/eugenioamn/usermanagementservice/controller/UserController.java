package com.eugenioamn.usermanagementservice.controller;

import com.eugenioamn.usermanagementservice.model.Role;
import com.eugenioamn.usermanagementservice.model.User;
import com.eugenioamn.usermanagementservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController("/service")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> create(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);

        return new ResponseEntity<>(userService.create(user),
                HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> get(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(
                userService.getByUsername(principal.getName()));
    }

    @GetMapping("/names")
    public ResponseEntity<?> getByIdList(@RequestBody List<Long> idList) {
        return ResponseEntity.ok(userService.getByIdList(idList));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It is working!");
    }
}
