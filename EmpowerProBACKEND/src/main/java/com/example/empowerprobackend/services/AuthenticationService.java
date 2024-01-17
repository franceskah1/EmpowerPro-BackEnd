package com.example.empowerprobackend.services;

import com.example.empowerprobackend.dto.LoginDTO;
import com.example.empowerprobackend.dto.LoginResponse;
import com.example.empowerprobackend.exceptions.LogoutFailedException;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.UserRepository;
import com.example.empowerprobackend.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private HttpSession session;

    private final AuthenticationProvider authenticationManager;
   private static final Logger logger = LogManager.getLogger(UserService.class);


    public Object login(LoginDTO loginDTO) {


        LoginResponse loginResponse;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword()));
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateAccessToken(userRepository.findUsersByEmailEnabled(loginDTO.getEmail()).get());
            loginResponse = setUserData(userRepository.findUsersByEmailEnabled(loginDTO.getEmail()).get(), token);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Login failed ! Invalid email or password.");
        } catch (Exception e) {
    logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }


    public LoginResponse setUserData(User user, String token) {
        LoginResponse apiResponse = new LoginResponse();
        apiResponse.setId(user.getId());
        apiResponse.setEmail(user.getEmail());
        apiResponse.setFirstName(user.getFirstName());
        apiResponse.setLastName(user.getLastName());
        apiResponse.setRole(user.getRole().getName());
        apiResponse.setAccessToken(token);

        return apiResponse;
    }
    public String logout(String id) {
        try {
            Long parseId = Long.parseLong(id);
            if (parseId != null && userRepository.existsById(parseId)) {
                session.invalidate();
                SecurityContextHolder.clearContext();
                return "Logout successful. You have been successfully logged out.";
            }
            throw new LogoutFailedException("Logout failed. No user exists with this id");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("User id: \"" + id + "\" can't be parsed!");
        } catch (Exception e) {
          logger.error(e.getMessage(), e);
            throw new LogoutFailedException("Logout failed. Please try again later.");
        }
    }
}


