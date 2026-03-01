package co.edu.uptc.interfaces;

import co.edu.uptc.exceptions.InvalidRouteException;

import java.io.File;
import java.util.List;

public interface ModelInterface {
    public List<File> findFiles(String pattern);
    public long rootFolderSize();
    public List<File> listRootContents();
    public List<File> listDirectoryContents(String subFolderName);
    public boolean deleteFile(String fileName);


}
