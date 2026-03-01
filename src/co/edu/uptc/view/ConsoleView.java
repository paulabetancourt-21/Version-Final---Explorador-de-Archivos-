package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {
    private PresenterInterface presenter;
    private Scanner console;
    private MenuCli menu;
    private static final String COLOR_DIRECTORY = "\u001B[36m";
    private static final String COLOR_FILE = "\u001B[34m";
    private static final String COLOR_RESET   = "\u001B[0m";
    public static final String ERROR_PRO = "\u001B[91;1m";
    public static final String WARNING_COLOR = "\u001B[38;5;220;1m";
    public static final String DANGER_WARNING = "\u001B[38;5;208;1m";
    public static final String INFO_COLOR = "\u001B[38;5;75m";

    public ConsoleView(){
        console = new Scanner(System.in);
        menu = new MenuCli();
    }
    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter; 
    }

    @Override
    public void start() {
        menu();
    } 

    public void showMessage(String message){
        System.out.println(INFO_COLOR + message + COLOR_RESET);
    }

    public String readData(String message){
        showMessage(message);
        String data = console.nextLine();
        return data;
    }

    public int readIntOption(String message){
        int option = 0;
        try{
        option = Integer.parseInt(readData(message));
        } catch (NumberFormatException e) {
            showError("El formato ingresado no es válido");
        }
        return option;
    }

    public String showListInformation(List<File> data) {
        String formattedData = "";
        for (File f : data) {
            if (f.isDirectory()) {
                formattedData += COLOR_DIRECTORY + "[CARPETA] " + f.getAbsolutePath() + " - Tamaño: " + f.length() + " bytes" + "\n" + COLOR_RESET;
            } else {
                formattedData += COLOR_FILE + "[ARCHIVO] " + f.getAbsolutePath() + " - Tamaño: " + f.length() + " bytes" + "\n" + COLOR_RESET;
            }
        }
        return formattedData;
    }

    @Override
    public void showError(String message) {
        showMessage(ERROR_PRO +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                "\n    ERROR: " + message +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
                + COLOR_RESET);
    }

    @Override
    public void showWarning(String message) {
        showMessage(WARNING_COLOR +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                "\n  ⚠: " + message +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
                + COLOR_RESET);
    }

    @Override
    public String showDangerWarning(String message) {
        String data = readData(DANGER_WARNING +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                "\n           ⚠⚠  ¡ESTA APUNTO DE ELIMINAR UN ARCHIVO!  ⚠⚠" +
                "\n"  + message +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
                + COLOR_RESET);
        return data;
    }


    public void menu(){
        int option = 0;
        do {
            menu.systemMenu();
             option = readIntOption("Ingrese una opción: ");
            switch (option){
                case 1 -> presenter.listDirectoryPath();
                case 2 -> presenter.searchArchiveParameters();
                case 3 -> presenter.rootFolderSize();
                case 4 -> presenter.listSpecificDirectory();
                case 5 -> presenter.deleteFile();
                case 6 -> {
                    showMessage("Gracias por usar el sistema");
                }
                default -> showWarning("Ingrese un número entre 1 y 6");
            }
        }while (option!=6);
    }
}
