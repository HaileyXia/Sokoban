package com.ae2dms.model;

import com.ae2dms.controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class {@link GameSetting} is extension as a MVC pattern, which extends {@link Application}
 *
 * <p>class {@link GameSetting}is a Model of a MVC pattern. Its Controller is Class {@link com.ae2dms.controller.SettingController}.
 * Its View is Setting.fxml. It Updates the controller as data changes. </p>
 *
 * <p>{@link GameSetting} is used to change the setting of the game.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameSetting extends Application {

    /**
     * Method start(Stage primaryStage) is extension.
     *
     * <p>Use Setting.fxml page to load change game character page.</p>
     *
     * @param primaryStage javaFX uses Stage box to load page.
     * @throws Exception In case it needs to throw exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Setting.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle(GameEngine.GAME_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        SettingController settingController = loader.getController();
        settingController.showSetting();
    }
}
