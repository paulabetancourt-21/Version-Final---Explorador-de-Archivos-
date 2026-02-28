package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import java.io.File;
import co.edu.uptc.exceptions.InvalidRouteException;


public class FileExplorer implements ModelInterface{
    private File directoryPath;

    public FileExplorer(File directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String exec() throws Exception {
        return ""; 
    }

    @Override
    public boolean validateRoute() throws InvalidRouteException {
        if (!directoryPath.exists()) {
            throw new InvalidRouteException("La ruta ingresada no es valida");
        }
        return true;
    }

}
