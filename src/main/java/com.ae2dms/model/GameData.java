package com.ae2dms.model;

import com.ae2dms.controller.MainController;
import com.ae2dms.controller.RankController;
import com.ae2dms.controller.StartController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class GameData{@link GameData} is extension.
 *
 * <p>It is used to read data from file, write data into file and sort data in the file.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameData {
    /**
     * Method readFromFile(FileReader fr) is extension.
     *
     * <p>Read moves count data from specific file.</p>
     *
     * @param fr File which read data from
     * @return Integer arrayList of moves count
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public ArrayList<Integer> readFromFile(FileReader fr) throws IOException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(fr);
        String str;
        while ((str = bf.readLine()) != null) {
            arrayList.add(Integer.valueOf(str).intValue());
        }
        bf.close();
        fr.close();
        if((RankController.rankControllerFlag == false)&&(StartController.loadFlag == false)){
            arrayList.add(GameEngine.getMovesCount());
        }
        return arrayList;
    }

    /**
     * Method writeIntoFile() is extension.
     *
     * <p>Write moves count data into specific file.</p>
     *
     * @param fileWriter Specific file which write data into.
     * @param array integer arrayList which stores moves count.
     * @param size size of the arrayList.
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public void writeIntoFile(FileWriter fileWriter, ArrayList<Integer> array, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            fileWriter.write(array.get(i) + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Method readNameFromFile(FileReader fr) is extension.
     *
     * <p>Read usernames data from file.</p>
     *
     * @param fr File which read data from.
     * @return String arrayList of usernames.
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public ArrayList<String> readNameFromFile(FileReader fr) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(fr);
        String str;
        while ((str = bf.readLine()) != null) {
            arrayList.add(str);
        }
        bf.close();
        fr.close();
        if((RankController.rankControllerFlag == false)&&(StartController.loadFlag == false)){
            arrayList.add(MainController.userInfo);
        }
        return arrayList;
    }

    /**
     * Method nameWriteIntoFile() is extension.
     *
     * <p>Write usernames data into specific file.</p>
     *
     * @param fileWriter Specific file which write data into.
     * @param array String arrayList which stores usernames.
     * @param size size of the string arrayList.
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public void nameWriteIntoFile(FileWriter fileWriter, ArrayList<String> array, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            fileWriter.write(array.get(i) + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Method readTimeFromFile(FileReader fr) is extension.
     *
     * <p>Read time data from file.</p>
     *
     * @param fr File which read data from.
     * @return Long arrayList of time.
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public ArrayList<Long> readTimeFromFile(FileReader fr) throws IOException {
        ArrayList<Long> arrayList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(fr);
        String str;
        while ((str = bf.readLine()) != null) {
            arrayList.add(Long.valueOf(str));
        }
        bf.close();
        fr.close();
        if((RankController.rankControllerFlag == false)&&(StartController.loadFlag == false)){
            arrayList.add(GameEngine.timeDifference);
        }
        return arrayList;
    }

    /**
     * Method timeWriteIntoFile() is extension.
     *
     * <p>Write time data into specific file.</p>
     *
     * @param fileWriter Specific file which write data into.
     * @param array Long arrayList which stores time.
     * @param size size of the long arrayList.
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    public void timeWriteIntoFile(FileWriter fileWriter, ArrayList<Long> array, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            fileWriter.write(array.get(i) + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Method getMinFromData() is extension.
     *
     * <p>Get the minimal moves count in the integer arrayList.</p>
     *
     * @param arrayList Integer arrayList of moves count.
     * @return minimal moves count in the arrayList.
     * @since 2020/10/25
     */
    public int getMinFromData(ArrayList<Integer> arrayList) {
        int min = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) < min) {
                min = arrayList.get(i);
            }
        }
        return min;
    }

    /**
     * Method bubbleSort() is extension.
     *
     * <p>Sort Integer arrayList, String arrayList and Long arrayList in ascending order.</p>
     *
     * @param arrayList Integer arrayList of moves count.
     * @param nameList String arrayList of usernames.
     * @param timeList Long arrayList of time.
     * @since 2020/10/25
     */
    public void bubbleSort(ArrayList<Integer> arrayList,ArrayList<String> nameList,ArrayList<Long> timeList) {
        for (int i = 0; i < (arrayList.size()-1); i++) {
            for (int j = 0; j < (arrayList.size()-1-i); j++) {
                if (((arrayList.get(j) == arrayList.get(j+1))&&(timeList.get(j) > timeList.get(j+1)))||(arrayList.get(j) > arrayList.get(j + 1))) {
                    int temp = arrayList.get(j);
                    Long timeTemp = timeList.get(j);
                    String nameTemp = nameList.get(j);
                    arrayList.set(j,arrayList.get(j+1));
                    timeList.set(j,timeList.get(j+1));
                    nameList.set(j,nameList.get(j+1));
                    arrayList.set(j+1, temp);
                    timeList.set(j+1,timeTemp);
                    nameList.set(j+1,nameTemp);
                }
            }
        }
    }
}
