package com.ae2dms.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class {@link ImageLoader} is extension, extends Class {@link ImageView}
 *
 * <p>Load image.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class ImageLoader extends ImageView
{
    private static Image imageBox = new Image("image/box.jpg");
    private static Image imageTarget = new Image("image/target.png");
    private static Image imagePlayer = new Image("image/player.png");
    private static Image imageFloor = new Image("image/floor.jpg");
    private static Image imageWall = new Image("image/wall.jpg");
    private static Image imageCrateOnDiamond = new Image("image/boxP.png");
    private static Image imagePlayerL = new Image("image/CharacterL.png");
    private static Image imagePlayerR = new Image("image/CharacterR.png");
    private static Image imagePlayerU = new Image("image/CharacterU.png");

    private static Image themePlayer = new Image("theme2/player.png");
    private static Image themeTarget = new Image("theme2/bomb.png");
    private static Image themeFloor = new Image("theme2/grass.png");
    private static Image themeWall = new Image("theme2/tree.png");
    private static Image themeBox = new Image("theme2/arrow.png");
    private static Image themeCrateOnDiamond = new Image("theme2/null.png");
    private static Image themePlayerR = new Image("theme2/playerR.jpg");

    /**
     * Method getImageBox() is extension.
     *
     * <p>Get Crate image.</p>
     *
     * @return Crate image
     * @since 2020/10/25
     */
    public static Image getImageBox()
    {
        return imageBox;
    }

    /**
     * Method getImageTarget() is extension.
     *
     * <p>Get Target image.</p>
     *
     * @return Target image
     * @since 2020/10/25
     */
    public static Image getImageTarget()
    {
        return imageTarget;
    }

    /**
     * Method getImagePlayer() is extension.
     *
     * <p>Get player image.</p>
     *
     * @return player image
     * @since 2020/10/25
     */
    public static Image getImagePlayer()
    {
        return imagePlayer;
    }

    /**
     * Method getImageFloor() is extension.
     *
     * <p>Get floor image.</p>
     *
     * @return floor image
     * @since 2020/10/25
     */
    public static Image getImageFloor()
    {
        return imageFloor;
    }

    /**
     * Method getImageWall() is extension.
     *
     * <p>Get wall image.</p>
     *
     * @return wall image
     * @since 2020/10/25
     */
    public static Image getImageWall() { return imageWall; }

    /**
     * Method getImageCrateOnDiamond() is extension.
     *
     * <p>Get CrateOnDiamond image.</p>
     *
     * @return CrateOnDiamond image
     * @since 2020/10/25
     */
    public static Image getImageCrateOnDiamond()
    {
        return imageCrateOnDiamond;
    }

    /**
     * Method getImagePlayerL() is extension.
     *
     * <p>Get Left player image.</p>
     *
     * @return Left player image
     * @since 2020/10/25
     */
    public static Image getImagePlayerL() { return imagePlayerL; }

    /**
     * Method getImagePlayerR() is extension.
     *
     * <p>Get Right player image.</p>
     *
     * @return Right player image
     * @since 2020/10/25
     */
    public static Image getImagePlayerR() { return imagePlayerR; }

    /**
     * Method getImagePlayerU() is extension.
     *
     * <p>Get up player image.</p>
     *
     * @return up player image
     * @since 2020/10/25
     */
    public static Image getImagePlayerU() { return imagePlayerU; }

    /**
     * Method getThemePlayer() is extension.
     *
     * <p>Get player image.</p>
     *
     * @return player image
     * @since 2020/10/25
     */
    public static Image getThemePlayer()
    {
        return themePlayer;
    }

    /**
     * Method getThemePlayerR() is extension.
     *
     * <p>Get right player image.</p>
     *
     * @return right player image
     * @since 2020/10/25
     */
    public static Image getThemePlayerR()
    {
        return themePlayerR;
    }

    /**
     * Method getThemeFloor() is extension.
     *
     * <p>Get floor image.</p>
     *
     * @return floor image
     * @since 2020/10/25
     */
    public static Image getThemeFloor()
    {
        return themeFloor;
    }

    /**
     * Method getThemeTarget() is extension.
     *
     * <p>Get target image.</p>
     *
     * @return target image
     * @since 2020/10/25
     */
    public static Image getThemeTarget() { return themeTarget; }

    /**
     * Method getThemeBox() is extension.
     *
     * <p>Get crate image.</p>
     *
     * @return crate image
     * @since 2020/10/25
     */
    public static Image getThemeBox() { return themeBox; }

    /**
     * Method getThemeWall() is extension.
     *
     * <p>Get wall image.</p>
     *
     * @return wall image
     * @since 2020/10/25
     */
    public static Image getThemeWall() { return themeWall; }

    /**
     * Method getThemeCrateOnDiamond() is extension.
     *
     * <p>Get CrateOnDiamond image.</p>
     *
     * @return CrateOnDiamond image
     * @since 2020/10/25
     */
    public static Image getThemeCrateOnDiamond() { return  themeCrateOnDiamond; }
}
