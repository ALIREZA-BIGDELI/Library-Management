import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ControlOfTerminal {

    public ControlOfTerminal(User user , Book book , Lending lending , History history) throws IOException {
        System.out.println("Welcome to our Library");
        menu: while (true){
            Scanner scanner = new Scanner(System.in);
            int control;
            System.out.println("1. Add User");
            System.out.println("2. Show User");
            System.out.println("3. Show All User");
            System.out.println("4. Remove User");
            System.out.println("5. Add Book");
            System.out.println("6. Show Book");
            System.out.println("7. Show All Book");
            System.out.println("8. Remove Book");
            System.out.println("9. Borrow Book");
            System.out.println("10. Return the book");
            System.out.println("11. Show Loaned books");
            System.out.println("12. Show History Of User");
            System.out.println("13. Show History Of Book");
            System.out.println("14. Exit");
            System.out.println("15. Extract name With 'i'");
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
                    System.out.print("Enter the desired user ID: ");
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

                case 6:
                    System.out.print("Enter the desired book ISBN: ");
                    String isbn = scanner.next();
                    book.printInformationOfBook(isbn);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 7:
                    book.printInformationOfAllBooks();
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
                    String userID , bookID;
                    System.out.print("Enter the user's id :");
                    userID = scanner.next();
                    System.out.print("Enter the book's ISBN :");
                    bookID = scanner.next();
                    lending.borrowBook(userID , bookID);

                    if(!checkContinue())
                        break menu;
                    continue;

                case 10:
                    System.out.print("Enter the ID of user: ");
                    String idForReturn = String.valueOf(scanner.nextInt());
                    System.out.print("Enter the ISBN of the book you want to return: ");
                    String returnISBN = String.valueOf(scanner.nextInt());
                    lending.returnBook(idForReturn , returnISBN);

                    if(!checkContinue())
                        break menu;
                    continue;


                case 11:
                    lending.printAllLoanedBooks();
                    if(!checkContinue())
                        break menu;
                    continue;


                case 12:
                    int userId;
                    System.out.print("Enter the ID of user: ");
                    userId = scanner.nextInt();
                    history.printHistoryOfUser(String.valueOf(userId));

                    if(!checkContinue())
                        break menu;
                    continue;

                case 13:
                    int ISBN2;
                    System.out.print("Enter the ISBN of Book: ");
                    ISBN2 = scanner.nextInt();
                    history.printHistoryOfBook(String.valueOf(ISBN2));

                    if(!checkContinue())
                        break menu;
                    continue;

                case 14:
                    break menu;

                case 15:
                    List list = user.getNameWithI();
                    list.stream()
                            .forEach(a -> System.out.println(list));


                    if(!checkContinue())
                        break menu;
                    continue;
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
