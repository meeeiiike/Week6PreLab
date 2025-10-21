package ie.atu.week5lab3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor@AllArgsConstructor@Data@Builder
public class Passenger {
    @NotBlank(message = "id required") @Size(max=40)
    private String passengerID;
    @NotBlank(message = "name is required") @Size(max=60)
    private String name;
    @NotBlank(message = "email is required") @Email(message = "must be valid email")
    private String email;
}
