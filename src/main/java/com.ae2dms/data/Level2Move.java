package com.ae2dms.data;

import com.ae2dms.model.GameData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Level2Move {@link Level2Move} is maintenance.
 *
 * <p>Create an entity class that implements the interface {@link AllData}.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class Level2Move implements AllData{
    private GameData gameData = new GameData();
    private ArrayList<Integer> arrayList;
    private ArrayList<String> nameList;
    private ArrayList<Long> timeList;

    /**
     * Method readMove() is extension
     *
     * <p>Read all move count from level2.txt, write all move counts into level2.txt. Use method from CLass {@link GameData}</p>
     *
     * @throws IOException Catch I/O exception
     * @since 2020/10/25
     */
    @Override
    public void readMove() throws IOException {
        FileReader frMove = new FileReader("src/main/resources/data/level2.txt");
        arrayList = gameData.readFromFile(frMove);
        int size = arrayList.size();
        FileWriter fileWriter = new FileWriter("src/main/resources/data/level2.txt");
        gameData.writeIntoFile(fileWriter, arrayList, size);
    }

    /**
     * Method readName() is extension
     *
     * <p>Read all usernames from name2.txt, write all usernames into name2.txt. Use method from CLass {@link GameData}</p>
     *
     * @throws IOException Catch I/O exception
     * @since 2020/10/25
     */
    public void readName() throws IOException {
        FileReader frName = new FileReader("src/main/resources/data/name2.txt");
        nameList = gameData.readNameFromFile(frName);
        int nameSize = nameList.size();
        FileWriter fileWriter = new FileWriter("src/main/resources/data/name2.txt");
        gameData.nameWriteIntoFile(fileWriter,nameList,nameSize);
    }

    /**
     * Method readTime() is extension.
     *
     * <p>Read all time from time2.txt, write all time into time2.txt. Use method from CLass {@link GameData}</p>
     *
     * @throws IOException Catch I/O exception
     * @since 2020/10/25
     */
    public void readTime() throws IOException{
        FileReader frTime = new FileReader("src/main/resources/data/time2.txt");
        timeList = gameData.readTimeFromFile(frTime);
        int timeSize = timeList.size();
        FileWriter fileWriter = new FileWriter("src/main/resources/data/time2.txt");
        gameData.timeWriteIntoFile(fileWriter, timeList, timeSize);
    }

    /**
     * Method getArrayList() is extension to improve encapsulation.
     *
     * <p>Get ArrayList.</p>
     *
     * @return Integer arrayList of moves count
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public ArrayList<Integer> getArrayList() throws IOException {
        readMove();
        return arrayList;
    }

    /**
     * Method getNameList() is extension to improve encapsulation.
     *
     * <p>Get nameList. </p>
     *
     * @return String arrayList of usernames
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public ArrayList<String> getNameList() throws IOException {
        readName();
        return nameList;
    }

    /**
     * Method getTimeList() is extension to improve encapsulation.
     *
     * <p>Get timeList.</p>
     *
     * @return Long arrayList of time
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    @Override
    public ArrayList<Long> getTimeList() throws IOException {
        readTime();
        return timeList;
    }
}
