package com.ae2dms.direction;

import javafx.scene.input.KeyCode;

import java.io.IOException;

/**
 * Interface {@link Strategy} is extension of Strategy Pattern.
 *
 * <p>Create a Strategy interface that defines the activity and an entity Strategy class that implements the Strategy interface.
 * A Context{@link Context} is a class that use some kind of policy.</p>
 *
 * <p>Create an interface.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public interface Strategy {
    /**
     * Method Operation(KeyCode code) is maintenance using Strategy Pattern.
     *
     * <p>Operation Method apply on interface.</p>
     *
     * @param code KeyCode code get from keyboard
     * @throws IOException Catch exception
     * @since 2020/10/25
     */
    public void Operation(KeyCode code) throws IOException;
}
