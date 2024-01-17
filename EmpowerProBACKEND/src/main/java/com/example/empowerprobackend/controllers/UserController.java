package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.ChangePasswordDTO;
import com.example.empowerprobackend.dto.ProfileUpdateDTO;
import com.example.empowerprobackend.dto.RestPasswordDTO;
import com.example.empowerprobackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("EmpowerPro/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("forgetPassword/{email}")
    public ResponseEntity<?> forgetPassword(@PathVariable String email) {

        try {
            return userService.forgetPassword(email);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable(value = "token") String token, @RequestBody RestPasswordDTO resetPasswordDTO) {
        try {
            return userService.resetPassword(token, resetPasswordDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while resetting the password.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("updatePassword/{id}")
    public ResponseEntity<?> updatePasswordById(@RequestBody ChangePasswordDTO changePasswordDto, @PathVariable("id") Long id) {
        return userService.updatePassword(changePasswordDto, id);
    }
    @PostMapping("/updateUserProfile")
    public ResponseEntity<?> updateUserProfile(@ModelAttribute  ProfileUpdateDTO profileUpdateDTO) {
        try {
            return new ResponseEntity<>(userService.updateUserProfile(profileUpdateDTO), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?>findAll() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @GetMapping("/getManagers")
        public ResponseEntity<?>findAllManagers() {
            try {
                return new ResponseEntity<>(userService.findAllManagers(), HttpStatus.OK);
            } catch (DataAccessException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

  @GetMapping("/getEmployees")
  public ResponseEntity<?>findAllEmployees(){
  try {
   return new ResponseEntity<>(userService.findAllEmployees(), HttpStatus.OK);
   }catch (DataAccessException e ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
      }catch (Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }

}
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


