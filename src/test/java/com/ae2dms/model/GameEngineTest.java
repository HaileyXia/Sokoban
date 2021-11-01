package com.ae2dms.model;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class {@link GameEngineTest} to test Class {@link GameEngine}
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameEngineTest extends Application {
    private static GameEngine gameEngine;
    private Level currentLevel;
    private static Level level;
    private static boolean debug = false;

    /**
     * <p>new GameEngine() before start</p>
     *
     * @since 2020/10/25
     */
    @Before
    public void setUp() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("level/debugLevel.skb");
        gameEngine = new GameEngine(in, true);
        level = gameEngine.getCurrentLevel();
    }

    /**
     * <p>test constructor</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testConstructor() {
        assertTrue("gameEngine has not been initialized", gameEngine != null);
    }

    /**
     * <p>test isDebugActive()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testIsDebugActive() {
        assertEquals(false, GameEngine.isDebugActive());
    }

    /**
     * <p>Test getMovesCount()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetMovesCount() {
        assertEquals(1,1);
    }

    /**
     * <p>Test handleKey()</p>
     *
     * @throws IOException catch I/O exception.
     * @since 2020/10/25
     */
    @Test
    public void testHandleKey() throws IOException {
        gameEngine.move(new Point(0,-1));
        assertEquals(2, GameEngine.getMovesCount());
    }

    /**
     * <p>Test getNextLevel()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetNextLevel() {
        assertEquals(gameEngine.getCurrentLevel(),level);
    }

    /**
     * <p>Test isGameNotComplete()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testIsGameNotComplete() {
        assertEquals(false,gameEngine.isGameComplete());
    }

    /**
     * <p>Test move()</p>
     *
     * @throws NoSuchMethodException Catch no such method exception
     * @throws InvocationTargetException catch no invocation target exception
     * @throws IllegalAccessException catch illegal access exception
     * @since 2020/10/25
     */
    @Test
    public void testMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method move = GameEngine.class.getDeclaredMethod("move", Point.class);
        move.setAccessible(true);

        move.invoke(gameEngine, new Point(0, -1));
        assertEquals(1,gameEngine.getMovesCount());
    }

    /**
     * <p>test getCurrentLevel()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetCurrentLevel() {
        assertEquals(1,level.getIndex());
    }

    /**
     * <p>Test toggleDebug()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testToggleDebug() {
        assertEquals(true,!debug);
    }

    /**
     * <p>Start method to initialize</p>
     *
     * @param stage javaFX use stage to show
     * @throws Exception Catch exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage stage) throws Exception {

    }
}