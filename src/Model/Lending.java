package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lending {

    private User user;
    private Book book;
    private History history;
    private File lendingFile;

    public Lending(User user , Book book , History history){
        this.user = user;
        this.book = book;
        this.history = history;
        lendingFile = new File("Information/Model.Lending.txt");
        try {
            lendingFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrowBook(String userID , String ISBN) throws IOException {
        int numberOfBookLoanedFromUser = numberOfBooksLoaned(userID , ISBN);
        if(isFree(ISBN) == true && numberOfBookLoanedFromUser <= 4 && isTheBookAvailable(ISBN)) {
            FileWriter fileWriter = new FileWriter(lendingFile.getPath(), true);
            fileWriter.write("userID:" + userID);
            fileWriter.write(" ");
            fileWriter.write("bookID:" + ISBN);
            fileWriter.write(" \n");
            fileWriter.close();
            System.out.println("The book was lent");
            System.out.println("-----------");
            history.addToHistory(userID , ISBN , true);
        }
        else if(isFree(ISBN) == false){
            System.out.println("The book is on loan");
            System.out.println("-----------");
        }

        else if (isTheBookAvailable(ISBN) == false){
            System.out.println("The book is not available");
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

                history.addToHistory(userID , ISBN , false);
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

    private boolean isTheBookAvailable(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(book.getBookFile());
        boolean result = false;
        while (scanner.hasNext()){
            if (book.checkISBNOfBook(scanner.nextLine() , ISBN) == true)
                return true;
        }
        return false;
    }

    private String getISBN(String str) throws FileNotFoundException {
        int start = str.indexOf("bookID:") + "bookID:".length();
        int end = str.indexOf(" " , start);
        String ISBN = str.substring(start , end);
        return ISBN;
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
                    count++;
            }
        }
        return count;
    }
}
