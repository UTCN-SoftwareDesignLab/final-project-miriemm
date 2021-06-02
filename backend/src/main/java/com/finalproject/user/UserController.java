package com.finalproject.user;

import com.finalproject.UrlMapping;
import com.finalproject.security.dto.MessageResponse;
import com.finalproject.user.dto.UserDTO;
import com.finalproject.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //private final RoleRepository roleRepository;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {
        if(userService.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        userService.create(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PatchMapping
    public ResponseEntity<?> edit(@Valid @RequestBody UserDTO userDTO) {

        userService.edit(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody UserDTO userDTO){

        userService.delete(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

}
