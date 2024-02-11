package Controller;

import Controller.ControlOfTerminal;
import Model.Book;
import Model.History;
import Model.Lending;
import Model.User;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Book book = new Book();
        History history = new History();
        Lending lending = new Lending(user , book , history);
        try {
            ControlOfTerminal controlOfTerminal = new ControlOfTerminal(user , book , lending , history);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
