package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.UserDTO;
import com.example.empowerprobackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(source.getId());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        userDTO.setBirthdate(source.getBirthdate());
        userDTO.setBirthplace(source.getBirthplace());
        userDTO.setPhoneNumber(source.getPhoneNumber());
        userDTO.setHireDate(source.getHireDate());
        userDTO.setGender(source.getGender());
        userDTO.setRole(source.getRole());
        userDTO.setEmail(source.getEmail());
        userDTO.setBytePhoto(source.getPhoto());
        userDTO.setFileName(source.getFileName());
        userDTO.setFileType(source.getFileType());
        userDTO.setAddress(source.getAddress());
        return userDTO;
    }


}



