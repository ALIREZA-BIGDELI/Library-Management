import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class History {
    private File historyFile;

    public History(){
        historyFile = new File("Information/History.txt");
    }

    public void printHistoryOfUser(String userID) throws FileNotFoundException {
        Scanner scanner = new Scanner(historyFile);
        List<String> list = new ArrayList<>();
        while (scanner.hasNext())
            list.add(scanner.nextLine());

        int start = list.get(0).indexOf("userID:") + "userID:".length();
        int end = list.get(0).indexOf(" " , start);

        list.stream()
                .filter(a ->a.substring(start , end).equals(userID))
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
}
