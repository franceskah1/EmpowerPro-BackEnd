package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.ProfileUpdateDTOToUser;
import com.example.empowerprobackend.converters.UserToUserDTO;
import com.example.empowerprobackend.dto.ChangePasswordDTO;
import com.example.empowerprobackend.dto.ProfileUpdateDTO;
import com.example.empowerprobackend.dto.RestPasswordDTO;
import com.example.empowerprobackend.dto.UserDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.Role;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.RoleRepository;
import com.example.empowerprobackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 60;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;
    private CustomUserDetailsService customUserDetailsService;
    private final ProfileUpdateDTOToUser profileUpdateDTOToUser;
    private final UserToUserDTO toUserDTO;
    private static final Logger logger = LogManager.getLogger(UserService.class);


    private static final Logger LOGGER = LogManager.getLogger(UserService.class);


    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid);
    }

    private boolean tokenIsExpired(final LocalDateTime tokenCreationDateForgotPassword) {
        LocalDateTime now = LocalDateTime.now();
        Duration difference = Duration.between(tokenCreationDateForgotPassword, now);
        return difference.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;

    }

    public ResponseEntity<?> forgetPassword(String email) throws MessagingException {
        try {
            Optional<User> userOptional = userRepository.findUsersByEmail(email);
            if (!userOptional.isPresent()) {
                return new ResponseEntity<>("There is no user with this email address.", HttpStatus.BAD_REQUEST);
            }

            User user = userOptional.get();
            user.setForgetPasswordToken(generateToken());
            user.setForgetPasswordTokenCreationDate(LocalDateTime.now());
            user = userRepository.save(user);

            String link = "http://localhost:3000/EmpowerPro/resetPassword/" + user.getForgetPasswordToken();

            if (customUserDetailsService.exists(email)) {
                emailService.send(user.getEmail(), emailService.buildResetEmail


                        (user.getFirstName(), link));
            }
            return new ResponseEntity<>(user.getForgetPasswordToken(), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Database error while processing forgetPassword: " + e.getMessage());
            return new ResponseEntity<>("A database error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOGGER.error("Error in forgetPassword: " + e.getMessage());
            return new ResponseEntity<>("An error occurred while processing the password reset request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> resetPassword(String uuid, RestPasswordDTO resetPasswordDTO) {
        try {
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findByForgetPasswordToken(uuid));
            if (!optionalUser.isPresent()) {
                return new ResponseEntity<>("The redirection link is invalid ", HttpStatus.NOT_ACCEPTABLE);
            }

            LocalDateTime tokenCreationDate = optionalUser.get().getForgetPasswordTokenCreationDate();
            if (tokenIsExpired(tokenCreationDate)) {
                return new ResponseEntity<>("The link has expired. Please complete once again the forgot password form.", HttpStatus.NOT_ACCEPTABLE);
            }

            User user = optionalUser.get();
            user.setPassword(bCryptPasswordEncoder.encode(resetPasswordDTO.getPassword()));
            user.setForgetPasswordToken(null);
            userRepository.save(user);
            return new ResponseEntity<>("Your password was successfully changed!", HttpStatus.OK);

        } catch (DataAccessException e) {
            LOGGER.error("Database error while processing resetPassword: " + e.getMessage());
            return new ResponseEntity<>("A database error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOGGER.error("Unexpected error in resetPassword: " + e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> updatePassword(ChangePasswordDTO changePasswordDTO, Long userId) {
        User currentUser = userRepository.findById(userId).get();


        if (Objects.nonNull(changePasswordDTO.getPassword()) && !"".equalsIgnoreCase(changePasswordDTO.getPassword())) {

        }

        if (bCryptPasswordEncoder.matches(changePasswordDTO.getPassword(), currentUser.getPassword())) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
            currentUser.setPassword(encodePassword);
            userRepository.save(currentUser);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Your password is wrong!", HttpStatus.BAD_REQUEST);
        }

    }

    public Object updateUserProfile(ProfileUpdateDTO profileUpdateDTO) {
        try {
             userRepository.save(Objects.requireNonNull(profileUpdateDTOToUser.convert(profileUpdateDTO)));
            return "Profile Updated Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("Profile can not be updated due to data access problems.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
    public UserDTO findById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("User id: \"" + id + "\" can't be parsed!");
        }
        return toUserDTO.convert(userRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> toUserDTO.convert(user)).collect(Collectors.toList());
    }

    public String deleteUserById(String userId) {

        try {
            Long parseId = Long.parseLong(userId);
            userRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {

            throw new InternalError("User  id: \"" + userId + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("User  with id " + userId + " not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }

    public List<UserDTO> findAllManagers() {
        Role managerRole = roleRepository.findByName("MANAGER");
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(managerRole))
                .map(toUserDTO::convert)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findAllEmployees() {
        Role employeeRole = roleRepository.findByName("EMPLOYEE");
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(employeeRole))
                .map(toUserDTO::convert)
                .collect(Collectors.toList());
    }
}



