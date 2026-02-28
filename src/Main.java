import co.edu.uptc.model.FileExplorer;
import co.edu.uptc.presenter.Runner;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        FileExplorer fileExplorer = new FileExplorer(directory);

        try {
            fileExplorer.validateRoute();
            System.out.println("Ruta válida");
            System.out.println("Ruta: " + args[0]);
            Runner runner = new Runner(directory);
            runner.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
