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

    private List<String> listContents(File folder) {
        List<String> contents = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    contents.add(f.getName());
                }
            }
        }
        return contents;
    }

    public List<String> listRootContents() {
        return listContents(directoryPath);
    }

    public List<String> listDirectoryContents(String subFolderName) {
        File subFolder = new File(directoryPath, subFolderName);
        return listContents(subFolder);
    }

    public boolean deleteFile(String fileName) {
        File fileToDelete = new File(directoryPath, fileName);
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            return fileToDelete.delete();
        }
        return false;
    }

}

