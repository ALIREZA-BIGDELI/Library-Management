import java.io.IOException;
import java.util.Scanner;

public class ControlOfTerminal {

    public ControlOfTerminal(User user) throws IOException {
        System.out.println("Welcome to our Library");
        menu: while (true){
            Scanner scanner = new Scanner(System.in);
            int control;
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Exit");
            System.out.print("Please select the desired option: ");
            control = scanner.nextInt();
            switch (control){
                case 1:
                    System.out.print("Enter the User's name: ");
                    String name = scanner.next();
                    System.out.print("Enter the User's email: ");
                    String email = scanner.next();
                    System.out.print("Enter a password: ");
                    String password = scanner.next();
                    user.addUser(name , email , password);
                    continue;

                case 2:
                    System.out.print("Enter the user ID you want to delete: ");
                    int id = scanner.nextInt();
                    user.deleteUser(id);
                    continue;

                case 3:
                    user.deleteUser(1);
                    user.writeID();
                    break menu;
            }
        }
    }

}
