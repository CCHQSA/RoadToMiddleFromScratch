package Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataBase {
    private List<User> users;

    public DataBase() {
        users = new ArrayList<>();
    }

    public Optional<User> findUserById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}

