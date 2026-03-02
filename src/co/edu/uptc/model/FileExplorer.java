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

    @Override
    public List<File> findFiles(String pattern) {
        return findRecursive(directoryPath, pattern);
    }


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
    //Es carpeta y coincide con el patron?
    private void addDirectoryIfMatches(File f, String pattern, List<File> matched) {
        if (f.isDirectory() && matchesPattern(f.getName(), pattern)) {
            matched.add(f);
        }
    }
    //Es un archivo y coincide con el patron?
    private void addFileIfMatches(File f, String pattern, List<File> matched) {
        if (f.isFile() && matchesPattern(f.getName(), pattern)) {
            matched.add(f);
        }
    }
    //Entra en la carpeta a buscar el patron
    private void recurseIntoDirectory(File f, String pattern, List<File> matched) {
        if (f.isDirectory()) {
            matched.addAll(findRecursive(f, pattern));
        }
    }
    //patron -> empieza, termina o contiene el patron en alguna parte
    private boolean matchesPattern(String name, String pattern) {
        String lower = name.toLowerCase();
        String pat = pattern.toLowerCase();
        return lower.startsWith(pat) || lower.endsWith(pat) || lower.contains(pat);
    }

    @Override
    public long rootFolderSize() {
        return calculateFolderSize(directoryPath);
    }

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

    @Override
    public List<File> listDirectoryPath(){
        return listContents(directoryPath);
    }

   @Override
    public List<File> listDirectoryContents(String subFolderName) {
        File subFolder = new File(directoryPath, subFolderName);
        return listContents(subFolder);
    }

    @Override
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

    @Override
    public boolean exists(String fileName) {
        File file = new File(directoryPath, fileName);
        return file.exists();
    }


}

