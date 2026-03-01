package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer implements ModelInterface {
    private File directoryPath;

    //Constructor
    public FileExplorer(File directoryPath) {
        this.directoryPath = directoryPath;
    }

    public List<File> findFiles(String pattern) {
        return findRecursive(directoryPath, pattern);
    }
//TODO PONER COMENTARIOS, POR QUE ESTOS METODOS SI ESTAN COMPLEJOS DE ENTENDER
    private List<File> findRecursive(File folder, String pattern) {
        List<File> matched = new ArrayList<>();
        File[] list = folder.listFiles();
        if (list != null) {
            for (File f : list) {
                addDirectoryIfMatches(f, pattern, matched);
                addFileIfMatches(f, pattern, matched);
                recurseIntoDirectory(f, pattern, matched);
            }
        }
        return matched;
    }

    private void addDirectoryIfMatches(File f, String pattern, List<File> matched) {
        if (f.isDirectory() && matchesPattern(f.getName(), pattern)) {
            matched.add(f);
        }
    }

    private void addFileIfMatches(File f, String pattern, List<File> matched) {
        if (f.isFile() && matchesPattern(f.getName(), pattern)) {
            matched.add(f);
        }
    }

    private void recurseIntoDirectory(File f, String pattern, List<File> matched) {
        if (f.isDirectory()) {
            matched.addAll(findRecursive(f, pattern));
        }
    }

    private boolean matchesPattern(String name, String pattern) {
        String lower = name.toLowerCase();
        String pat = pattern.toLowerCase();
        return lower.startsWith(pat) || lower.endsWith(pat) || lower.contains(pat);
    }


    //tamaño de la carpeta carpeta principal
    public long rootFolderSize() {
        return calculateFolderSize(directoryPath);
    }

    //metodo privado para calcular el tamaño del directorio y devuelve el peso en bytes
    private long calculateFolderSize(File folder) {
        long totalSize = 0;
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                totalSize += calculateFolderSize(file);
            } else {
                totalSize += file.length();
            }
        }
        return totalSize;
    }

    // Listar los contenidos de cualquier carpeta y devolverlos como objetos File
    private List<File> listContents(File folder) {
        List<File> contents = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    contents.add(f);
                }
            }
        }
        return contents;
    }

    // Listar los contenidos de la carpeta principal
    public List<File> listRootContents() {
        return listContents(directoryPath);
    }

    // Listar los contenidos de un subdirectorio
    public List<File> listDirectoryContents(String subFolderName) {
        File subFolder = new File(directoryPath, subFolderName);
        return listContents(subFolder);
    }

    //Eliminar un archivo en especifico -> de la carpeta principal o de toda la carpeta?
    public boolean deleteFile(String fileName) {
        File fileToDelete = new File(directoryPath, fileName);
        return deleteFileRecursive(fileToDelete);
    }

    private boolean deleteFileRecursive(File file) {
        if (!file.exists()) return false;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFileRecursive(f);
            }
        }
        return file.delete();
    }




}

