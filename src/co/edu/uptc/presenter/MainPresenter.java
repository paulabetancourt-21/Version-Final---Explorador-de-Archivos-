package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import java.io.File;
import java.util.List;

public class MainPresenter implements PresenterInterface{
    private ViewInterface view; 
    private ModelInterface model;

    @Override
    public void setView(ViewInterface view) {
        this.view = view; 
    }

    @Override
    public void setModel(ModelInterface model) {
        this.model = model; 
    }

    public void searchArchiveParameters(){
        String nameFileOrFilter = view.readData("Ingrese el nombre de un archivo o un comodín: ");
        List<File> findFiles = model.findFiles(nameFileOrFilter.toLowerCase().trim());
        view.showMessage(view.showListInformation(findFiles));
    }

    public void rootFolderSize(){
        view.showMessage("El tamaño del directorio principal es de: " + model.rootFolderSize() + " bytes");
    }

    public void listSpecificDirectory(){
        String directory = view.readData("Ingrese el nombre de una carpeta para ver su contenido: ").toLowerCase().trim();
        List<File> listDirectory;
        listDirectory = model.listDirectoryContents(directory);
        view.showMessage(view.showListInformation(listDirectory));
    }

    public void deleteFile(){
        String fileDelete;
        fileDelete = view.readData("Ingrese el nombre del archivo o carpeta que quiere eliminar: ");
        if (model.deleteFile(fileDelete.toLowerCase().trim())){
            System.out.println("Archivo eliminado");
        }else{
            System.out.println("El archivo no se pudo eliminar");
        }
    }

}
