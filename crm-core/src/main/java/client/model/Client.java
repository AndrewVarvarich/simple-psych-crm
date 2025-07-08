package client.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String notes;
    private LocalDateTime createdAt;
}
