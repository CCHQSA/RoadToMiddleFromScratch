package SOLID.SOLID_Single_Responsibility;


/*
class UserService {
    void registerUser() {}
    void sendEmail() {}
    void saveToDatabase() {}
    void generatePdf() {}
}
 */
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository,  EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    void registerUser() {
        userRepository.saveToDatabase();
        emailService.sendEmail();
    }
}
