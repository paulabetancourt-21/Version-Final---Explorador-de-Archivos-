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
    public static final String ERROR_PRO = "\u001B[38;5;199;1m";

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
        System.out.println(message);
    }

    public String readData(String message){
        showMessage(message);
        String data = console.nextLine();
        return data;
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
    public void show_Error(String message) {
        showMessage(ERROR_PRO + "/***********************************/" + COLOR_RESET);
        showMessage(message);
        showMessage(ERROR_PRO + "/***********************************/" + COLOR_RESET);
    }

    public void menu(){
        int option = 0;
        do {
            menu.systemMenu();
             option = Integer.parseInt(readData("Ingrese una opción: "));
            switch (option){
                case 1 -> presenter.searchArchiveParameters();
                case 2 -> presenter.rootFolderSize();
                case 3 -> presenter.listSpecificDirectory();
                case 4 -> presenter.deleteFile();
                case 5 -> {
                    showMessage("Saliendo del sistema");
                    System.exit(0);
                }
                default -> showMessage("La opción ingresada no es valida");
            }
        }while (option!=5);
    }
}
