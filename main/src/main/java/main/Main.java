package main;

import controller.ControllerFacade;
import model.ModelFacade;
import view.ViewFacade;

/**
 * <h1>The Class Main.</h1>
 *
 * @author Vincent Linck
 * @version 1.0
 */
public abstract class Main {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     * @throws InterruptedException 
     */
    public static void main(final String[] args) throws InterruptedException {
    	final ModelFacade model = new ModelFacade(20, 12);
    	final ViewFacade view = new ViewFacade(model);
        final ControllerFacade controller = new ControllerFacade(view, model);

        controller.start();
    }

}