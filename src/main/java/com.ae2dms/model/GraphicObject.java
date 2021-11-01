package com.ae2dms.model;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class {@link GraphicObject} is extension and maintenance.
 *
 * <p>GraphicObject is used to populate the game grid. It extends {@link ImageView}.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GraphicObject extends ImageView{
    /**
     * Method GraphicObject(GameObject obj) is extension and maintenance.
     *
     * <p>Show different image depends on different GameObject. Modify: if keeper move up, left, right, down, it will show different image.</p>
     *
     * @param obj GameObject from class {@link GameObject}
     * @since 2020/10/25
     */
    public GraphicObject(GameObject obj)  {
        switch (obj) {
            case WALL:
                setImageFor(ImageLoader.getImageWall());
                break;

            case CRATE:
                setImageFor(ImageLoader.getImageBox());
                break;

            case DIAMOND:
                setImageFor(ImageLoader.getImageTarget());

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
                if (GameEngine.leftFlag == true){
                    setImageFor(ImageLoader.getImagePlayerL());
                    GameEngine.leftFlag = false;
                }else if(GameEngine.rightFlag == true){
                    setImageFor(ImageLoader.getImagePlayerR());
                    GameEngine.rightFlag = false;
                }else if(GameEngine.upFlag == true){
                    setImageFor(ImageLoader.getImagePlayerU());
                    GameEngine.upFlag = false;
                }else {
                    setImageFor(ImageLoader.getImagePlayer());
                }
                break;

            case FLOOR:
                setImageFor(ImageLoader.getImageFloor());
                break;

            case CRATE_ON_DIAMOND:
                setImageFor(ImageLoader.getImageCrateOnDiamond());
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
     * @param imageWall Image to be set size.
     * @since 2020/10/25
     */
    private void setImageFor(Image imageWall) {
        setImage(imageWall);
        setFitHeight(30);
        setFitWidth(30);
    }
}

