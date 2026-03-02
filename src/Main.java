import co.edu.uptc.presenter.Runner;
import co.edu.uptc.exceptions.InvalidRouteException;
import co.edu.uptc.util.ValidateFilePath;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.view.ConsoleView;

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
            ViewInterface view = new ConsoleView();
            view.showError(e.getMessage());
        }
    }
}
