package Records;

import java.util.Objects;

public record UserResponse(
        long id,
        String username,
        String email
) {

    public UserResponse {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(username, "username cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
    }
}
