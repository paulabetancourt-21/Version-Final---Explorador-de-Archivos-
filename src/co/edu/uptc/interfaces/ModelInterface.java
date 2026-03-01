package co.edu.uptc.interfaces;

import co.edu.uptc.exceptions.InvalidRouteException;

import java.io.File;
import java.util.List;

public interface ModelInterface {
    List<File> findFiles(String pattern);
    long rootFolderSize();
    List<File> listRootContents();
    List<File> listDirectoryContents(String subFolderName);
    boolean deleteFile(String fileName);
    boolean exists(String fileName);
    List<File> listDirectoryPath();


}
