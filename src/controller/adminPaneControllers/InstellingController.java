package controller.adminPaneControllers;
import model.SpelModel;
import view.panels.InstellingPane;

/**
 * @author Jowan
 */
public class InstellingController {
    private SpelModel model;
    private InstellingPane view;


    public InstellingController(SpelModel model){
        this.model = model;
    }

    public SpelModel getModel(){
        return model;
    }

    public void setView(InstellingPane view) {
        this.view = view;
    }

}
