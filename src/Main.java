import co.edu.uptc.model.FileExplorer;
import co.edu.uptc.presenter.Runner;
import co.edu.uptc.exceptions.InvalidRouteException;
import co.edu.uptc.util.ValidateFilePath;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ValidateFilePath validator = new ValidateFilePath();
        try {
            validator.validateNotNull(args);
            File directory = new File(args[0]);
            Runner runner = new Runner(directory);
            runner.start();
        }catch (InvalidRouteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
