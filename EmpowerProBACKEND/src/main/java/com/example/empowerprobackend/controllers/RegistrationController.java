package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.RegisterRequest;
import com.example.empowerprobackend.dto.RegistrationDTO;
import com.example.empowerprobackend.services.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/EmpowerPro")
@AllArgsConstructor
public class RegistrationController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {

        return registerService.registerUser(registrationDTO);
    }

    @PostMapping("savePassword/{token}")
    public String savePassword(@PathVariable("token") String token, @RequestBody RegisterRequest confirmationRequest) {
        try {
            return registerService.savePassword(token, confirmationRequest);
        } catch(IllegalArgumentException  e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
