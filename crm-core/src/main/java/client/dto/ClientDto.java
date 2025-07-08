package client.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String notes;
    private LocalDateTime createdAt;
}
