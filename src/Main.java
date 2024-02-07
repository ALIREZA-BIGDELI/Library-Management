import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Book book = new Book();
        Lending lending = new Lending(user , book);
        History history = new History();
        try {
            ControlOfTerminal controlOfTerminal = new ControlOfTerminal(user , book , lending , history);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
