package com.ae2dms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * class MainController {@link SettingController} is the Controller of MVC pattern. It's used for maintenance.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <p>Its Model is Class {@link com.ae2dms.model.GameSetting}. Its View is Setting.fxml.
 * It is used to receive the radio button action from user. And show game character to user. </p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class SettingController {
    /**
     * Radio button to check user theme choosing type.
     */
    public RadioButton theme1;
    /**
     * Radio button to check user theme choosing type.
     */
    public RadioButton theme2;

    /**
     * Flag to tag user choose which type of game character.
     */
    public static boolean settingFlag = true;
    /**
     * img1 to show game character.
     */
    public ImageView img1;
    /**
     * img2 to show game character.
     */
    public ImageView img2;
    /**
     * img3 to show game character.
     */
    public ImageView img3;
    /**
     * img4 to show game character.
     */
    public ImageView img4;
    /**
     * img5 to show game character.
     */
    public ImageView img5;
    /**
     * img6 to show game character.
     */
    public ImageView img6;
    /**
     * Button to close setting page.
     */
    public Button backToHome;

    /**
     * Method initialize() is extension.
     *
     * <p>Set default option for user, Choosing theme 1. If user click on bak to home button, go to main page.</p>
     *
     * @since 2020/10/25
     */
    @FXML
    private void initialize() {
        theme1.setSelected(true);
        settingFlag = true;
        backToHome.setOnAction(event -> ((Stage) (backToHome.getScene().getWindow())).close());
    }

    /**
     * Method radioButtonOnClicked() is extension.
     *
     * <p>Check which type of theme is chosen.</p>
     *
     * @since 2020/10/25
     */
    public void radioButtonOnClicked() {
        if(theme1.isSelected()) {
            theme2.setSelected(false);
            settingFlag = true;
        }
        else if(theme2.isSelected()){
            theme1.setSelected(false);
            settingFlag = false;
        }
    }

    /**
     * Method showSetting() is extension.
     *
     * <p>show specific game character image.</p>
     *
     * @since 2020/10/25
     */
    public void showSetting() {
        Image image1 = new Image("image/player.png");
        img1.setImage(image1);
        Image image2 = new Image("image/box.jpg");
        img2.setImage(image2);
        Image image3 = new Image("image/target.png");
        img3.setImage(image3);
        Image image4 = new Image("theme2/player.png");
        img4.setImage(image4);
        Image image5 = new Image("theme2/arrow.png");
        img5.setImage(image5);
        Image image6 = new Image("theme2/bomb.png");
        img6.setImage(image6);
    }
}
