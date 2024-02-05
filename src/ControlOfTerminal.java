import java.io.IOException;
import java.util.Scanner;

public class ControlOfTerminal {

    public ControlOfTerminal(User user) throws IOException {
        System.out.println("Welcome to our Library");
        menu: while (true){
            Scanner scanner = new Scanner(System.in);
            int control;
            System.out.println("1. Add User");
            System.out.println("2. Show User");
            System.out.println("3. Show All User");
            System.out.println("4. Remove User");
            System.out.println("5. Exit");
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

                    if(!checkContinue())
                        break menu;
                    continue;

                case 2:
                    System.out.print("Enter the desired user number: ");
                    int id = scanner.nextInt();
                    user.printInformationOfUser(id);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 3:
                    user.printInformationOfAllUsers();
                    if(!checkContinue())
                        break menu;
                    continue;


                case 4:
                    System.out.print("Enter the user ID you want to delete: ");
                    int id1 = scanner.nextInt();
                    user.deleteUser(id1);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 5:
                    break menu;
            }
        }
        user.writeID();
    }


    private boolean checkContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("if you want to continue press 1 : ");
        int n = scanner.nextInt();
        if(n == 1)
            return true;
        return false;
    }

}
