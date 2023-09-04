package il.ac.Shenkar;

import il.ac.Shenkar.CostManager.Model.SimpleApp;
import il.ac.Shenkar.CostManager.View.MainApp;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        new SimpleApp().go(args);
        System.out.println("SimpleApp finished");
        System.out.println("start");
       MainApp mainGui = new MainApp();
       mainGui.start();
    }
}
