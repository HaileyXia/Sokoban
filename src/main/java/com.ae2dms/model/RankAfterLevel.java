package com.ae2dms.model;

import com.ae2dms.controller.RankAfterLevelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class {@link RankAfterLevel} is extension as a MVC pattern, which extends {@link Application}
 *
 * <p>class {@link RankAfterLevel}is a Model of a MVC pattern. Its Controller is Class {@link com.ae2dms.controller.RankAfterLevelController}.
 * Its View is RankAfterLevel.fxml. It Updates the controller as data changes. </p>
 *
 * <p>{@link RankAfterLevel} is used to show the current level's ranking list.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class RankAfterLevel extends Application {

    /**
     * Method start(Stage primaryStage) is extension.
     *
     * <p>Use RankAfterLevel.fxml page to load current level's ranking page.</p>
     *
     * @param primaryStage javaFX uses Stage box to load page.
     * @throws Exception In case it needs to throw exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/RankAfterLevel.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle(GameEngine.GAME_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        RankAfterLevelController rankAfterLevelController = loader.getController();
        rankAfterLevelController.rankToTable();
    }

}
