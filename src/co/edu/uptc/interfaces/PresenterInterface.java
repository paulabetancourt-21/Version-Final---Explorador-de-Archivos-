package co.edu.uptc.interfaces;

import co.edu.uptc.exceptions.InvalidRouteException;

public interface PresenterInterface {
    void setView(ViewInterface view); 
    void setModel(ModelInterface model);
    void searchArchiveParameters();
    void rootFolderSize();
    void listSpecificDirectory();
    void deleteFile();
    void listDirectoryPath();
}
