package com.ae2dms.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Class {@link LevelTest} to test Class {@link Level}
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class LevelTest {
    private static GameEngine gameEngine;
    private Method getKeeperPosition;
    private Level level;

    /**
     * <p>new GameEngine() before start</p>
     *
     * @since 2020/10/25
     */
    @BeforeEach
    public void setUp() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("level/debugLevel.skb");
        gameEngine = new GameEngine(in, true);
        level = gameEngine.getCurrentLevel();
    }

    /**
     * <p>Test constructor</p>
     *
     * @since 2020/10/25
     */
    @Before
    public void testConstructor() {
        assertTrue(level != null);
    }


    /**
     * <p>test getName()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetName() {
        String name = level.getName();
        assertEquals("Just this level",name);
    }

    /**
     * <p>Test getIndex()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetIndex() {
        assertEquals(1,level.getIndex());
    }

    /**
     * <p>test getKeeperPosition()</p>
     *
     * @throws NoSuchMethodException Catch no such method exception
     * @throws InvocationTargetException Catch invocation target exception
     * @throws IllegalAccessException Catch illegal access exception
     * @since 2020/10/25
     */
    @Test
    public void testGetKeeperPosition() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Point check = new Point(18,10);
        getKeeperPosition = Level.class.getDeclaredMethod("getKeeperPosition", null);
        getKeeperPosition.setAccessible(true);

        Point keeper = (Point) getKeeperPosition.invoke(level, null);

        assertTrue("Keeper position" + keeper + ", expected: " + check, keeper.equals(check));
    }

    /**
     * <p>Test toString()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testToString() {
        String exp = "WWWWWWWWWWWWWWWWWWWW\n" +
                "W    W             W\n" +
                "W C  W             W\n" +
                "W    W      WWWWWWWW\n" +
                "W    WWWW  WWWWWWWWW\n" +
                "W            WWWWWWW\n" +
                "W    WWWWW   WWWWWWW\n" +
                "W    WWWWWWWWWWWWWWW\n" +
                "W    WWWWWWWWWWWWWWW\n" +
                "W    WWWWWWWWWWWWWWW\n" +
                "W    WWWWWWWWWWWWWWW\n" +
                "W           WWWWWWWW\n" +
                "W         WWWWWWWWWW\n" +
                "WWWWWWW   WWWWWWWWWW\n" +
                "WWWWWWW   WWWWWWWWWW\n" +
                "WWWWWWW   WWWWWWWWWW\n" +
                "WWWWWWW   WWWWWWWWWW\n" +
                "WWWWWWW   WWWWWWWWWW\n" +
                "WWWWWWW   SWWWWWWWWW\n" +
                "WWWWWWWWWWWWWWWWWWWW\n" +
                "";
        assertEquals(exp,level.toString());
    }
}