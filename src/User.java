import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private File userFile;
    private int user_id;
    public User(){
        try {
            userFile = new File("Information/Users.txt");
            Scanner scanner = new Scanner(new File("Information/user_id.txt"));
            user_id = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("file not created");
        }
    }

    public void addUser(String name , String email , String password) throws IOException {
        if(isEmailDuplicate(email) == false) {
            FileWriter fileWriter = new FileWriter(userFile.getPath(), true);
            fileWriter.write("ID:" + user_id);
            fileWriter.write(" ");
            fileWriter.write("name:" + name);
            fileWriter.write(" ");
            fileWriter.write("email:" + email);
            fileWriter.write(" ");
            fileWriter.write("password:" + password);
            fileWriter.write("\n");
            fileWriter.close();
            System.out.println("user successfully created <" + user_id + ">");
            System.out.println("-----------");
            user_id++;
        }
        else {
            System.out.println("duplicate value for email");
            System.out.println("-----------");
        }
    }

    private boolean isEmailDuplicate(String email) throws FileNotFoundException {
        Scanner scanner = new Scanner(userFile);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            int start = line.indexOf("email:") + "email:".length();
            int end = line.indexOf(" " , start);
            String extractedEmail = line.substring(start , end);
            if(email.equals(extractedEmail))
                return true;
        }
        return false;
    }

    void writeID() throws IOException {
        FileWriter fileWriter = new FileWriter("Information/user_id.txt");
        fileWriter.write(String.valueOf(user_id));
        fileWriter.close();

    }
}

