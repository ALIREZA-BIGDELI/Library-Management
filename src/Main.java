import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        try {
            ControlOfTerminal controlOfTerminal = new ControlOfTerminal(user);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
