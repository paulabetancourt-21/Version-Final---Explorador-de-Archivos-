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

    public void searchArchiveParameters() {
        String nameFileOrFilter = view.readData("Ingrese el nombre del archivo o carpeta: completo, parcial, con extensión o solo la extensión (ej: reporte, rep, hola.txt, .txt)");
        List<File> findFiles = model.findFiles(nameFileOrFilter.toLowerCase().trim());
        if (nameFileOrFilter.equals(null) || nameFileOrFilter.trim().isEmpty()) {
            view.showWarning("Debe ingresar un nombre o comodín válido");
        }else if (findFiles.isEmpty()) {
            view.showWarning("No se encontraron coincidencias");
        } else {
            view.showMessage(view.showListInformation(findFiles));
            view.showMessage("Cantidad encontrada: " + findFiles.size());
        }
    }

    public void rootFolderSize(){
        view.showMessage("El tamaño del directorio principal es de: " + model.rootFolderSize() + " bytes" + "\n");
    }

    public void listSpecificDirectory(){
        String directory = view.readData("Ingrese el nombre de una carpeta de la ruta principal para ver su contenido: ").toLowerCase().trim();
        List<File> listDirectory = model.listDirectoryContents(directory);
        if(listDirectory.isEmpty()){
            view.showWarning("No se encontro información de la carpeta " + directory);
        }else{
            view.showMessage(view.showListInformation(listDirectory));
        }
    }

    private boolean confirmDeletion() {
        while (true) {
            String option = view.showDangerWarning(
                    "¿Desea eliminar permanentemente este archivo? (SI / NO): "
            ).trim().toLowerCase();
            if (option.equals("si")) return true;
            if (option.equals("no")) return false;
            view.showWarning("Respuesta inválida. Ingrese únicamente SI o NO.");
        }
    }

    public void deleteFile() {
        String fileDelete = view.readData(
                "Ingrese el nombre del archivo o carpeta que quiere eliminar del directorio principal: ");
        if (!model.exists(fileDelete)) {
            view.showWarning("El archivo o carpeta ingresado no existe");
        }else if (confirmDeletion()) {
            model.deleteFile(fileDelete);
            view.showMessage("Archivo eliminado correctamente");
        } else {
            view.showMessage("Operación cancelada");
        }
    }
}
