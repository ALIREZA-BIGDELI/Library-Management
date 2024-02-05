import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Book book = new Book();
        try {
            ControlOfTerminal controlOfTerminal = new ControlOfTerminal(user , book);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
