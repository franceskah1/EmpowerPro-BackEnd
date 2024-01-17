package com.example.empowerprobackend.services;
import com.example.empowerprobackend.dto.RegisterRequest;
import com.example.empowerprobackend.dto.RegistrationDTO;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@AllArgsConstructor
@Service
public class RegisterService {
    private final UserRepository userRepository;
    private  final EmailService emailService;

    private final static Logger LOGGER= LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<?> registerUser(RegistrationDTO registrationDTO)  {

        User userToRegister=new User();
        if (userRepository.existsByEmail(registrationDTO.getEmail())== TRUE ){
            return new ResponseEntity<>("There is already an user with this email address.Please use another email!", HttpStatus.CONFLICT);
            }else{
                userToRegister.setFirstName(registrationDTO.getFirstName());
                userToRegister.setLastName(registrationDTO.getLastName());
                userToRegister.setUserName(registrationDTO.getUsername());
                userToRegister.setEmail(registrationDTO.getEmail());
                userToRegister.setAddress(registrationDTO.getAddress());
                userToRegister.setBirthdate(registrationDTO.getBirthdate());
                userToRegister.setBirthplace(registrationDTO.getBirthplace());
                userToRegister.setRole(registrationDTO.getRole());
                userToRegister.setDepartments(registrationDTO.getDepartments());
                userToRegister.setGender(registrationDTO.getGender());
                userToRegister.setPhoneNumber(registrationDTO.getPhoneNumber());
                userToRegister.setHireDate(registrationDTO.getHireDate());
                userToRegister.setConfirmationToken(generateConfirmationToken());
                userToRegister.setTokenCreationDate(LocalDateTime.now());


            }
            try {
                userRepository.save(userToRegister);
                String link="http://localhost:3000/auth/empowerPro/registration/" + userToRegister.getConfirmationToken();
                emailService.send(registrationDTO.getEmail(), emailService.buildEmail(registrationDTO.getFirstName(),link));

            }catch (Exception exception){
                return new ResponseEntity<>("This user can not be saved.Please try again!",HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(userToRegister.getConfirmationToken(),HttpStatus.OK);
    }

    private String generateConfirmationToken() {
        String confirmationToken= UUID.randomUUID().toString();
        return confirmationToken;

    }
    @Transactional
    public String savePassword(String token, RegisterRequest confirmationRequest) {

        User userToUpdatePassword = userRepository.findUserByConfirmationToken(token);

        try {
            if (userToUpdatePassword == null) {
                return "Invalid token. User not found.";
            }
            if (userToUpdatePassword.getTokenConfirmationDate() != null) {
                return "This email has already been confirmed.";
            }
            LocalDateTime expireAt = userToUpdatePassword.getTokenCreationDate().plusHours(24);
            if (expireAt.isBefore(LocalDateTime.now())) {
                userRepository.deleteById(userToUpdatePassword.getId());
                return "The verification link has been expired!";
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "User password can not be saved.The verification link is expired";

        }
        try {
            String encodedUserPassword = bCryptPasswordEncoder.encode(confirmationRequest.getPassword());
            userToUpdatePassword.setPassword(encodedUserPassword);
            userToUpdatePassword.setTokenConfirmationDate(LocalDateTime.now());
            userToUpdatePassword.setEnabled(true);
            userRepository.save(userToUpdatePassword);
            return "User password was saved successfully.";
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            return "An error occurred while saving the user password.";
        }

    }




}
