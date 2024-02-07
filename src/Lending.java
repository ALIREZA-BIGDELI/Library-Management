import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lending {

    User user;
    Book book;
    private File lendingFile;

    public Lending(User user , Book book){
        this.user = user;
        this.book = book;
        lendingFile = new File("Information/Lending.txt");
        try {
            lendingFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrowBook(String userID , String ISBN) throws IOException {
        int numberOfBookLoanedFromUser = numberOfBooksLoaned(userID , ISBN);
        if(isFree(ISBN) == true && numberOfBookLoanedFromUser <= 4) {
            FileWriter fileWriter = new FileWriter(lendingFile.getPath(), true);
            fileWriter.write("userID:" + userID);
            fileWriter.write(" ");
            fileWriter.write("bookID:" + ISBN);
            fileWriter.write(" \n");
            fileWriter.close();
            System.out.println("The book was lent");
            System.out.println("-----------");


            //add to History File
            FileWriter fileWriterToHistory = new FileWriter("Information/History.txt" , true);
            fileWriterToHistory.write("userID:" + userID);
            fileWriterToHistory.write(" ");
            fileWriterToHistory.write("bookID:" + ISBN);
            fileWriterToHistory.write(" \n");
            fileWriterToHistory.close();
        }
        else if(isFree(ISBN) == false){
            System.out.println("The book is on loan");
            System.out.println("-----------");
        }
        else {
            System.out.println("You have borrowed more than the allowed book limit ");
            System.out.println("-----------");
        }
    }

    public void returnBook(String userID , String ISBN) throws IOException {
        ArrayList<String> contentsOfFile = new ArrayList<>();
        Scanner scanner = new Scanner(new File(lendingFile.getPath()));
        while (scanner.hasNext())
            contentsOfFile.add(scanner.nextLine());
//        if(!isFree(ISBN)){
//
//        }
        boolean isDeleted = false;
        for (int i = 0; i < contentsOfFile.size() && isDeleted == false; i++) {
            int startOfUserID = contentsOfFile.get(i).indexOf("userID:") + "userID:".length();
            int endOfUserID = contentsOfFile.get(i).indexOf(" ", startOfUserID);
            String checkID = contentsOfFile.get(i).substring(startOfUserID, endOfUserID);

            int startOfBookID = contentsOfFile.get(i).indexOf("bookID:") + "bookID:".length();
            int endOfBookID = contentsOfFile.get(i).indexOf(" ", startOfBookID);
            String checkISBN = contentsOfFile.get(i).substring(startOfBookID, endOfBookID);
            if (checkISBN.equals(ISBN) && checkID.equals(userID)) {
                contentsOfFile.remove(i);
                isDeleted = true;
            }
        }

        if(isDeleted){
            FileWriter fileWriter = new FileWriter(lendingFile.getPath());
            fileWriter.write("");
            fileWriter.close();

            FileWriter fWriterWithAppend = new FileWriter(lendingFile.getPath() , true);
            for(int i = 0; i < contentsOfFile.size(); i++)
                fWriterWithAppend.write(contentsOfFile.get(i) + "\n");

            fWriterWithAppend.close();
            System.out.println("Successfully returned");
            System.out.println("-----------");
            return;
        }

        else {
            System.out.println("This book has not been lent");
            System.out.println("-----------");
        }
    }

    public void printAllLoanedBooks() throws FileNotFoundException {
        Scanner scanner = new Scanner(lendingFile);
        if(scanner.hasNext() == false){
            System.out.println("No books have been lent");
            System.out.println("-----------");
            return;
        }

        while (scanner.hasNext()){
            String line = scanner.nextLine();
            int startOfUserID = line.indexOf("userID:") + "userID:".length();
            int endOfUserID = line.indexOf(" " , startOfUserID);
            String userID = line.substring(startOfUserID , endOfUserID);
            user.printInformationOfUser(Integer.valueOf(userID));

            int startOfBookID = line.indexOf("bookID:") + "bookID:".length();
            int endOfBookID = line.indexOf(" " , startOfBookID);
            String bookID = line.substring(startOfBookID , endOfBookID);
            book.printInformationOfBook(bookID);
            System.out.println("*****************************");
        }
    }
    private boolean isFree(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(lendingFile);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String checkISBN = getISBN(line);
            if(checkISBN.equals(ISBN))
                return false;
        }
        return true;
    }

    private String getISBN(String str) throws FileNotFoundException {
        Scanner scanner = new Scanner(lendingFile);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            int start = line.indexOf("bookID:") + "bookID:".length();
            int end = line.indexOf(" " , start);
            String ISBN = line.substring(start , end);
            return ISBN;
        }
        return null;
    }

    private int numberOfBooksLoaned(String userID , String bookID) throws FileNotFoundException {
        Scanner scanner = new Scanner(lendingFile);
        int count = 0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            int start1 = line.indexOf("userID:") + "userID:".length();
            int end1 = line.indexOf(" " , start1);
            String checkUserID = line.substring(start1 , end1);
            if(checkUserID.equals(userID)){
//                int start2 = line.indexOf("bookID:") + "bookID:".length();
//                int end2 = line.indexOf(" " , start2);
//                String checkBookID = line.substring(start2 , end2);
//                if(checkBookID.equals(bookID))
                    count++;
            }
        }
        return count;
    }

//    private String searchInformationOfUser(String userID) throws FileNotFoundException {
//        Scanner scanner = new Scanner(user.getUserFile());
//        while (scanner.hasNext()){
//            String line = scanner.nextLine();
//            boolean checkID = user.checkIdOfUser(line , Integer.valueOf(userID));
//            if(checkID){
//
//            }
//        }
//    }
}
