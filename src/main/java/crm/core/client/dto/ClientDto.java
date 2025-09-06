package crm.core.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private Long id;

    @NotBlank(message = "ФИО клиента не может быть пустым")
    private String fullName;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "Номер телефона должен быть в формате +7XXXXXXXXXX (11 цифр)"
    )
    private String phoneNumber;

    private String notes;
}
