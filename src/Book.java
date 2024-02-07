import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {

    private File bookFile;

    public Book(){
        bookFile = new File("Information/Books.txt");
    }

    public void addBook(String name, String author , String ISBN) throws IOException {
        if(isBookDuplicate(ISBN) == false) {
            FileWriter fileWriter = new FileWriter(this.getBookFile().getPath(), true);
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

    public void printInformationOfBook(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(this.getBookFile());
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            boolean checkISBN = checkISBNOfBook(line , ISBN);
            if(checkISBN){
                String informationOfBook = line;
                System.out.println(informationOfBook);
                System.out.println("-----------");
                return;
            }
        }
        System.out.println("book not found");
        System.out.println("-----------");

    }

    public void printInformationOfAllBooks() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.getBookFile());

        if(scanner.hasNext() == false){
            System.out.println("no book exists");
            return;
        }

        while (scanner.hasNext()) {
            String informationOfUser = scanner.nextLine();
            System.out.println(informationOfUser);
        }
        System.out.println("-----------");
    }

    public void deleteBook(String ISBN) throws IOException {
        Scanner scanner = new Scanner(new File(getBookFile().getPath()));
        ArrayList<String> contentsOfFile = new ArrayList<>();
        while (scanner.hasNext())
            contentsOfFile.add(scanner.nextLine());


        boolean isDeleted = false;
        for(int i = 0; i < contentsOfFile.size() && isDeleted == false; i++){
            int start = contentsOfFile.get(i).indexOf("ISBN:") + "ISBN:".length();
            int end = contentsOfFile.get(i).indexOf(" " , start);
            String temp = contentsOfFile.get(i).substring(start , end);
            String checkISBN = temp;
            if(ISBN.equals(checkISBN)) {
                contentsOfFile.remove(i);
                isDeleted = true;
            }
        }

        if(isDeleted){
            FileWriter fileWriter = new FileWriter(getBookFile().getPath());
            fileWriter.write("");
            fileWriter.close();

            FileWriter fWriterWithAppend = new FileWriter(getBookFile().getPath() , true);
            for(int i = 0; i < contentsOfFile.size(); i++)
                fWriterWithAppend.write(contentsOfFile.get(i) + "\n");

            fWriterWithAppend.close();
            System.out.println("book deleted");
            System.out.println("-----------");
            return;
        }

        else {
            System.out.println("book not found");
            System.out.println("-----------");
        }
    }


    private boolean isBookDuplicate(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(getBookFile());
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


    public boolean checkISBNOfBook(String str , String ISBN){
        int start = str.indexOf("ISBN:") + "ISBN:".length();
        int end = str.indexOf(" " , start);
        String checkISBN = str.substring(start , end);
        if(checkISBN.equals(ISBN))
            return true;
        return false;
    }

    public File getBookFile() {
        return bookFile;
    }
}
