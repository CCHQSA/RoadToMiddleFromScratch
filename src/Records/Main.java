package Records;

public class Main {
    static void main() {
        UserResponse user = new UserResponse(1,"CCHQSA", "user@email.com");
        System.out.println(user.id());
        System.out.println(user.email());
        System.out.println(user.username());
        System.out.println(user.toString());

    }
}
