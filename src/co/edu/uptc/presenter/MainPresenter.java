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
        if (findFiles.isEmpty()){
            view.showMessage("No se encontraron coincidencias");
        }else {
            view.showMessage(view.showListInformation(findFiles));
            view.showMessage("Cantidad encontrada: " + findFiles.size());
        }
    }

    public void rootFolderSize(){
        view.showMessage("El tamaño del directorio principal es de: " + model.rootFolderSize() + " bytes");
    }

    public void listSpecificDirectory(){
        String directory = view.readData("Ingrese el nombre de una carpeta para ver su contenido: ").toLowerCase().trim();
        List<File> listDirectory = model.listDirectoryContents(directory);
        if(listDirectory.isEmpty()){
            view.showMessage("No se encontro información de la carpeta " + directory);
        }else{
            view.showMessage(view.showListInformation(listDirectory));
        }
    }

    public void deleteFile(){
        String fileDelete;
        fileDelete = view.readData("Ingrese el nombre del archivo o carpeta que quiere eliminar: ");
        if (model.exists(fileDelete)){
            String option = view.readData("¿Desea eliminar permanentemente este archivo? SI / NO");
            if (option.equalsIgnoreCase("si".trim())){
                model.deleteFile(fileDelete);
                view.showMessage("Archivo eliminado correctamente");
            }else {
                view.showMessage("Operación cancelada");
            }
        }else {
            view.showMessage("El archivo o carpeta ingresado no existe");
        }
    }
}
