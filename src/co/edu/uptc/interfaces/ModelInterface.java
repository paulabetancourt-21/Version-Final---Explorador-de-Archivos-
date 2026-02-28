package co.edu.uptc.interfaces;

import co.edu.uptc.exceptions.InvalidRouteException;

public interface ModelInterface {
    String exec() throws Exception;
    boolean validateRoute() throws InvalidRouteException;
    
}
