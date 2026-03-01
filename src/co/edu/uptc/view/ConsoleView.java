package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import java.io.File;
import java.util.List;
import java.util.Scanner;


public class ConsoleView implements ViewInterface {
    private PresenterInterface presenter;
    private Scanner console;
    private static final String COLOR_DIRECTORY = "\u001B[32m";
    private static final String COLOR_FILE = "\u001B[34m";
    private static final String COLOR_RESET   = "\u001B[0m";

    public ConsoleView(){
        console = new Scanner(System.in);
    }
    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter; 
    }

    @Override
    public void start() {
    } 

    public void showMessage(String message){
        System.out.println(message);
    }

    public String readData(String message){
        showMessage(message);
        String data = console.nextLine();
        return data;
    }

    //FORMATEA LA INFROMACIÓN PROPORCIONADA
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


}
