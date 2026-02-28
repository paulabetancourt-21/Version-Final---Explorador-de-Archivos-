package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer implements ModelInterface {
    private File directoryPath;

    public FileExplorer(File directoryPath) {
        this.directoryPath = directoryPath;
    }

    public List<File> findFiles(String pattern) {
        return findRecursive(directoryPath, pattern);
    }

    private List<File> findRecursive(File folder, String pattern) {
        List<File> matchedFiles = new ArrayList<>();
        File[] listFiles = folder.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    matchedFiles.addAll(findRecursive(file, pattern));
                } else if (matchesPattern(file.getName(), pattern)) {
                    matchedFiles.add(file);
                }
            }
        }
        return matchedFiles;
    }

    private boolean matchesPattern(String fileName, String pattern) {
        return fileName.startsWith(pattern) || fileName.endsWith(pattern) || fileName.contains(pattern);
    }

    //TODO pensar, el formato no es parte de la vista?
    private String formatFileInfo(File file) {
        return "Ruta: " + file.getAbsolutePath() + " - Tamaño: " + file.length() + " bytes";
    }
}

