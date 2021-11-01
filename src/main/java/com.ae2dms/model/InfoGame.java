package com.ae2dms.model;

import com.ae2dms.controller.InfoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class {@link InfoGame} is extension as a MVC pattern, which extends {@link Application}
 *
 * <p>class {@link InfoGame}is a Model of a MVC pattern. Its Controller is Class {@link com.ae2dms.controller.InfoController}.
 * Its View is InfoGame.fxml. It Updates the controller as data changes. </p>
 *
 * <p>{@link InfoGame} is used to show the instruction of game.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class InfoGame extends Application {

    /**
     * Method start(Stage primaryStage) is extension.
     *
     * <p>Use InfoGame.fxml page to load information page.</p>
     *
     * @param primaryStage javaFX uses Stage box to load page.
     * @throws Exception In case it needs to throw exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/InfoGame.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle(GameEngine.GAME_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        InfoController infoController = loader.getController();
        infoController.setInfo();
    }
}
