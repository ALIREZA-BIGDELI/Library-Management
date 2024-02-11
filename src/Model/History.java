package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class History {
    private File historyFile;

    public History(){
        try {
        historyFile = new File("Information/Model.History.txt");
        historyFile.createNewFile();
        }
        catch (Exception e){

        }
    }

    public void printHistoryOfUser(String userID) throws FileNotFoundException {
        Scanner scanner = new Scanner(historyFile);
        List<String> list = new ArrayList<>();
        while (scanner.hasNext())
            list.add(scanner.nextLine());
        AtomicInteger i = new AtomicInteger(0);

        list.stream()
                .filter(a ->{
                    int start = list.get(i.get()).indexOf("userID:") + "userID:".length();
                    int end = list.get(i.get()).indexOf(" " , start);
                    i.getAndIncrement();
                    return a.substring(start , end).equals(userID);
                })
                .forEach(a -> System.out.println(a));

    }

    public void printHistoryOfBook(String ISBN) throws FileNotFoundException {
        Scanner scanner = new Scanner(historyFile);
        List<String> list = new ArrayList<>();
        while (scanner.hasNext())
            list.add(scanner.nextLine());


        for (int i = 0; i < list.size(); i++){

        int start = list.get(i).indexOf("bookID:") + "bookID:".length();
        int end = list.get(i).indexOf(" " , start);

        String checkISBN = list.get(i).substring(start , end);

        if (checkISBN.equals(ISBN) == false)
            list.remove(i--);
        }

        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

    }

    public void addToHistory(String userID, String ISBN, boolean typeOfLend) throws IOException {  //if borrow the book typeOfLend = true , if return the book typeOfLend = false
        FileWriter fileWriter = new FileWriter(this.historyFile , true);
        if(typeOfLend == true)
            fileWriter.write("Model.Lending) ");
        else
            fileWriter.write("Take Back) ");
        fileWriter.write("userID:" + userID);
        fileWriter.write(" ");
        fileWriter.write("bookID:" + ISBN);
        fileWriter.write(" ");
        Date date = new Date();
        fileWriter.write("Date: " + date);
        fileWriter.write(" \n");
        fileWriter.close();
    }
}
