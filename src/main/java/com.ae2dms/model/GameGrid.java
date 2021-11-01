package com.ae2dms.model;

import java.awt.*;
import java.util.Iterator;

/**
 * Class GameGrid {@link GameGrid} is extension and maintenance.
 *
 * <p>GameGrid class can be used to create a 2D grid and gameObjects to it.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameGrid implements Iterable {

    /**
     * The number of columns.
     */
    final int COLUMNS;
    /**
     * The number of rows.
     */
    final int ROWS;
    /**
     * The deep copy of grid.
     */
    public GameGrid copyFixGrid;
    /**
     * The grid containing every GameObject.
     */
    private GameObject[][] gameObjects;

    /**
     * Method GameGrid(int columns, int rows) is maintenance.
     *
     * <p>Creates the grid using columns and rows to set the maximum size.</p>
     *
     * @param columns the number of columns
     * @param rows the number of rows
     * @since 2020/10/25
     */
    public GameGrid(int columns, int rows) {
        COLUMNS = columns;
        ROWS = rows;

        // Initialize the array
        gameObjects = new GameObject[COLUMNS][ROWS];
    }

    /**
     * Method translatePoint(Point sourceLocation, Point delta) is extension.
     *
     * <p>Returns the point located at a distance delta from a starting GameObject point.</p>
     *
     * @param sourceLocation the source(original) point
     * @param delta the distance between the reference GameObject and the target point
     * @return the target point at a distance delta from sourceLocation.
     * @since 2020/10/25
     */
    static Point translatePoint(Point sourceLocation, Point delta) {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }

    /**
     * Method getDimension() is maintenance.
     *
     * <p>Returns the size of this grid as {@link Dimension}.</p>
     *
     * @return a {@link Dimension} GameObject that shows the size of this grid
     * @since 2020/10/25
     */
    public Dimension getDimension() {
        return new Dimension(COLUMNS, ROWS);
    }

    /**
     * Method getTargetFromSource(Point source, Point delta) is maintenance.
     *
     * <p>Gets the GameObject at delta distance from the origin GameObject.</p>
     *
     * @param source the source GameObject point
     * @param delta the distance from source
     * @return the target GameObject
     * @since 2020/10/25
     */
    GameObject getTargetFromSource(Point source, Point delta) {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * Method getGameObjectAt(int col, int row) is maintenance.
     *
     * <p>getGameObjectAt(int col, int row)</p>
     *
     * @param col the row of the GameObject
     * @param row the column of the GameObject
     * @return GameObject
     * @throws ArrayIndexOutOfBoundsException Catch exception if coordinates are outside the grid bounds
     * @since 2020/10/25
     */
    public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
        if (isPointOutOfBounds(col, row)) {
            if (GameEngine.isDebugActive()) {
                System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
            }
            throw new ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is outside the map.");
        }

        return gameObjects[col][row];
    }

    /**
     * Method getGameObjectAt(Point p) is maintenance.
     *
     * <p>Gets an specific GameObject located at the chosen {@link Point}</p>
     *
     * @param p specific point that chosen.
     * @return GameObject
     * @since 2020/10/25
     */
    public GameObject getGameObjectAt(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }

        return getGameObjectAt((int) p.getX(), (int) p.getY());
    }

    /**
     * Method removeGameObjectAt(Point position) is maintenance.
     *
     * <p>Removes a {@link GameObject} from the grid</p>
     *
     * @param position specific point putting null on it.
     * @return boolean value, true if is possible to remove the GameObject, false if not.
     * @since 2020/10/25
     */
    public boolean removeGameObjectAt(Point position) {
        return putGameObjectAt(null, position);
    }

    /**
     * Method putGameObjectAt(GameObject gameObject, int x, int y) is maintenance.
     *
     * <p>Puts a {@link GameObject} into the specified location (x, y).</p>
     *
     * @param gameObject the gameObject to be put into the position
     * @param x the x coordinate
     * @param y the y coordinate
     * @return boolean value, true if the operation is successful, false if not.
     * @since 2020/10/25
     */
    public boolean putGameObjectAt(GameObject gameObject, int x, int y) {
        if (isPointOutOfBounds(x, y)) {
            return false;
        }

        gameObjects[x][y] = gameObject;
        return gameObjects[x][y] == gameObject;
    }

    /**
     * Method putGameObjectAt(GameObject gameObject, Point p) is extension.
     *
     * <p>Puts a {@link GameObject} into the specified point.</p>
     *
     * @param gameObject the gameObject to be put into the position
     * @param p the point where the GameObject will be put into
     * @return boolean value, true if the operation is successful, false if not.
     * @since 2020/10/25
     */
    public boolean putGameObjectAt(GameObject gameObject, Point p) {
        return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
    }

    /**
     * Method isPointOutOfBounds(int x, int y) is maintenance.
     *
     * <p>Checks if a point is outside the grid.</p>
     *
     * @param x the x position on the grid
     * @param y the y position on the grid
     * @return boolean value, true if it is outside the grid, false if not.
     * @since 2020/10/25
     */
    private boolean isPointOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
    }

    /**
     * Method isPointOutOfBounds(Point p) is maintenance.
     *
     * <p>Checks if a point is outside the grid.</p>
     *
     * @param p the point need to be checked
     * @return boolean value, true if it is outside the grid, false if not.
     * @since 2020/10/25
     */
    private boolean isPointOutOfBounds(Point p) {
        return isPointOutOfBounds(p.x, p.y);
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
        StringBuilder sb = new StringBuilder(gameObjects.length);

        for (GameObject[] gameObject : gameObjects) {
            for (GameObject aGameObject : gameObject) {
                if (aGameObject == null) {
                    aGameObject = GameObject.DEBUG_OBJECT;
                }
                sb.append(aGameObject.getCharSymbol());
            }
            sb.append('\n');
        }
        return sb.toString();
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
        return new GridIterator();
    }

    /**
     * Class {@link GridIterator} is maintenance.
     *
     * <p>Class GridIterator implements the interface Iterator to iterate through the grid containing
     * the {@link GameObject} in the current {@link Level}.</p>
     *
     * @see Iterator
     * @author Tianqi XIA
     * @since 2020/10/25
     * @version 2.1
     */
    public class GridIterator implements Iterator<GameObject> {
        int row = 0;
        int column = 0;

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
            return !(row == ROWS && column == COLUMNS);
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
            if (column >= COLUMNS) {
                column = 0;
                row++;
            }
            return getGameObjectAt(column++, row);
        }
    }

    /**
     * Method getGameGrid(int columns, int rows, GameGrid fixGrid) is extension.
     *
     * <p>Deep copy GameGrid.</p>
     *
     * @param columns The number of columns.
     * @param rows The number of rows.
     * @param fixGrid Original GameGrid to be deep copied.
     * @return Deep copy GameGrid.
     * @since 2020/10/25
     */
    public GameGrid getGameGrid(int columns, int rows, GameGrid fixGrid){
        copyFixGrid = new GameGrid(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GameObject fixGridObj = fixGrid.getGameObjectAt(i,j);
                copyFixGrid.putGameObjectAt(fixGridObj,i,j);
            }
        }
        return copyFixGrid;
    }
}