package com.finalproject.user;

import com.finalproject.user.mapper.UserMapper;
import com.finalproject.user.model.ERole;
import com.finalproject.user.model.Role;
import com.finalproject.user.dto.UserDTO;
import com.finalproject.user.dto.UserListDTO;
import com.finalproject.user.dto.UserMinimalDTO;
import com.finalproject.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        // find user with role Employee
        return userRepository.findAll()
                .stream().filter(user -> (user.getRoles().stream()
                        .filter(role -> role.getName() == ERole.EMPLOYEE)
                        .findAny()
                        .orElse(null) != null))
                .map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

   public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
   }

   public void create(UserDTO userDTO){
       User user = User.builder()
               .username(userDTO.getUsername())
               .password(encoder.encode(userDTO.getPassword()))
               .email(userDTO.getEmail())
               .build();

       Set<String> rolesStr = userDTO.getRoles();
       Set<Role> roles = new HashSet<>();

       if (rolesStr == null) {
           Role defaultRole = roleRepository.findByName(ERole.EMPLOYEE)
                   .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));
           roles.add(defaultRole);
       } else {
           rolesStr.forEach(r -> {
               Role ro = roleRepository.findByName(ERole.valueOf(r))
                       .orElseThrow(() -> new RuntimeException("Cannot find role: " + r));
               roles.add(ro);
           });
       }

       user.setRoles(roles);
       userRepository.save(user);

//        return userMapper.toDto(userRepository.save(
//                userMapper.fromDto(user)
//        ));
   }

   public void edit(UserDTO userDTO){
        User actualUser = findById(userDTO.getId());
        actualUser.setUsername(userDTO.getUsername());
        actualUser.setEmail(userDTO.getEmail());

        if (userDTO.getPassword() != null) {
            actualUser.setPassword(userDTO.getPassword());
        }

       Set<String> rolesStr = userDTO.getRoles();
       Set<Role> roles = new HashSet<>();

       if (rolesStr == null) {
           Role defaultRole = roleRepository.findByName(ERole.EMPLOYEE)
                   .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));
           roles.add(defaultRole);
       } else {
           rolesStr.forEach(r -> {
               Role ro = roleRepository.findByName(ERole.valueOf(r))
                       .orElseThrow(() -> new RuntimeException("Cannot find role: " + r));
               roles.add(ro);
           });
       }

       actualUser.setRoles(roles);
       userRepository.save(actualUser);

   }

    public void delete(UserDTO userDTO){
        User actualUser = findById(userDTO.getId());
        userRepository.delete(actualUser);

    }


}
