package co.edu.uptc.util;

import co.edu.uptc.exceptions.InvalidRouteException;
import java.io.File;

public class ValidateFilePath {

    public void validate(File directory) throws InvalidRouteException {
        validateNotEmpty(directory);
        validateExists(directory);
        validateDirectory(directory);
    }

    public void validateNotNull(String[] args) throws InvalidRouteException {
        if (args == null || args.length == 0) {
            throw new InvalidRouteException("Debe ingresar una ruta como parámetro al ejecutar el programa."
            );
        }
        File directory = new File(args[0]);
        validate(directory);
    }

    private void validateNotEmpty(File directory) throws InvalidRouteException {
        if (directory.getPath().trim().isEmpty()) {
            throw new InvalidRouteException("La ruta ingresada está vacía o contiene solo espacios en blanco."
            );
        }
    }

    private void validateExists(File directory) throws InvalidRouteException {
        if (!directory.exists()) {
            throw new InvalidRouteException("La ruta '" + directory.getPath() + "' no existe en el sistema."
            );
        }
    }

    private void validateDirectory(File directory) throws InvalidRouteException {
        if (!directory.isDirectory()) {
            throw new InvalidRouteException("La ruta '" + directory.getPath() + "' existe, pero no es un directorio."
            );
        }
    }
}
