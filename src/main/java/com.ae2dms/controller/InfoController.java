package com.ae2dms.controller;

import com.ae2dms.model.InfoGame;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class InfoController {@link InfoController} is the Controller of MVC pattern.
 * It's used for maintenance of extension class{@link InfoGame}.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <p>Its Model is Class {@link InfoGame}. Its View is InfoGame.fxml.
 * Class InfoController is used to receive input text message on how to play Sokoban in each step.
 * And show the result through class {@link InfoGame} in label and image.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class InfoController {
    /**
     * Label to store step1.
     */
    public Label step1;
    /**
     * Label to store step2.
     */
    public Label step2;
    /**
     * Label to store step3.
     */
    public Label step3;
    /**
     * Label to store step4.
     */
    public Label step4;
    /**
     * Label to store step5.
     */
    public Label step5;
    /**
     * Label to store step6.
     */
    public Label step6;
    /**
     * Label to store step7.
     */
    public Label step7;
    /**
     * Label to store step8.
     */
    public Label step8;
    /**
     * Label to store step9.
     */
    public Label step9;
    /**
     * Label to store step10.
     */
    public Label step10;
    /**
     * Label to store step11.
     */
    public Label step11;
    /**
     * Label to store step12.
     */
    public Label step12;
    /**
     * Label to store step13.
     */
    public Label step13;
    /**
     * Label to store step14.
     */
    public Label step14;
    /**
     * Label to store step15.
     */
    public Label step15;
    /**
     * Label to store step16.
     */
    public Label step16;
    /**
     * ImageView to store img1.
     */
    public ImageView img1;
    /**
     * ImageView to store img2.
     */
    public ImageView img2;
    /**
     * ImageView to store img3.
     */
    public ImageView img3;
    /**
     * ImageView to store img4.
     */
    public ImageView img4;
    /**
     * ImageView to store img5.
     */
    public ImageView img5;
    /**
     * ImageView to store img6.
     */
    public ImageView img6;
    /**
     * ImageView to store img7.
     */
    public ImageView img7;
    /**
     * ImageView to store img8.
     */
    public ImageView img8;
    /**
     * ImageView to store img9.
     */
    public ImageView img9;
    /**
     * ImageView to store img10.
     */
    public ImageView img10;

    /**
     * Method setInfo() is extension.
     *
     * <p>Set message in the label and set image in the imageView, which will be displayed in INFO stage to show the instruction of game.</p>
     *
     * @since 2020/10/25
     */
    public void setInfo(){
        step1.setText("1. User need to enter the username before start.");
        step2.setText("2. User can turn on/off start music at home page.");
        step3.setText("3. Use keeper/Cat to push box/arrow to hole/bomb");
        step4.setText("4. Keeper/Cat can only walk on floor/grass but not wall/tree");
        step5.setText("5. If box/arrow is on hole/bomb, then box/arrow will become blue/null");
        step6.setText("6. If user unlock HIDDEN game, then will BLOW wall and get GOLDEN key!");
        step7.setText("7. Navigation bar show username, level, moves count and hidden GOLDEN key");
        step8.setText("8. User can undo/reset, save/load game");
        step9.setText("9. User can turn on/off background music while playing game");
        step10.setText("10. Have music effect on different action.");
        step11.setText("11. User can check Ranking List after each round.");
        step12.setText("12. About game shows basic info");
        step13.setText("13. User can choose settings at home page.");
        step14.setText("14. User can check Ranking List in navigation bar");
        step15.setText("15. *Menu bar functions can be achieved through shortcut keys. ");
        step16.setText("16. *Ranking is based on steps and time.");
        Image image1 = new Image("image/player.png");
        img1.setImage(image1);
        Image image10 = new Image("theme2/player.png");
        img10.setImage(image10);
    }
}
