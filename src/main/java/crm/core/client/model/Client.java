package crm.core.client.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    private Long id;

    @NotBlank(message = "ФИО клиента не может быть пустым")
    private String fullName;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "Номер телефона должен быть в формате +7XXXXXXXXXX (11 цифр)"
    )
    private String phoneNumber;

    private String notes;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
