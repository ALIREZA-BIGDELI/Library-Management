import java.io.IOException;
import java.util.Scanner;

public class ControlOfTerminal {

    public ControlOfTerminal(User user , Book book) throws IOException {
        System.out.println("Welcome to our Library");
        menu: while (true){
            Scanner scanner = new Scanner(System.in);
            int control;
            System.out.println("1. Add User");
            System.out.println("2. Show User");
            System.out.println("3. Show All User");
            System.out.println("4. Remove User");
            System.out.println("5. Add Book");
            System.out.println("8. Remove Book");
            System.out.println("9. Exit");
            System.out.print("Please select the desired option: ");
            control = scanner.nextInt();
            switch (control){
                case 1:
                    System.out.print("Enter the User's name: ");
                    String userName = scanner.next();
                    System.out.print("Enter the User's email: ");
                    String email = scanner.next();
                    System.out.print("Enter a password: ");
                    String password = scanner.next();
                    user.addUser(userName , email , password);

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
                    System.out.print("Enter the book's name: ");
                    String bookName = scanner.next();
                    System.out.print("Enter the book's author: ");
                    String author = scanner.next();
                    System.out.print("Enter the book's ISBN: ");
                    String ISBN = scanner.next();
                    book.addBook(bookName , author , ISBN);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 8:
                    System.out.print("Enter the book's ISBN you want to delete: ");
                    String isbn1 = String.valueOf(scanner.nextInt());
                    book.deleteBook(isbn1);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 9:
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
