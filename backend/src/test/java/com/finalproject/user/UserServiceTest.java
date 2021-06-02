package com.finalproject.user;

import com.finalproject.user.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;


    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, userMapper, roleRepository, encoder);
    }

    @Test
    public void allUsersMinimal() {
    }

    @Test
    public void allUsersForList() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void create() {
//        Role employeeRole = new Role();
//        employeeRole.setId(2);
//        employeeRole.setName(ERole.EMPLOYEE);
//        Set<Role> userRoles = new HashSet<Role>();
//        userRoles.add(employeeRole);
//
//        UserDTO userDTO = UserDTO.builder().username(randomString())
//                .email(randomString())
//                .password(randomString())
//                .roles(userRoles)
//                .build();
//
////        User user = User.builder().username(userDTO.getUsername())
////                .email(userDTO.getEmail())
////                .password(userDTO.getPassword())
////                .roles(userDTO.getRoles())
////                .build();
//
//
////        when(userMapper.fromDto(userDTO)).thenReturn(user);
////
////        when(userRepository.save(userMapper.fromDto(userDTO))).thenReturn(user);
//
//        when(userMapper.toDto(userRepository.save(userMapper.fromDto(userDTO)))).thenReturn(userDTO);
//
//        UserDTO createdUserDTO = userService.create(userDTO);
//
//        Assertions.assertEquals(userDTO, createdUserDTO);



    }
}