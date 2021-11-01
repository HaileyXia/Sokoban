package com.ae2dms.model;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class {@link ThemeObject} is extension.
 *
 * <p>ThemeObject is used to populate the game grid. It extends {@link ImageView}.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class ThemeObject extends ImageView {

    /**
     * Method ThemeObject(GameObject obj) is extension.
     *
     * <p>Show different image depends on different GameObject. The theme of this set of image is cat disarm bombs.</p>
     *
     * @param obj GameObject from class {@link GameObject}
     * @since 2020/10/25
     */
    public ThemeObject(GameObject obj) {
        switch (obj) {
            case WALL:
                setImageFor(ImageLoader.getThemeWall());
                break;

            case CRATE:
                setImageFor(ImageLoader.getThemeBox());
                break;

            case DIAMOND:
                setImageFor(ImageLoader.getThemeTarget());
                if (GameEngine.isDebugActive()) {
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.2);
                    ft.setCycleCount(Timeline.INDEFINITE);
                    ft.setAutoReverse(true);
                    ft.play();
                }
                break;

            case KEEPER:
                if ((GameEngine.upFlag == true)||(GameEngine.rightFlag == true)){
                    setImageFor(ImageLoader.getThemePlayerR());
                    GameEngine.rightFlag = false;
                    GameEngine.upFlag = false;
                }else {
                    setImageFor(ImageLoader.getThemePlayer());
                }
                break;

            case FLOOR:
                setImageFor(ImageLoader.getThemeFloor());
                break;

            case CRATE_ON_DIAMOND:
                setImageFor(ImageLoader.getThemeCrateOnDiamond());
                break;

            default:
                String message = "Error in Level constructor. Object not recognized.";
                GameEngine.logger.severe(message);
                throw new AssertionError(message);
        }
    }

    /**
     * Method setImageFor(Image imageWall) is extension.
     *
     * <p>Set image with fix height and width.</p>
     *
     * @param themeWall Image to be set size.
     * @since 2020/10/25
     */
    private void setImageFor(Image themeWall) {
        setImage(themeWall);
        setFitHeight(30);
        setFitWidth(30);
    }
}
