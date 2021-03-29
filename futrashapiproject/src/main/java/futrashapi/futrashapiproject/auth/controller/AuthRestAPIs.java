package futrashapi.futrashapiproject.auth.controller;


import futrashapi.futrashapiproject.auth.message.request.LoginForm;
import futrashapi.futrashapiproject.auth.message.request.SignUpForm;
import futrashapi.futrashapiproject.auth.message.response.JwtResponse;
import futrashapi.futrashapiproject.auth.model.Role;
import futrashapi.futrashapiproject.auth.model.RoleName;
import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.auth.repository.RoleRepository;
import futrashapi.futrashapiproject.auth.repository.UserRepository;
import futrashapi.futrashapiproject.auth.security.jwt.JwtProvider;
import futrashapi.futrashapiproject.flow_handle.exception.ResourceNotFoundException;
import futrashapi.futrashapiproject.flow_handle.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/foodTrash/user")

public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByName(signUpRequest.getName())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getName(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch(role) {
                case "seller":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_SELLER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "buyer":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_BUYER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }

    //edit user test
    @PutMapping("/edit/{id}")
    public void editUser(@RequestHeader("Authorization") String token, @PathVariable long id, @RequestBody User userGet) {
        User user= new User();
        user.setName(userGet.getName());
        user.setEmail(userGet.getEmail());
        user.setPhone(userGet.getPhone());
        user.setPassword(userGet.getPassword());
        user.setRoles(userGet.getRoles());
        userRepository.save(user);

    }

    //tes delete user
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@RequestHeader("Authorization") String token,@PathVariable long id) {
        userRepository.deleteById(id);

    }

    @GetMapping("/show/{id}")
    public User getUser( @RequestHeader("Authorization") String token,@PathVariable long id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

    }
}
