package com.ae2dms.controller;

import com.ae2dms.model.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.List;

import static java.lang.System.exit;

/**
 * Class {@link StartController} is the Controller of MVC pattern. It's used for maintenance.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <P>Its Model is Class {@link GameStart}. Its View is GameStart.fxml.
 * Class StartController is used to make connection with all the function
 * in the menubar of fxml. Receive the action input and pass it.</P>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class StartController {
    /**
     * label to show username.
     */
    @FXML
    public javafx.scene.control.Label nameLabelOut;
    /**
     * label to show moves count.
     */
    public javafx.scene.control.Label countLabelOut;
    /**
     * label to show current level index.
     */
    public javafx.scene.control.Label levelLabelOut;
    /**
     * ImageView to show golden key image.
     */
    public ImageView key;
    /**
     * ImageView to show golden key image.
     */
    public ImageView key2;
    /**
     * ImageView to show golden key image.
     */
    public ImageView key3;
    /**
     * ImageView to show golden key image.
     */
    public ImageView key4;
    private Stage primaryStage;
    private static GameEngine gameEngine;
    private File saveFile;
    private File loadFile;
    @FXML
    private MenuBar menu;
    public GridPane gameGrid;
    private static boolean debug = false;
    private static boolean flag = false;
    private static boolean themeFlag = false;
    /**
     * tag whether load own file or not. true if load own file, false if not.
     */
    public static boolean loadFlag = false;

    /**
     * Method loadDefaultSaveFile(Stage primaryStage) is maintenance.
     *
     * <p>Loads the default game file. Receive the username input from main page.</p>
     *
     * @param primaryStage the primary stage that will load the game page
     * @since 2020/10/25
     */
    public void loadDefaultSaveFile(Stage primaryStage) {
        this.primaryStage = primaryStage;
        InputStream in = getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
        initializeGame(in);
        setEventFilter();
        nameLabelOut.setText(MainController.userInfo);
    }

    /**
     * Method initializeGame(InputStream input) is maintenance.
     *
     * <p>Initializes the game using the provided game file. Reload the game.</p>
     *
     * @param input the game file to be loaded.
     * @since 2020/10/25
     */
    public void initializeGame(InputStream input) {
        gameEngine = new GameEngine(input, true);
        reloadGrid();
    }

    /**
     * Method setEventFilter() is maintenance.
     *
     * <p>Add the event filter to handle {@link KeyEvent} and pass value to {@link GameEngine}.</p>
     *
     * @since 2020/10/25
     */
    private void setEventFilter() {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            try {
                gameEngine.handleKey(event.getCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            reloadGrid();
        });
    }

    /**
     * Method loadGameFile() is maintenance.
     *
     * <p>Open the load game window in the system.</p>
     *
     * @throws FileNotFoundException In case of file not found exception
     * @since 2020/10/25
     */
    private void loadGameFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        saveFile = fileChooser.showOpenDialog(primaryStage);

        if (saveFile != null) {
            if (GameEngine.isDebugActive()) {
                GameEngine.logger.info("Loading save file: " + saveFile.getName());
            }
            loadFlag = true;
            initializeGame(new FileInputStream(saveFile));
        }
    }

    /**
     * Method reloadGrid() is maintenance and extension.
     *
     * <p>Reloads the grid using the {@link Level} iterator.
     * Show the current level and current moves count in real-time is extension.</p>
     *
     * @since 2020/10/25
     */
    public void reloadGrid() {
        if (gameEngine.isGameComplete()) {
            //showVictoryMessage();
            return;
        }
        Level currentLevel = gameEngine.getCurrentLevel();
        int currentLevelIndex = currentLevel.getIndex();
        Level.LevelIterator levelGridIterator = (Level.LevelIterator) currentLevel.iterator();
        gameGrid.getChildren().clear();
        while (levelGridIterator.hasNext()) {
            addObjectToGrid(levelGridIterator.next(), levelGridIterator.getCurrentPosition());
        }gameGrid.autosize();
        primaryStage.sizeToScene();
        countLabelOut.setText(String.valueOf(GameEngine.getMovesCount()));
        levelLabelOut.setText(String.valueOf(currentLevelIndex));
        setGoldenKeyImg();
    }

    /**
     * Method setGoldenKeyImg() is extension.
     *
     * <p>If user unlock specific hidden game, show GOLDEN key image at stage.</p>
     *
     * @since 2020/10/25
     */
    private void setGoldenKeyImg() {
        if (GameEngine.level1Flag == true){
            Image image = new Image("image/key.png");
            key.setImage(image);
        }
        if(GameEngine.level2Flag == true){
            Image image = new Image("image/key.png");
            key2.setImage(image);
        }
        if (GameEngine.level3Flag == true){
            Image image = new Image("image/key.png");
            key3.setImage(image);
        }
        if (GameEngine.level4Flag == true){
            Image image = new Image("image/key.png");
            key4.setImage(image);
        }
    }

    /**
     * Method showVictoryMessage() is maintenance.
     *
     * <p>Show moves count when completing levels in a new dialog.</p>
     *
     * @since 2020/10/25
     */
    private void showVictoryMessage() {
        String dialogTitle = "Game Complete!";
        String dialogMessage = "You completed " + gameEngine.mapSetName + " in " + GameEngine.getMovesCount() + " moves!";
        MotionBlur mb = new MotionBlur(2, 3);
        newDialog(dialogTitle, dialogMessage, null, mb);
        return;
    }

    /**
     * Method newDialog is maintenance and extension.
     *
     * <p>Improve and set a new dialog with input title, messages and effects.</p>
     *
     * @param dialogTitle Title of the dialog
     * @param dialogMessage Message of the dialog
     * @param dialogMessage2 Message of the dialog
     * @param dialogMessageEffect effect put on the message
     * @since 2020/10/25
     */
    public void newDialog(String dialogTitle, String dialogMessage, String dialogMessage2, Effect dialogMessageEffect) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setResizable(false);
        dialog.setTitle(dialogTitle);

        Text text1 = new Text(dialogMessage);
        Text text2 = new Text(dialogMessage2);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setFont(javafx.scene.text.Font.font(14));
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setFont(javafx.scene.text.Font.font(14));

        if (dialogMessageEffect != null) {
            text1.setEffect(dialogMessageEffect);
            text2.setEffect(dialogMessageEffect);
        }

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setBackground(Background.EMPTY);
        dialogVbox.getChildren().add(text1);
        dialogVbox.getChildren().add(text2);

        Scene dialogScene = new Scene(dialogVbox, 450, 450);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Method addObjectToGrid is maintenance.
     *
     * <p>Adds an object to the specified grid position.
     * It first converts a {@link GameObject} into a {@link ImageView}, then adds the
     * new image into the specified location. It can change theme type base on user's choice in setting.</p>
     *
     * @param gameObject the game object to be added into the grid
     * @param location the location game object put on
     * @since 2020/10/25
     */
    private void addObjectToGrid(GameObject gameObject, Point location) {
        if ((SettingController.settingFlag == false)){
            ThemeObject themeObject = new ThemeObject(gameObject);
            gameGrid.add(themeObject,location.y, location.x);
        }
        else {
            GraphicObject graphicObject = new GraphicObject(gameObject);
            gameGrid.add(graphicObject, location.y, location.x);
        }
    }

    /**
     * Method closeGame() is maintenance.
     *
     * <p>Exit the game.</p>
     *
     * @since 2020/10/25
     */
    public void closeGame() {
        exit(0);
    }

    /**
     * Method saveGame() is extension.
     *
     * <p>Open save game window in the system.</p>
     *
     * @since 2020/10/25
     */
    public void saveGame(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        loadFile = fileChooser.showSaveDialog(primaryStage);
        Level currentLevel = gameEngine.getCurrentLevel();
        java.util.List<Level> currentList = gameEngine.getCurrentList();
        contentToSkb(String.valueOf(loadFile),currentLevel,currentList);
    }

    /**
     * Method contentToSkb is extension.
     *
     * <p>Write levels from class {@link GameEngine} into new skb files.</p>
     *
     * @param filePath filePath to save the levels
     * @param currentLevel currentLevel from class {@link GameEngine}
     * @param currentList all levels from class {@link GameEngine}
     * @since 2020/10/25
     */
    private void contentToSkb(String filePath, Level currentLevel, List<Level> currentList){
        int currentLevelIndex = currentLevel.getIndex();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath),true));
            if(currentLevelIndex == 1) {
                writer.write("MapSetName: \nLevelName: \n"+String.valueOf(currentLevel.fixGrid)+"\n");
            }
            else{
                writer.write("LevelName: \n"+String.valueOf(currentLevel.fixGrid)+"\n");
            }
            for (int i = currentLevelIndex; i < currentList.size(); i++) {//save future move
                writer.write("LevelName: \n"+String.valueOf(currentList.get(i).fixGrid)+"\n");
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method loadGame() is maintenance.
     *
     * <p>Call loadGameFile() method. Catch file not found exception.</p>
     *
     * @since 2020/10/25
     */
    public void loadGame() {
        try {
            loadGameFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method undo() is extension.
     *
     * <p>Call undo() method from class {@link GameEngine}</p>
     *
     * @since 2020/10/25
     */
    public void undo()  {
        gameEngine.undo();
        reloadGrid();
    }

    /**
     * Method resetLevel() is extension.
     *
     * <p>Call reset() method from class {@link GameEngine}</p>
     *
     * @since 2020/10/25
     */
    public void resetLevel() {
        gameEngine.reset();
        reloadGrid();
    }

    /**
     * Method showAbout() is maintenance and extension.
     *
     * <p>Show information in a new dialog. Show username which got from class {@link MainController}</p>
     *
     * @since 2020/10/25
     */
    public void showAbout() {
        String title = "About this game";
        String message = "Game created by "+ MainController.userInfo+"\n";

        newDialog(title, message, null,null);
    }

    /**
     * Method toggleMusic() is extension.
     *
     * <p>Use Singleton pattern. Toggles the music from class {@link GameMusic}.</p>
     *
     * @since 2020/10/25
     */
    public void toggleMusic() {
        GameMusic object = GameMusic.getInstance();
        object.toggleMusic();
    }

    /**
     * Method toggleDebug() is maintenance.
     *
     * <P>Call toggleDebug() from class {@link GameEngine}.</P>
     *
     * @since 2020/10/25
     */
    public void toggleDebug() {
        gameEngine.toggleDebug();
        reloadGrid();
    }

    /**
     * Method showRankList() is extension.
     *
     * <p>Show Rank List in a new dialog through calling class {@link GameRank}. Catch exception.</p>
     *
     * @since 2020/10/25
     */
    public void showRankList() {
        GameRank gameRank = new GameRank();
        try {
            gameRank.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getGameEngine() is extension.
     *
     * <p>Use get method to get private parameter, enhance encapsulation</p>
     *
     * @return private parameter GameEngine
     * @since 2020/10/25
     */
    public static GameEngine getGameEngine() {
        return gameEngine;
    }
}
