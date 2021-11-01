package com.ae2dms.model;

import com.ae2dms.controller.StartController;
import com.ae2dms.direction.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class {@link GameEngine} is maintenance and extension.
 *
 * <p>It is the engine of the game, the main methods related to game are in this class.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameEngine{
    private StartController startController = new StartController();
    /**
     * name of the game.
     */
    public static final String GAME_NAME = "SokobanFX";
    /**
     * logger of the game
     */
    public static GameLogger logger;
    private static boolean undoFlag = false;
    private static boolean resetFlag = false;
    /**
     * tag whether player turn left or not.
     */
    public static boolean leftFlag = false;
    /**
     * tag whether player turn right or not.
     */
    public static boolean rightFlag = false;
    /**
     * tag whether player go up or not.
     */
    public static boolean upFlag = false;
    /**
     * tag whether unlock hidden game in level1.
     */
    public static boolean level1Flag = false;
    /**
     * tag whether unlock hidden game in level2.
     */
    public static boolean level2Flag = false;
    /**
     * tag whether unlock hidden game in level3.
     */
    public static boolean level3Flag = false;
    /**
     * tag whether unlock hidden game in level4.
     */
    public static boolean level4Flag = false;
    private static int movesCount = 0;
    /**
     * current level index.
     */
    public static int currentLevelIndex;
    /**
     * map name.
     */
    public String mapSetName;
    private static boolean debug = false;
    private Level currentLevel;
    private List<Level> levels;
    private boolean gameComplete = false;
    private int moveIndex = 0;
    private Point keeperPosition;
    private Point targetObjectPoint;
    private GameObject keeper;
    private GameObject keeperTarget;
    private ArrayList<Point> keeperHistory = new ArrayList<Point>();
    private ArrayList<Point> targetHistory = new ArrayList<Point>();
    private ArrayList<GameGrid> allHistory = new ArrayList<>();
    private ArrayList<GameGrid> allObjectsGrid = new ArrayList<>();
    private Long startTime;
    private Long stopTime;
    /**
     * playing time between start and stop
     */
    public static Long timeDifference;

    /**
     * Constructor GameEngine is maintenance.
     *
     * <p>Uses a {@link File} to load the game map containing all the levels.</p>
     *
     * @param input the file containing the game levels.
     * @param production true if using the engine
     * @since 2020/10/25
     */
    public GameEngine(InputStream input, boolean production) {
        try {
            logger = new GameLogger();
            levels = loadGameFile(input);
            currentLevel = getNextLevel();
        } catch (IOException x) {
            System.out.println("Cannot create logger.");
        } catch (NoSuchElementException e) {
            logger.warning("Cannot load the default save file: " + e.getStackTrace());
        }
    }

    /**
     * Method isDebugActive() is maintenance.
     *
     * <p>Checks if the debug mode is active.</p>
     *
     * @return boolean true if the debug mode is active, false if not
     * @since 2020/10/25
     */
    public static boolean isDebugActive() {
        return debug;
    }

    /**
     * Method getMovesCount()
     *
     * <p>Get moves count</p>
     *
     * @return moves count
     * @since 2020/10/25
     */
    public static int getMovesCount() {
        return movesCount;
    }

    /**
     * Method getMapSetName() is maintenance.
     *
     * <p>Get map name.</p>
     *
     * @return map name
     * @since 2020/10/25
     */
    public String getMapSetName() {
        return mapSetName;
    }

    /**
     * Method handleKey(KeyCode code) is extension and maintenance, using Strategy Pattern {@link Strategy}.
     *
     * <p>Handles the action that should be executed when a specific keyboard key {@link KeyCode} is pressed.</p>
     *
     * @param code keyboard key {@link KeyCode} is pressed
     * @throws IOException Catch I/O exception from keyboard.
     * @since 2020/10/25
     */
    public void handleKey(KeyCode code) throws IOException {
        switch (code) {
            case UP:
            case W:
                upFlag = true;
                Context contextU = new Context(new UpOperation());
                contextU.executeStrategy(code);
                break;

            case RIGHT:
            case D:
                rightFlag = true;
                Context contextR = new Context(new RightOperation());
                contextR.executeStrategy(code);
                break;

            case DOWN:
            case S:
                Context contextD = new Context(new DownOperation());
                contextD.executeStrategy(code);
                break;

            case LEFT:
            case A:
                leftFlag = true;
                Context contextL = new Context(new LeftOperation());
                contextL.executeStrategy(code);
                break;

            default:
        }
        if (isDebugActive()) {
            System.out.println(code);
        }
    }

    /**
     * Method undo() is extension.
     *
     * <p>undo, if keeper at the start position, show warning message, else, get the undo move(movesCount - moveIndex)'s GameGrid.
     * then delete the GameGrid from undo move to last move.</p>
     *
     * @return GameGrid of (movesCount - moveIndex) move without diamond
     * @since 2020/10/25
     */
    public GameGrid undo(){
        if(keeperHistory.size()==0){
            String dialogTitle = "WARNING";
            String dialogMessage = "You are in the START position! You can NOT undo!!";
            startController.newDialog(dialogTitle, dialogMessage, null, null);
        }else {
            undoFlag = true;
            moveIndex = moveIndex + 1;
            if ((movesCount - moveIndex) >= 0) {
                GameGrid backGrid = allObjectsGrid.get(movesCount - moveIndex);
                GameGrid backSaveGrid = allHistory.get(movesCount - moveIndex);
                for (int row = 0; row < currentLevel.objectsGrid.ROWS; row++) {
                    for (int col = 0; col < currentLevel.objectsGrid.COLUMNS; col++) {
                        GameObject getObj = backGrid.getGameObjectAt(row, col);
                        if (getObj == GameObject.DIAMOND) {
                            getObj = GameObject.FLOOR;
                        } else if (getObj == GameObject.KEEPER) {
                            keeperPosition = currentLevel.getKeeperPosition();
                            keeperPosition = new Point(row, col);
                        }
                        currentLevel.objectsGrid.putGameObjectAt(getObj, row, col);
                        getObj = null;
                    }
                }
                getSpecificGrid(backSaveGrid);
            }
            safeDeleteGrid();
        }
        return currentLevel.objectsGrid;
    }

    /**
     * Method getSpecificGrid(GameGrid backSaveGrid) is extension.
     *
     * <p>Set GameGrid with all {@link GameObject} in specific index.</p>
     *
     * @param backSaveGrid Specific GameGrid with all {@link GameObject}
     * @since 2020/10/25
     */
    private void getSpecificGrid(GameGrid backSaveGrid) {
        for (int row = 0; row < currentLevel.objectsGrid.ROWS; row++) {
            for (int col = 0; col < currentLevel.objectsGrid.COLUMNS; col++) {
                GameObject getObj = backSaveGrid.getGameObjectAt(row, col);
                if (getObj == GameObject.DIAMOND) {
                    currentLevel.fixGrid.putGameObjectAt(getObj,row,col);
                }else if(getObj == GameObject.KEEPER){
                    keeperPosition = currentLevel.getKeeperPosition();
                    keeperPosition = new Point(row, col);
                }
                currentLevel.fixGrid.putGameObjectAt(getObj,row,col);
                getObj = null;
            }
        }
    }

    /**
     * Method reset() is extension.
     *
     * <p>reset, if keeper at the start position, show warning message, else, get the index 0's GameGrid.
     * then clear all the arrayList which store the gameGrid, and set hidden game flag into false.</p>
     *
     * @return GameGrid without diamond at start point.
     * @since 2020/10/25
     */
    public GameGrid reset(){
        if(allObjectsGrid.size()==0){
            String dialogTitle = "WARNING";
            String dialogMessage = "You are in the START position! You can NOT reset!!";
            startController.newDialog(dialogTitle, dialogMessage, null, null);
        }else {
            movesCount = 0;
            moveIndex = 0;
            resetFlag = true;
            level1Flag = false;
            level2Flag = false;
            level3Flag = false;
            level4Flag = false;
            GameGrid resetGrid = allObjectsGrid.get(0);
            GameGrid allResetGrid = allHistory.get(0);
            for (int row = 0; row < currentLevel.objectsGrid.ROWS; row++) {
                for (int col = 0; col < currentLevel.objectsGrid.COLUMNS; col++) {
                    GameObject getObj = resetGrid.getGameObjectAt(row, col);
                    if (getObj == GameObject.KEEPER) {
                        keeperPosition = currentLevel.getKeeperPosition();
                        keeperPosition = new Point(row, col);
                    }
                    currentLevel.objectsGrid.putGameObjectAt(getObj, row, col);
                    getObj = null;
                }
            }
            getSpecificGrid(allResetGrid);
            System.out.println(keeperPosition);
            allHistory.clear();
            allObjectsGrid.clear();
            keeperHistory.clear();
            targetHistory.clear();
        }
        return currentLevel.objectsGrid;
    }

    /**
     * Method safeDeleteGrid() is extension.
     *
     * <p>Delete gameGrid(with or without diamond) from index of (movesCount-moveIndex) to last move in the arrayList.
     * Delete keeper position and target position from index of (movesCount-moveIndex) to last move in the arrayList.</p>
     *
     * @return the arrayList of GameGrid with all {@link GameObject}
     * @since 2020/10/25
     */
    private ArrayList<GameGrid> safeDeleteGrid(){
        int i = movesCount-moveIndex;
        if ((keeperHistory.size()-(movesCount-moveIndex))>=1){
            for ( ; i < (keeperHistory.size()); i++) {
                keeperHistory.remove(i);
                targetHistory.remove(i);
            }
        }
        int j = movesCount-moveIndex;
        for ( ; j < (allHistory.size()-1); j++) {
            allObjectsGrid.remove(j+1);
            allHistory.remove(j+1);
        }
        return allObjectsGrid;
    }

    /**
     * Method move(Point delta) is extension and maintenance.
     *
     * <p>Handles the movement of the keeper and the objects that collide with it.
     * Each time keeper move, save the keeper position, target position, GameGrid with all {@link GameObject} and GameGrid without diamond into arrayList.
     * have music effect when keeper pushing crate. have music effect when complete level.</p>
     *
     * @param delta the movement delta
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public void move(Point delta) throws IOException {
        if(movesCount == 0){
            allHistory.add(currentLevel.fixGrid.getGameGrid(currentLevel.fixGrid.COLUMNS,currentLevel.fixGrid.ROWS,currentLevel.fixGrid));
            allObjectsGrid.add(currentLevel.objectsGrid.getGameGrid(currentLevel.fixGrid.COLUMNS,currentLevel.fixGrid.ROWS,currentLevel.objectsGrid));
        }//add the original gameGrid into arrayList.
        if (isGameComplete()) {
            return;
        }
        if((undoFlag == false)&&(resetFlag == false)){
            keeperPosition = currentLevel.getKeeperPosition();
        }
        keeper = currentLevel.objectsGrid.getGameObjectAt(keeperPosition);
        targetObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
        keeperTarget = currentLevel.objectsGrid.getGameObjectAt(targetObjectPoint);
        if (keeperTarget != GameObject.WALL) {
            keeperHistory.add(new Point(keeperPosition));
            targetHistory.add(new Point(GameGrid.translatePoint(keeperPosition, delta)));
        }
        if (GameEngine.isDebugActive()) {
            System.out.println("Current level state:");
            System.out.println(currentLevel.toString());
            System.out.println("Keeper pos: " + keeperPosition);
            System.out.println("Movement source obj: " + keeper);
            System.out.printf("Target object: %s at [%s]", keeperTarget, targetObjectPoint);
            System.out.println();
        }
        boolean keeperMoved = false;
        switch (keeperTarget) {
            case WALL:
                break;

            case CRATE:
                GameObject crateTarget = currentLevel.getTargetObject(targetObjectPoint, delta);
                if (crateTarget != GameObject.FLOOR) {
                    break;
                }
                GameMusic object = GameMusic.getInstance();
                object.crateMusic();
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(targetObjectPoint, delta)), targetObjectPoint);
                currentLevel.objectsGrid.putGameObjectAt(keeperTarget, GameGrid.translatePoint(targetObjectPoint, delta));
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)), keeperPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
                keeperMoved = true;
                break;

            case FLOOR:
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)), keeperPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
                keeperMoved = true;
                break;

            default:
                logger.severe("The object to be moved was not a recognised GameObject.");
                throw new AssertionError("This should not have happened. Report this problem to the developer.");
        }
        if (StartController.loadFlag == false) {
            hideGame();
        }
        for (int row = 0; row < currentLevel.objectsGrid.ROWS; row++) {
            for (int col = 0; col < currentLevel.objectsGrid.COLUMNS; col++) {
                GameObject getObj = currentLevel.objectsGrid.getGameObjectAt(row, col);
                GameObject getTarget = currentLevel.diamondsGrid.getGameObjectAt(row,col);
                if (getTarget == GameObject.DIAMOND) {
                    if (getObj == GameObject.CRATE){
                        getObj = GameObject.CRATE_ON_DIAMOND;
                        currentLevel.fixGrid.putGameObjectAt(GameObject.CRATE_ON_DIAMOND,row,col);
                    } else {
                        getObj = getTarget;
                        currentLevel.fixGrid.putGameObjectAt(getObj, row, col);
                    }
                }
                currentLevel.fixGrid.putGameObjectAt(getObj,row,col);
                getObj = null;
            }
        }
        if (keeperTarget != GameObject.WALL) {
            allHistory.add(currentLevel.fixGrid.getGameGrid(20, 20, currentLevel.fixGrid));
            allObjectsGrid.add(currentLevel.objectsGrid.getGameGrid(20, 20, currentLevel.objectsGrid));
        }
        if (keeperMoved) {
            keeperPosition.translate((int) delta.getX(), (int) delta.getY());
            movesCount++;
            if (currentLevel.isComplete()) {
                stopTime = System.currentTimeMillis();
                System.out.println("Level complete!");
                GameMusic object = GameMusic.getInstance();
                object.victoryMusic();
                if(StartController.loadFlag == false) {
                    showLevelComplete();
                }
                if(StartController.loadFlag == true){
                    String dialogTitle = "WARNING";
                    String dialogMessage = "You are LOADING your OWN game! You can NOT Participant in Ranking!!";
                    startController.newDialog(dialogTitle, dialogMessage, null, null);
                }
                if (isDebugActive()) {
                    System.out.println("Level complete!");
                }
                currentLevel = getNextLevel();
            }
        }
    }

    /**
     * Method hideGame() is extension.
     *
     * <p>if keeper go to specific point at each level, keeper can unlock hidden game, blow the wall, get golden key.</p>
     *
     * @since 2020/10/25
     */
    private void hideGame(){
        int currentLevelIndex = currentLevel.getIndex();
        if(currentLevelIndex == 1){
            if (keeperPosition.equals(new Point(2, 4))&&(level1Flag==false)){
                getGoldenKey();
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(1,5));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(2,5));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(3,5));
                GameMusic object = GameMusic.getInstance();
                object.blowMusic();
                level1Flag = true;
            }
        }
        if(currentLevelIndex == 2){
            if (keeperPosition.equals(new Point(11, 11))&&(level2Flag==false)){
                getGoldenKey();
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(13,10));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(14,10));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(15,10));
                GameMusic object = GameMusic.getInstance();
                object.blowMusic();
                level2Flag = true;
            }
        }
        if(currentLevelIndex == 3){
            if (keeperPosition.equals(new Point(6, 18))&&(level3Flag==false)){
                getGoldenKey();
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(3,7));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(3,8));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(3,6));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(3,5));
                GameMusic object = GameMusic.getInstance();
                object.blowMusic();
                level3Flag = true;
            }
        }
        if(currentLevelIndex == 4){
            if (keeperPosition.equals(new Point(13, 3))&&(level4Flag==false)){
                getGoldenKey();
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(14,4));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(14,5));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(14,6));
                currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(14,7));
                GameMusic object = GameMusic.getInstance();
                object.blowMusic();
                level4Flag = true;
            }
        }
    }

    /**
     * Method getGoldenKey() is extension.
     *
     * <p>if keeper unlock hidden game, pop-up good news message.</p>
     *
     * @since 2020/10/25
     */
    private void getGoldenKey() {
        String dialogTitle = "GOOD NEWS";
        String dialogMessage = "GOOD news! You unlock HIDDEN game!\n Which will BLOW up the walls around you!!";
        startController.newDialog(dialogTitle, dialogMessage, null, null);
    }

    /**
     * Method showLevelComplete() is extension.
     *
     * <p>if level is complete, pop up ranking list(include username, steps, ranking and time).
     * clear gameGrid and keeper position, target position's arrayList. set moves count and index into 0.
     * set resetFlag and undoFlag false.</p>
     *
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    private void showLevelComplete() throws IOException {
        timeDifference = (stopTime-startTime)/1000;
        currentLevelIndex = currentLevel.getIndex();
        RankAfterLevel rankAfterLevel = new RankAfterLevel();
        try {
            rankAfterLevel.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resetFlag = false;
        undoFlag = false;
        movesCount = 0;
        moveIndex = 0;
        keeperHistory.clear();
        allObjectsGrid.clear();
        allHistory.clear();
        targetHistory.clear();
        return;
    }

    /**
     * Method loadGameFile(InputStream input) is maintenance.
     *
     * <p>Loads a game file creating a {@link List} of {@link Level}s.</p>
     * @param input the file containing the levels
     * @return the list containing the levels
     * @since 2020/10/25
     */
    private List<Level> loadGameFile(InputStream input) {
        List<Level> levels = new ArrayList<>(5);
        int levelIndex = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            boolean parsedFirstLevel = false;
            List<String> rawLevel = new ArrayList<>();
            String levelName = "";

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    if (rawLevel.size() != 0) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                    }
                    break;
                }
                if (line.contains("MapSetName")) {
                    mapSetName = line.replace("MapSetName: ", "");
                    continue;
                }
                if (line.contains("LevelName")) {
                    if (parsedFirstLevel) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                        rawLevel.clear();
                    } else {
                        parsedFirstLevel = true;
                    }
                    levelName = line.replace("LevelName: ", "");
                    continue;
                }
                line = line.trim();
                line = line.toUpperCase();
                if (line.matches(".*W.*W.*")) {
                    rawLevel.add(line);
                }
            }
        } catch (IOException e) {
            logger.severe("Error trying to load the game file: " + e);
        } catch (NullPointerException e) {
            logger.severe("Cannot open the requested file: " + e);
        }
        return levels;
    }

    /**
     * Method isGameComplete() is maintenance.
     *
     * <p>true if the game is complete. false if not.</p>
     *
     * @return boolean value, true if the game is complete, false if not.
     * @since 2020/10/25
     */
    public boolean isGameComplete() {
        return gameComplete;
    }

    /**
     * Method getNextLevel() is maintenance.
     *
     * <p>Get next level in the list of levels.</p>
     *
     * @return the next level loaded from the save file.
     * @since 2020/10/25
     */
    public Level getNextLevel() {
        if (currentLevel == null) {
            startTime = System.currentTimeMillis();
            return levels.get(0);
        }
        int currentLevelIndex = currentLevel.getIndex();
        if (currentLevelIndex < levels.size()) {
            startTime = System.currentTimeMillis();
            return levels.get(currentLevelIndex);
        }
        gameComplete = true;
        return null;
    }

    /**
     * Method getCurrentLevel() is maintenance.
     *
     * <p>Get the current level.</p>
     *
     * @return the current level.
     * @since 2020/10/25
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Method getCurrentList() is maintenance.
     *
     * <p>get list of level.</p>
     *
     * @return list of level
     * @since 2020/10/25
     */
    public List<Level> getCurrentList() {
        return levels;
    }

    /**
     * Method toggleDebug() is maintenance.
     *
     * <p>Toggles the debug mode.</p>
     *
     * @since 2020/10/25
     */
    public void toggleDebug() {
        debug = !debug;
    }

}