package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.UserSlimDTO;
import com.example.empowerprobackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserSlimDTO implements Converter<User, UserSlimDTO> {
    @Override
    public UserSlimDTO convert(User source) {
        if (source!=null){
            UserSlimDTO userSlimDTO=new UserSlimDTO();
            userSlimDTO.setUserId(source.getId());
            userSlimDTO.setRole(source.getRole());
            userSlimDTO.setFirstName(source.getFirstName()) ;
            userSlimDTO.setLastName(source.getLastName());
            return userSlimDTO;
        }
        return null;
    }
}
