package elisa_daria.be_mood_watch.payloads.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Name is required")
        @Size(min = 2, max = 25, message = "Name can't be less than 2 characters or more than 25")
        String name,
        @NotEmpty(message = "Surname is required")
        @Size(min = 2, max = 25, message = "Surname can't be less than 2 characters or more than 25")
        String surname,
        @NotEmpty(message = "Email is required")
        @Email(message = "Email given is invalid")
        String email,
        @NotEmpty(message = "Username is required")
        @Size(min = 2, max = 30, message = "Username can't be less than 2 characters or more than 30 characters")
        String username,
        @NotEmpty(message = "Password is required")
        @Size(min = 6, message = "Password can't be less than 6 characters")
        String password
) {
}
