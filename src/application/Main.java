package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
    private static final int WINSIZE_X = 800, WINSIZE_Y = 600;
    private final String WINTITLE = "Game";
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception
    {
    	Menu rootPane = new Menu();
        rootPane.setPrefSize(WINSIZE_X, WINSIZE_Y);
        Scene scene = new Scene(rootPane, WINSIZE_X, WINSIZE_Y, Color.BLACK);
        stage.setTitle(WINTITLE);
        stage.setScene(scene);
        stage.show();
    }

    public void setScene(Scene scene){
    	stage.setScene(scene);
    }

    /**
     * Technically this is not needed for JavaFX applications. Added just in case.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}