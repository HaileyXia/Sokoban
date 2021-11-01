package com.ae2dms.model;

import com.ae2dms.controller.RankController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class {@link GameRank} is extension as a MVC pattern, which extends {@link Application}
 *
 * <p>class {@link GameRank}is a Model of a MVC pattern. Its Controller is Class {@link com.ae2dms.controller.RankController}.
 * Its View is GameRank.fxml. It Updates the controller as data changes. </p>
 *
 * <p>{@link GameRank} is used to show the overall ranking list.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameRank extends Application {

    /**
     * Method start(Stage primaryStage) is extension.
     *
     * <p>Use GameRank.fxml page to load ranking page.</p>
     *
     * @param primaryStage javaFX uses Stage box to load page.
     * @throws Exception In case it needs to throw exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/GameRank.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle(GameEngine.GAME_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        RankController rankController = loader.getController();
        rankController.levelToTextArea();

    }
}
