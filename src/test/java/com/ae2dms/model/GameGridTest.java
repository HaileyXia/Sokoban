package com.ae2dms.model;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class {@link GameGridTest} to test Class {@link GameGrid}
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameGridTest {
    private static GameEngine gameEngine;
    private Level level;
    private static GameGrid gameGrid;
    private int rows = 5;
    private int columns = 5;

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
     * <p>new GameGrid() before start.</p>
     *
     * @since 2020/10/25
     */
    @BeforeEach
    public void testConstructor() {
        gameGrid = new GameGrid(columns, rows);
        assertTrue(gameGrid != null);
    }


    /**
     * <p>Test putObjectAt()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testPutObjectAt1() {
        int newRow = rows + 1;
        int newColumn = columns + 1;
        assertFalse(gameGrid.putGameObjectAt(GameObject.CRATE, newRow, newColumn));
    }

    /**
     * <p>Test putObjectAt()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testPutObjectAt2() {
        int newRow = rows - 1;
        int newColumn = columns - 1;
        assertTrue(gameGrid.putGameObjectAt(GameObject.CRATE, newRow, newColumn));
    }

    /**
     * <p>Test putObjectAt()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testPutObjectAt3() {
        int newRow = rows;
        int newColumn = columns;
        assertFalse(gameGrid.putGameObjectAt(GameObject.CRATE,newRow, newColumn));
    }

    /**
     * <p>Test putObjectAt()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testPutObjectAt4() {
        gameGrid.putGameObjectAt(GameObject.CRATE, 0,0);
        assertEquals(gameGrid.getGameObjectAt(0,0), GameObject.CRATE);
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
        TestCase.assertEquals(exp,level.toString());
    }

    /**
     * <p>Test getGameGrid()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetGameGrid() {
        GameGrid copy = level.objectsGrid.getGameGrid(20,20,level.fixGrid);
        assertEquals(level.fixGrid.toString(),copy.toString());
    }

}