package com.ae2dms.controller;

import com.ae2dms.data.AllData;
import com.ae2dms.data.DataFactory;
import com.ae2dms.model.GameData;
import com.ae2dms.model.GameEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * class RankAfterLevelController {@link RankAfterLevelController} is the Controller of MVC pattern. It's used for maintenance of the extension class {@link com.ae2dms.model.RankAfterLevel}.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <P>Its Model is Class {@link com.ae2dms.model.RankAfterLevel}. Its View is RankAfterLevel.fxml.
 * Class RankAfterLevelController is used to bubble sort the moves count, name, index and time in current level from class {@link DataFactory} and {@link AllData}.
 * {@link AllData} is the interface, which means specific level method implements the method from interface {@link AllData}.
 * And show the result through class {@link com.ae2dms.model.RankAfterLevel}.</P>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class RankAfterLevelController {
    /**
     * index column to show rank index.
     */
    @FXML
    public TableColumn<RankList,Integer> index;
    /**
     * name column to show username.
     */
    public TableColumn<RankList,String> name;
    /**
     * moves column to show moves count.
     */
    public TableColumn<RankList,Integer> moves;
    /**
     * time column to show playing time.
     */
    public TableColumn<RankList,Long> time;
    /**
     * label to show current level.
     */
    public Label levelNameOut;
    /**
     * table to show rank.
     */
    public TableView<RankList> table;
    /**
     * label to show current round moves count.
     */
    public Label movesOut;
    /**
     * label to show current username.
     */
    public Label usernameOut;
    /**
     * label to show current user playing time.
     */
    public Label timeOut;

    GameData gameData = new GameData();
    private DataFactory dataFactory = new DataFactory();
    RankController rankController = new RankController();

    private final ObservableList<RankList> cellData = FXCollections.observableArrayList();

    /**
     * Method rankToTable() is extension, to show current level's rank(A HIGH SCORE pop-up).
     *
     * <p>Read moves count, name and time data in current level from class {@link DataFactory} and {@link AllData},
     * which is the interface of different level class. Sort them and pack them.</p>
     *
     * @throws IOException Catch I/O exception
     * @since 2020/10/25
     */
    public void rankToTable() throws IOException {
        AllData level = dataFactory.getData(GameEngine.currentLevelIndex);
        ArrayList<Integer> arrayList = level.getArrayList();
        ArrayList<String> nameList = level.getNameList();
        ArrayList<Long> timeList = level.getTimeList();
        gameData.bubbleSort(arrayList,nameList,timeList);
        rankController.packData(arrayList,nameList,timeList,cellData,index,moves,name,time,table);
        usernameOut.setText(MainController.userInfo);
        movesOut.setText(String.valueOf(GameEngine.getMovesCount()));
        timeOut.setText(String.valueOf(GameEngine.timeDifference));
        if(GameEngine.currentLevelIndex == 1){
            levelNameOut.setText("LEVEL 1");
        }
        if(GameEngine.currentLevelIndex == 2){
            levelNameOut.setText("LEVEL 2");
        }
        if(GameEngine.currentLevelIndex == 3){
            levelNameOut.setText("LEVEL 3");
        }
        if(GameEngine.currentLevelIndex == 4){
            levelNameOut.setText("LEVEL 4");
        }
        if(GameEngine.currentLevelIndex == 5){
            levelNameOut.setText("LEVEL 5");
        }
        if(GameEngine.currentLevelIndex == 6){
            levelNameOut.setText("LEVEL 6");
        }
    }
}
