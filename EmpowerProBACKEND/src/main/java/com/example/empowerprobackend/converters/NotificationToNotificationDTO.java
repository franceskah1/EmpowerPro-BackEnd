package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.NotificationDTO;
import com.example.empowerprobackend.models.Notifications;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class NotificationToNotificationDTO implements Converter<Notifications, NotificationDTO> {
    private final UserToUserSlimDTO toUserSlimDTO;
    @Override
    public NotificationDTO convert(Notifications source) {
       if (source!=null){
           NotificationDTO notificationDTO=new NotificationDTO();
           notificationDTO.setId(source.getId());
           notificationDTO.setNotificationTypes(source.getNotificationTypes().name());
           notificationDTO.setNotificationTime(source.getNotificationTime());
           notificationDTO.setMessage(source.getMessage());
           notificationDTO.setUserSlimDTO(toUserSlimDTO.convert(source.getUser()));
           return notificationDTO;

       }
        return null;
    }
}
