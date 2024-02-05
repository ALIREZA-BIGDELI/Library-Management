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

    public void deleteUser(int id) throws IOException {
        Scanner scanner = new Scanner(new File(userFile.getPath()));
        ArrayList<String> contentsOfFile = new ArrayList<>();
        while (scanner.hasNext())
            contentsOfFile.add(scanner.nextLine());


        boolean isDeleted = false;
        for(int i = 0; i < contentsOfFile.size() && isDeleted == false; i++){
            int start = 3;
            int end = contentsOfFile.get(i).indexOf(" ");
            String temp = contentsOfFile.get(i).substring(start , end);
            int checkid = Integer.valueOf(temp);
            if(checkid == id) {
                contentsOfFile.remove(i);
                isDeleted = true;
            }
        }

        if(isDeleted){
            FileWriter fileWriter = new FileWriter(userFile.getPath());
            fileWriter.write("");
            fileWriter.close();

            FileWriter fWriterWithAppend = new FileWriter(userFile.getPath() , true);
            for(int i = 0; i < contentsOfFile.size(); i++)
                fWriterWithAppend.write(contentsOfFile.get(i) + "\n");

            fWriterWithAppend.close();
            System.out.println("user deleted");
            System.out.println("-----------");
            return;
        }

        else {
            System.out.println("user not found");
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

