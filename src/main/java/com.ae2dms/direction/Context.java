package com.ae2dms.direction;

import javafx.scene.input.KeyCode;

import java.io.IOException;

/**
 * Class Context(){@link Context} is extension of Strategy Pattern.
 *
 * <p>Create Context Class.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class Context {
    private Strategy strategy;

    /**
     * Method Context(Strategy strategy) is maintenance using Strategy Pattern.
     *
     * <p>Create Context(Strategy strategy) Method, which use specific strategy.</p>
     *
     * @param strategy specific strategy
     * @since 2020/10/25
     */
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * Method executeStrategy(KeyCode code) is extension.
     *
     * <p>Call Method Operation(code) from interface {@link Strategy}</p>
     *
     * @param code KeyCode code get from keyboard.
     * @throws IOException Catch exception
     * @since 2020/10/25
     */
    public void executeStrategy(KeyCode code) throws IOException {
        strategy.Operation(code);
    }
}
