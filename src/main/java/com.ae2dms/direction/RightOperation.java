package com.ae2dms.direction;

import com.ae2dms.model.GameEngine;
import com.ae2dms.controller.StartController;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.io.IOException;

/**
 * Class RightOperation {@link RightOperation} is maintenance using Strategy Pattern.
 *
 * <p>Create an entity class that implements the interface {@link Strategy}.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class RightOperation implements Strategy{
    private StartController startController = new StartController();
    private GameEngine gameEngine = startController.getGameEngine();

    /**
     * Method Operation(KeyCode code) is extension.
     *
     * <p>Call method move from class {@link GameEngine} to move rightward.</p>
     *
     * @param code KeyCode code get from keyboard
     * @throws IOException Catch exception.
     * @since 2020/10/25
     */
    @Override
    public void Operation(KeyCode code) throws IOException {
        gameEngine.move(new Point(0,1));
    }
}
