import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Book {

    private File bookFile;

    public Book(){
        bookFile = new File("Information/Books.txt");
    }

    public void addBook(String name, String author , String ISBN) throws IOException {
        if(isBookDuplicate(ISBN) == false) {
            FileWriter fileWriter = new FileWriter(this.bookFile.getPath(), true);
            fileWriter.write("name:" + name);
            fileWriter.write(" ");
            fileWriter.write("author:" + author);
            fileWriter.write(" ");
            fileWriter.write("ISBN:" + ISBN);
            fileWriter.write(" \n");
            fileWriter.close();
            System.out.println("BOOK CREATED");
            System.out.println("-----------");
        }
        else {
            System.out.println("DUPLICATE BOOK-ID");
            System.out.println("-----------");
        }
    }


    private boolean isBookDuplicate(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(bookFile);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            int start = line.indexOf("ISBN:") + "ISBN:".length();
            int end = line.indexOf(" " , start);
            String extractedISBN = line.substring(start , end);
            if(ISBN.equals(extractedISBN))
                return true;
        }
        return false;
    }
}
