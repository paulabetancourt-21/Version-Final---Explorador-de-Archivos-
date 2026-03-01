package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.FileExplorer;
import co.edu.uptc.view.ConsoleView;
import java.io.File;
import co.edu.uptc.exceptions.InvalidRouteException;

public class Runner {

    private PresenterInterface presenter;
    private ModelInterface model;
    private ViewInterface view;
    private File directory;

    public Runner(File directory) {
        this.directory = directory;
    }

    private void makeMVP(){
        presenter = new MainPresenter();
        model = new FileExplorer(directory);
        view = new ConsoleView();

        presenter.setView(view);
        presenter.setModel(model);
        view.setPresenter(presenter);
    }

    public void start() throws InvalidRouteException {
        makeMVP();
        presenter.deleteFile();
        view.start();
    }
}
