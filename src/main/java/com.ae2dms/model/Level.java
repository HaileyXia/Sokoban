package com.ae2dms.model;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Class {@link Level} is extension and maintenance, implements {@link Iterable}
 *
 * <p>Level handles the creation of the game level parsing a {@link List} of {@link String}s and putting the right
 * {@link GameObject} in array. The object is created matching a char with the corresponding {@link GameObject}.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public final class Level implements Iterable<GameObject> {
    /**
     * GameGrid to store {@link GameObject} except Diamond
     */
    public final GameGrid objectsGrid;
    /**
     * GameGrid to store Diamond
     */
    public final GameGrid diamondsGrid;
    /**
     * GameGrid to store {@link GameObject}
     */
    public final GameGrid fixGrid;
    private final String name;
    private final int index;
    private int numberOfDiamonds = 0;
    private int numberOfDiamondsInGrid = 0;
    private Point keeperPosition = new Point(0, 0);

    /**
     * Method Level() is maintenance and extension.
     *
     * <p>Creates a level using the first parameter as the level name and the second parameter as {@link List} of
     * {@link String}, each one containing the characters corresponding to a specific game object. Store GameObject in objectGrid and fixGrid.</p>
     *
     * @param levelName the name of the level
     * @param levelIndex the number used as index for the levels
     * @param raw_level the raw data of the level
     * @since 2020/10/25
     */
    public Level(String levelName, int levelIndex, List<String> raw_level) {
        if (GameEngine.isDebugActive()) {
            System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
        }

        name = levelName;
        index = levelIndex;

        int rows = raw_level.size();
        int columns = raw_level.get(0).trim().length();//trim用于删除头尾空白符号

        objectsGrid = new GameGrid(rows, columns);
        diamondsGrid = new GameGrid(rows, columns);
        fixGrid = new GameGrid(rows, columns);

        for (int row = 0; row < raw_level.size(); row++) {

            for (int col = 0; col < raw_level.get(row).length(); col++) {
                GameObject curTile = GameObject.fromChar(raw_level.get(row).charAt(col));

                if (curTile == GameObject.DIAMOND) {
                    numberOfDiamonds++;
                    diamondsGrid.putGameObjectAt(curTile, row, col);
                    curTile = GameObject.FLOOR;
                } else if (curTile == GameObject.KEEPER) {
                    keeperPosition = new Point(row, col);
                }
                objectsGrid.putGameObjectAt(curTile, row, col);
                curTile = null;
            }
        }

        for (int row = 0; row < raw_level.size(); row++) {
            for (int col = 0; col < raw_level.get(row).length(); col++) {
                GameObject getObj = GameObject.fromChar(raw_level.get(row).charAt(col));
                if (getObj == GameObject.DIAMOND) {
                    //numberOfDiamonds++;
                    fixGrid.putGameObjectAt(getObj,row,col);
                }else if(getObj == GameObject.KEEPER){
                    keeperPosition = new Point(row, col);
                }
                fixGrid.putGameObjectAt(getObj,row,col);
                getObj = null;
            }
        }
    }

    /**
     * Method isComplete() is maintenance.
     *
     * <p>Check whether game is complete.</p>
     *
     * @return boolean value, whether game is complete, true if complete, false if not
     * @since 2020/10/25
     */
    boolean isComplete() {
        int cratedDiamondsCount = 0;
        for (int row = 0; row < objectsGrid.ROWS; row++) {
            for (int col = 0; col < objectsGrid.COLUMNS; col++) {
                if (((objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE_ON_DIAMOND)||(objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE)) && diamondsGrid.getGameObjectAt(col, row) == GameObject.DIAMOND){
                    cratedDiamondsCount++;
                }
            }
        }
        return cratedDiamondsCount >= numberOfDiamonds;
    }

    /**
     * Method getName() is maintenance.
     *
     * <p>Get Level Name</p>
     *
     * @return level name
     * @since 2020/10/25
     */
    public String getName() {
        return name;
    }

    /**
     * Method getIndex() is maintenance.
     *
     * <p>Get level index.</p>
     *
     * @return level index
     * @since 2020/10/25
     */
    public int getIndex() {
        return index;
    }

    /**
     * Method getKeeperPosition() is maintenance.
     *
     * <p>Get Keeper Position</p>
     *
     * @return keeper position
     * @since 2020/10/25
     */
    Point getKeeperPosition() {
        return keeperPosition;
    }

    /**
     * Method getTargetObject(Point source, Point delta) is maintenance.
     *
     * <p>Get the object at distance delta from source</p>
     *
     * @param source the source point
     * @param delta the distance from the source point
     * @return the object at distance delta from source
     * @since 2020/10/25
     */
    GameObject getTargetObject(Point source, Point delta) {
        return objectsGrid.getTargetFromSource(source, delta);
    }

    /**
     * Method toString() is maintenance.
     *
     * <p>Transfer GameGrid Object into String Object.</p>
     *
     * @return string value of gameObjects {@link GameObject}.
     * @since 2020/10/25
     */
    @Override
    public String toString() {
        return objectsGrid.toString();
    }

    /**
     * Method iterator() is maintenance.
     *
     * <p>Returns an iterator over elements of type {@link GameObject}.</p>
     *
     * @return an Iterator of GameObject
     * @since 2020/10/25
     */
    @Override
    public Iterator<GameObject> iterator() {
        return new LevelIterator();
    }

    /**
     * Class {@link LevelIterator} is maintenance.
     *
     * <p>Class LevelIterator implements the interface Iterator to iterate through the grid containing
     * the {@link GameObject} in the current {@link Level}.</p>
     *
     * @see Iterator
     * @since 2020/10/25
     */
    public class LevelIterator implements Iterator<GameObject> {

        int column = 0;
        int row = 0;

        /**
         * Method hasNext() is maintenance.
         *
         * <p>Check whether have rows and columns or not.</p>
         *
         * @return boolean value, true if has rows and columns, false if not.
         * @since 2020/10/25
         */
        @Override
        public boolean hasNext() {
            return !(row == objectsGrid.ROWS - 1 && column == objectsGrid.COLUMNS);
        }

        /**
         * Method next() is maintenance.
         *
         * <p>Check next GameObject {@link GameObject}.</p>
         *
         * @return Next GameObject
         * @since 2020/10/25
         */
        @Override
        public GameObject next() {
            if (column >= objectsGrid.COLUMNS) {
                column = 0;
                row++;
            }
            GameObject object = objectsGrid.getGameObjectAt(column, row);
            GameObject diamond = diamondsGrid.getGameObjectAt(column, row);
            GameObject retObj = object;
            column++;
            if (diamond == GameObject.DIAMOND) {
                if (object == GameObject.CRATE) {
                    retObj = GameObject.CRATE_ON_DIAMOND;
                } else if (object == GameObject.FLOOR) {
                    retObj = diamond;
                } else {
                    retObj = object;
                }
            }
            return retObj;
        }

        /**
         * Method getCurrentPosition() is maintenance.
         *
         * <p>Get current position</p>
         *
         * @return current position
         * @since 2020/10/25
         */
        public Point getCurrentPosition() {
            return new Point(column, row);
        }
    }
}