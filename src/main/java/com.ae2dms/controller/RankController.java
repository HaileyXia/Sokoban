package com.ae2dms.controller;

import com.ae2dms.model.GameData;
import com.ae2dms.data.AllData;
import com.ae2dms.data.DataFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.ae2dms.model.GameRank;

import java.io.IOException;
import java.util.ArrayList;

/**
 * class RankController {@link RankController} is the Controller of MVC pattern. It's used for maintenance of the extension class {@link GameRank}.
 *
 * <p>Controller is used to wait for user input, and pass the input from user to Model.</p>
 *
 * <P>Its Model is Class {@link GameRank}. Its View is GameRank.fxml.
 * Class RankController is used to bubble sort the moves count, name and time in each level from class {@link DataFactory} and {@link AllData}.
 * {@link AllData} is the interface, which means specific level method implements the method from interface {@link AllData}.
 * And show the result through class {@link GameRank}.</P>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class RankController {
    /**
     * table1 to show rank.
     */
    @FXML
    public TableView<RankList> table1;
    /**
     * table1Name column to show username.
     */
    public TableColumn<RankList,String> table1Name;
    /**
     * table1Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table1Moves;
    /**
     * table1Time column to show time.
     */
    public TableColumn<RankList,Long> table1Time;
    /**
     * table1index column to show rank index.
     */
    public TableColumn<RankList,Integer> table1index;
    /**
     * table2 to show rank.
     */
    public TableView<RankList> table2;
    /**
     * table2Name column to show username.
     */
    public TableColumn<RankList,String> table2Name;
    /**
     * table2Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table2Moves;
    /**
     * table2Time column to show time.
     */
    public TableColumn<RankList,Long> table2Time;
    /**
     * table2index column to show rank index.
     */
    public TableColumn<RankList,Integer> table2index;
    /**
     * table3 to show rank.
     */
    public TableView<RankList> table3;
    /**
     * table3Name column to show username.
     */
    public TableColumn<RankList,String> table3Name;
    /**
     * table3Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table3Moves;
    /**
     * table3Time column to show time.
     */
    public TableColumn<RankList,Long> table3Time;
    /**
     * table3index column to show rank index.
     */
    public TableColumn<RankList,Integer> table3index;
    /**
     * table4 to show rank.
     */
    public TableView<RankList> table4;
    /**
     * table4Name column to show username.
     */
    public TableColumn<RankList,String> table4Name;
    /**
     * table4Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table4Moves;
    /**
     * table4Time column to show time.
     */
    public TableColumn<RankList,Long> table4Time;
    /**
     * table4index column to show rank index.
     */
    public TableColumn<RankList,Integer> table4index;
    /**
     * table5 to show rank.
     */
    public TableView<RankList> table5;
    /**
     * table5Name column to show username.
     */
    public TableColumn<RankList,String> table5Name;
    /**
     * table5Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table5Moves;
    /**
     * table5Time column to show time.
     */
    public TableColumn<RankList,Long> table5Time;
    /**
     * table5index column to show rank index.
     */
    public TableColumn<RankList,Integer> table5index;
    /**
     * table6 to show rank.
     */
    public TableView<RankList> table6;
    /**
     * table6Name column to show username.
     */
    public TableColumn<RankList,String> table6Name;
    /**
     * table6Moves column to show moves count.
     */
    public TableColumn<RankList,Integer> table6Moves;
    /**
     * table6Time column to show time.
     */
    public TableColumn<RankList,Long> table6Time;
    /**
     * table6index column to show rank index.
     */
    public TableColumn<RankList,Integer> table6index;

    GameData gameData = new GameData();
    private DataFactory dataFactory = new DataFactory();
    /**
     * tag whether check overall ranking list or not. true if check ranking list button turn on, false if not.
     */
    public static boolean rankControllerFlag = false;
    private final ObservableList<RankList> cellData1 = FXCollections.observableArrayList();
    private final ObservableList<RankList> cellData2 = FXCollections.observableArrayList();
    private final ObservableList<RankList> cellData3 = FXCollections.observableArrayList();
    private final ObservableList<RankList> cellData4 = FXCollections.observableArrayList();
    private final ObservableList<RankList> cellData5 = FXCollections.observableArrayList();
    private final ObservableList<RankList> cellData6 = FXCollections.observableArrayList();
    /**
     * Method levelToTextArea() is extension, to show each level's rank in real time.
     *
     * <p>Read moves count, name and time data in each level from class {@link DataFactory} and {@link AllData},
     * which is the interface of different level class. Sort them and pack them.</p>
     *
     * @throws IOException in case of any I/O exception
     * @since 2020/10/25
     */
    public void levelToTextArea() throws IOException {
        rankControllerFlag = true;
        AllData level = dataFactory.getData(1);
        ArrayList<Integer> arrayList1 = level.getArrayList();
        ArrayList<String> nameList1 = level.getNameList();
        ArrayList<Long> timeList1 = level.getTimeList();
        gameData.bubbleSort(arrayList1,nameList1,timeList1);
        packData(arrayList1,nameList1,timeList1,cellData1,table1index,table1Moves,table1Name,table1Time,table1);

        AllData level2 = dataFactory.getData(2);
        ArrayList<Integer> arrayList2 = level2.getArrayList();
        ArrayList<String> nameList2 = level2.getNameList();
        ArrayList<Long> timeList2 = level2.getTimeList();
        gameData.bubbleSort(arrayList2,nameList2,timeList2);
        packData(arrayList2,nameList2,timeList2,cellData2,table2index,table2Moves,table2Name,table2Time,table2);

        AllData level3 = dataFactory.getData(3);
        ArrayList<Integer> arrayList3 = level3.getArrayList();
        ArrayList<String> nameList3 = level3.getNameList();
        ArrayList<Long> timeList3 = level3.getTimeList();
        gameData.bubbleSort(arrayList3,nameList3,timeList3);
        packData(arrayList3,nameList3,timeList3,cellData3,table3index,table3Moves,table3Name,table3Time,table3);

        AllData level4 = dataFactory.getData(4);
        ArrayList<Integer> arrayList4 = level4.getArrayList();
        ArrayList<String> nameList4 = level4.getNameList();
        ArrayList<Long> timeList4 = level4.getTimeList();
        gameData.bubbleSort(arrayList4,nameList4,timeList4);
        packData(arrayList4,nameList4,timeList4,cellData4,table4index,table4Moves,table4Name,table4Time,table4);

        AllData level5 = dataFactory.getData(5);
        ArrayList<Integer> arrayList5 = level5.getArrayList();
        ArrayList<String> nameList5 = level5.getNameList();
        ArrayList<Long> timeList5 = level5.getTimeList();
        gameData.bubbleSort(arrayList5,nameList5,timeList5);
        packData(arrayList5,nameList5,timeList5,cellData5,table5index,table5Moves,table5Name,table5Time,table5);

        AllData level6 = dataFactory.getData(6);
        ArrayList<Integer> arrayList6 = level6.getArrayList();
        ArrayList<String> nameList6 = level6.getNameList();
        ArrayList<Long> timeList6 = level6.getTimeList();
        gameData.bubbleSort(arrayList6,nameList6,timeList6);
        packData(arrayList6,nameList6,timeList6,cellData6,table6index,table6Moves,table6Name,table6Time,table6);

        rankControllerFlag = false;
    }

    /**
     * Method packData is extension, to pack ranking data.
     *
     * <P>Pack data(name, moves,time and rank index) from method levelToTextArea() into cellData and pass value to specific table.</P>
     *
     * @param arrayList3  sorted moves count arraylist
     * @param nameList3  sorted name arraylist
     * @param timeList3  sorted time arraylist
     * @param cellData3 data package
     * @param table3index specific table cell to store rank
     * @param table3Moves  specific table cell to store moves count
     * @param table3Name  specific table cell to store name
     * @param table3Time  specific table cell to store time
     * @param table3 specific table
     * @since 2020/10/25
     */
    public void packData(ArrayList<Integer> arrayList3, ArrayList<String> nameList3, ArrayList<Long> timeList3, ObservableList<RankList> cellData3, TableColumn<RankList, Integer> table3index,TableColumn<RankList, Integer> table3Moves, TableColumn<RankList, String> table3Name, TableColumn<RankList, Long> table3Time,TableView<RankList> table3) {
        for (int i = 0; i < arrayList3.size(); i++) {
            cellData3.add(new RankList(arrayList3.get(i), nameList3.get(i), timeList3.get(i),(i+1)));
            table3Moves.setCellValueFactory(cellData -> cellData.getValue().getMoves().asObject());
            table3Name.setCellValueFactory(cellData -> cellData.getValue().getRankName());
            table3Time.setCellValueFactory(cellData ->cellData.getValue().getTime().asObject());
            table3index.setCellValueFactory(cellData ->cellData.getValue().getIndex().asObject());
            table3.setItems(cellData3);
        }
    }
}
