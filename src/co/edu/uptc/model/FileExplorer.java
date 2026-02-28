package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import java.io.File;

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
    public boolean validateRoute() throws Exception {
        boolean flag = false;
        if (!directoryPath.exists()){
            throw new Exception("La ruta ingresada no es valida");
        }else {
            flag = directoryPath.exists();
        }
        return flag;
    }

}
