package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.ProfileUpdateDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
@Getter
@Setter
@Component
@AllArgsConstructor
public class ProfileUpdateDTOToUser implements Converter<ProfileUpdateDTO, User> {
    private final UserRepository userRepository;
    @Override
    public User convert(ProfileUpdateDTO source) {
        if (source!=null){

            if (source.getId()!=null){
                User user = userRepository.findById(source.getId()).orElseThrow(()->
                        new NotFoundException("User not found"));
                user.setId(source.getId());

                if (source.getFirstName()!=null)
                    user.setFirstName(source.getFirstName());

                if (source.getLastName()!=null)
                    user.setLastName(source.getLastName());

                if (source.getEmail()!=null)
                    user.setEmail(source.getEmail());

                if (source.getPhoneNumber()!=null) {
                    user.setPhoneNumber(source.getPhoneNumber());
                }

                if (source.getPlaceOfBirth()!=null)
                    user.setBirthplace(source.getPlaceOfBirth());

                if (source.getDateOfBirth()!=null)
                    user.setBirthdate(Instant.ofEpochMilli(source.getDateOfBirth()).atZone(ZoneId.systemDefault()).toLocalDate());

                if (source.getAddress()!=null)
                    user.setAddress(source.getAddress());

                if (source.getPhoto()==null & source.getId()!=null){
                    user.setPhoto(user.getPhoto());
                    user.setFileName(user.getFileName());
                    user.setFileType(user.getFileType());
                }else {
                    try {
                        user.setPhoto(source.getPhoto().getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    user.setFileName( StringUtils.cleanPath(source.getPhoto().getOriginalFilename()));
                    user.setFileType(source.getPhoto().getContentType());
                }

                return user;
            }
        }
        return null;

    }



}
