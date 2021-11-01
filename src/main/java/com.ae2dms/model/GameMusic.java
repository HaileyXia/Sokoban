package com.ae2dms.model;

import com.ae2dms.controller.MainController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class GameMusic {@link GameMusic} is extension and maintenance using Singleton Pattern
 *
 * <p>Singleton Pattern involves a single class that is responsible for creating its own objects while ensuring that only a single object is created.
 * This class provides a way to access its unique objects directly without instantiating the objects of the class.</p>
 *
 * <p>Create only single object while playing MediaPlayer. </p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameMusic {
    private MediaPlayer player;
    private MediaPlayer cratePlayer;
    private MediaPlayer startPlayer;
    private MediaPlayer blowPlayer;
    private MediaPlayer victoryPlayer;
    private static boolean flag = false;
    /**
     * Create an object for SingleObject
     */
    private static GameMusic instance = new GameMusic();

    /**
     * Method GameMusic() is extension.
     *
     * <p>Make the constructor private so that the class is not instantiated.</p>
     *
     * @since 2020/10/25
     */
    private GameMusic(){
    }

    /**
     * Method getInstance() is extension.
     *
     * <p>Gets the only available object.</p>
     *
     * @return available object GameMusic
     * @since 2020/10/25
     */
    public static GameMusic getInstance(){
        return instance;
    }

    /**
     * Method toggleMusic() is extension.
     *
     * <p>Play background music.</p>
     *
     * @since 2020/10/25
     */
    public void toggleMusic(){
        flag = !flag;
        if(flag == true){
            Media media = new Media(getClass().getResource("/music/backgroundMusic.wav").toString());
            player = new MediaPlayer(media);
            player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
            player.play();
        }
        else{
            player.pause();
        }
    }

    /**
     * Method crateMusic() is extension.
     *
     * <p>Set music effect when keeper pushing crate.</p>
     *
     * @since 2020/10/25
     */
    public void crateMusic(){
        Media media = new Media(getClass().getResource("/music/crate.wav").toString());
        cratePlayer = new MediaPlayer(media);
        cratePlayer.play();
    }

    /**
     * Method startMusic() is extension.
     *
     * <p>Play start music.</p>
     *
     * @since 2020/10/25
     */
    public void startMusic(){
        MainController.startMusicFlag = !MainController.startMusicFlag;
        if(MainController.startMusicFlag == true){
            Media media = new Media(getClass().getResource("/music/startMusic.wav").toString());
            startPlayer = new MediaPlayer(media);
            startPlayer.setOnEndOfMedia(() -> startPlayer.seek(Duration.ZERO));
            startPlayer.play();
        }
        else{
            startPlayer.pause();
        }
    }

    /**
     * Method blowMusic() is extension.
     *
     * <p>Set music effect when blowing the walls.</p>
     *
     * @since 2020/10/25
     */
    public void blowMusic(){
        Media media = new Media(getClass().getResource("/music/blow.wav").toString());
        blowPlayer = new MediaPlayer(media);
        blowPlayer.play();
    }

    /**
     * Method victoryMusic() is extension.
     *
     * <p>Set music effect when complete level.</p>
     *
     * @since 2020/10/25
     */
    public void victoryMusic(){
        Media media = new Media(getClass().getResource("/music/victory.wav").toString());
        victoryPlayer = new MediaPlayer(media);
        victoryPlayer.play();
    }

    /**
     * Method getStartPlayer() is extension.
     *
     * <p>Get MediaPlayer of Start Music.</p>
     *
     * @return MediaPlayer of Start Music
     * @since 2020/10/25
     */
    public MediaPlayer getStartPlayer(){
        return startPlayer;
    }
}
