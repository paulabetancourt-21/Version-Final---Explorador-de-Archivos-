package co.edu.uptc.interfaces;

import java.io.File;
import java.util.List;

public interface ViewInterface {
    void setPresenter(PresenterInterface presenter);
    void start();
    String readData(String message);
    void showMessage(String message);
    String showListInformation(List<File> data);
    void show_Error(String message);
}
