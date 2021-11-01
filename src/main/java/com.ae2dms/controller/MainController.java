package com.ae2dms.controller;

import com.ae2dms.model.GameMusic;
import com.ae2dms.model.GameSetting;
import com.ae2dms.model.GameStart;
import com.ae2dms.model.InfoGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * class MainController {@link MainController} is the Controller of MVC pattern. It's used for maintenance.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <p>Its Model is Class {@link com.ae2dms.Main}. Its View is Sokoban.fxml.
 * It is used to receive the button action from user through main page. </p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class MainController implements Initializable{

    /**
     * ImageView to store mainCharacter.
     */
    public ImageView mainCharacter;
    /**
     * Change Setting button.
     */
    public Button setting;
    /**
     * Play start music.
     */
    public Button music;
    /**
     * flag to tag whether press start button or not.
     */
    private Boolean flag = false;
    /**
     * String to store username.
     */
    public static String userInfo = null;
    /**
     * flag to tag whether press music button or not.
     */
    public static Boolean startMusicFlag = false;
    /**
     * Start button.
     */
    @FXML private Button start;
    /**
     * Info button.
     */
    @FXML private Button info;
    /**
     * TextField to store username.
     */
    @FXML private TextField Username;

    /**
     * GameStart(ActionEvent actionEvent) is extension of class {@link com.ae2dms.Main}
     *
     * <p>Pass the button action to class {@link GameStart}. Pass the username to class {@link GameStart}.
     * If the start music on main page is on, then pause the music and go to start page. Catch exception.</p>
     *
     * @param actionEvent the event triggered when the button is pressed
     * @since 2020/10/25
     */
    public void GameStart(ActionEvent actionEvent){
        start.setOnAction(event -> {
            if(actionEvent.getSource() == start){
                flag = true;
            }
        });
        GameStart gameStart = new GameStart();
        try {
            userInfo = Username.getText();
            gameStart.start(new Stage());
            if(MainController.startMusicFlag == true){
                GameMusic object = GameMusic.getInstance();
                MediaPlayer player = object.getStartPlayer();
                player.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * InfoGame(ActionEvent actionEvent) is extension of class {@link com.ae2dms.Main}
     *
     * <p>Pass the button action to class {@link InfoGame}.</p>
     *
     * @param actionEvent the event triggered when the button is pressed.
     * @since 2020/10/25
     */
    public void InfoGame(ActionEvent actionEvent) {
        InfoGame infoGame = new InfoGame();
        try {
            infoGame.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GameSetting() is extension of class {@link com.ae2dms.Main}
     *
     * <p>Pass the button action to class {@link GameSetting}.</p>
     *
     * @since 2020/10/25
     */
    public void GameSetting() {
        GameSetting gameSetting = new GameSetting();
        try {
            gameSetting.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method initialize
     *
     * @param url
     * @param resourceBundle
     * @since 2020/10/25
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * startMusic() is extension of Singleton Pattern.
     *
     * <p>Use Singleton Class to call method startMusic() to play the start music.</p>
     *
     * @since 2020/10/25
     */
    public void startMusic() {
        GameMusic object = GameMusic.getInstance();
        object.startMusic();
    }

    /**
     * setImg() is extension.
     *
     * <p>Set image in the home page.</p>
     *
     * @since 2020/10/25
     */
    public void setImg(){
        Image image = new Image("image/mainCharacter.png");
        mainCharacter.setImage(image);
    }
}
