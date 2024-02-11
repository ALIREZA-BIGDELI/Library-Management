package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private File userFile;
    private int user_id;

    public User() {
        try {
            userFile = new File("Information/Users.txt");
            Scanner scanner = new Scanner(new File("Information/user_id.txt"));
            user_id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("file not created");
        }
    }

    public void addUser(String userName, String email, String password) throws IOException {
        if (isEmailDuplicate(email) == false) {
            FileWriter fileWriter = new FileWriter(this.getUserFile().getPath(), true);
            fileWriter.write("ID:" + user_id);
            fileWriter.write(" ");
            fileWriter.write("Username:" + userName);
            fileWriter.write(" ");
            fileWriter.write("email:" + email);
            fileWriter.write(" ");
            fileWriter.write("password:" + password);
            fileWriter.write("\n");
            fileWriter.close();
            System.out.println("user successfully created <" + user_id + ">");
            System.out.println("-----------");
            user_id++;
        } else {
            System.out.println("duplicate value for email");
            System.out.println("-----------");
        }
    }

    public void deleteUser(int id) throws IOException {
        Scanner scanner = new Scanner(new File(getUserFile().getPath()));
        ArrayList<String> contentsOfFile = new ArrayList<>();
        while (scanner.hasNext())
            contentsOfFile.add(scanner.nextLine());


        boolean isDeleted = false;
        for (int i = 0; i < contentsOfFile.size() && isDeleted == false; i++) {
            int start = 3;
            int end = contentsOfFile.get(i).indexOf(" ");
            String temp = contentsOfFile.get(i).substring(start, end);
            int checkid = Integer.valueOf(temp);
            if (checkid == id) {
                contentsOfFile.remove(i);
                isDeleted = true;
            }
        }

        if (isDeleted) {
            FileWriter fileWriter = new FileWriter(getUserFile().getPath());
            fileWriter.write("");
            fileWriter.close();

            FileWriter fWriterWithAppend = new FileWriter(getUserFile().getPath(), true);
            for (int i = 0; i < contentsOfFile.size(); i++)
                fWriterWithAppend.write(contentsOfFile.get(i) + "\n");

            fWriterWithAppend.close();
            System.out.println("user deleted");
            System.out.println("-----------");
            return;
        } else {
            System.out.println("user not found");
            System.out.println("-----------");
        }
    }

    public void printInformationOfUser(int id) throws FileNotFoundException {
        Scanner scanner = new Scanner(this.getUserFile());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            boolean checkId = checkIdOfUser(line, id);
            if (checkId) {
                int start = 0;
                int end = line.indexOf("password:");
                String informationOfUser = line.substring(start, end - 1);
                System.out.println(informationOfUser);
                System.out.println("-----------");
                return;
            }
        }
        System.out.println("user not found");
        System.out.println("-----------");

    }

    public void printInformationOfAllUsers() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.getUserFile());

        if (scanner.hasNext() == false) {
            System.out.println("no user exists");
            return;
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int start = 0;
            int end = line.indexOf("password:");
            String informationOfUser = line.substring(start, end - 1);
            System.out.println(informationOfUser);
        }
        System.out.println("-----------");
    }

    private boolean isEmailDuplicate(String email) throws FileNotFoundException {
        Scanner scanner = new Scanner(getUserFile());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int start = line.indexOf("email:") + "email:".length();
            int end = line.indexOf(" ", start);
            String extractedEmail = line.substring(start, end);
            if (email.equals(extractedEmail))
                return true;
        }
        return false;
    }

    public void writeID() throws IOException {
        FileWriter fileWriter = new FileWriter("Information/user_id.txt");
        fileWriter.write(String.valueOf(user_id));
        fileWriter.close();
    }

    public boolean checkIdOfUser(String str, int id) {
        int start = 3;
        int end = str.indexOf(" ");
        String temp = str.substring(start, end);
        int checkid = Integer.valueOf(temp);
        if (checkid == id)
            return true;
        return false;
    }


    public List<String> getNameWithI() throws FileNotFoundException {
        Scanner scanner = new Scanner(userFile);
        List<String> temp = new ArrayList<>();
        List<String> list = new ArrayList<>();
        while (scanner.hasNext())
            temp.add(scanner.nextLine());


        for (int i = 0; i < temp.size(); i++) {
            int start = temp.get(i).indexOf("Username:") + "Username:".length();
            int end = temp.get(i).indexOf(" ", start);
            list.add(temp.get(i).substring(start, end));
        }

        return list.stream()
                .filter(a -> a.contains("i"))
                .toList();

    }


    public File getUserFile() {
        return userFile;
    }
}

