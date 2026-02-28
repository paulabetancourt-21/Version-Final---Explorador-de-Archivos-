import co.edu.uptc.model.FileExplorer;
import co.edu.uptc.presenter.Runner;
import co.edu.uptc.exceptions.InvalidRouteException;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File directory = new File(args[0]);
            Runner runner = new Runner(directory);
            runner.start();
            System.out.println("La ruta ingresada es válida");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Debe ingresar una ruta como parametro para que el sistema pueda continuar");
        }catch (InvalidRouteException e) {
            System.out.println(e.getMessage());
        }


    }
}
