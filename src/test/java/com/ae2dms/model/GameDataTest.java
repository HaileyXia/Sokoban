package com.ae2dms.model;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class {@link GameDataTest} to test Class {@link GameData}
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameDataTest {
    GameData gameData = new GameData();

    /**
     * <p>Test method readFromFile().</p>
     *
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    @Test
    public void testReadFromFile() throws IOException {
        FileReader fr = new FileReader("src/test/resources/data/debugLevel1.txt");
        ArrayList<Integer> arrayList = gameData.readFromFile(fr);
        ArrayList<Integer> array= new ArrayList<>(){{add(56);add(0);}};
        assertEquals(array,arrayList);
    }


    /**
     * <p>Test readNameFromFile()</p>
     *
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    @Test
    public void testReadNameFromFile() throws IOException {
        FileReader fr = new FileReader("src/test/resources/data/debugName1.txt");
        ArrayList<String> nameList = gameData.readNameFromFile(fr);
        ArrayList<String> array= new ArrayList<>(){{add("Dan");add(null);}};
        assertEquals(array,nameList);
    }

    /**
     * <p>Test readTimeFromFile()</p>
     *
     * @throws IOException Catch I/O exception.
     * @since 2020/10/25
     */
    @Test
    public void testReadTimeFromFile() throws IOException {
        FileReader fr = new FileReader("src/test/resources/data/debugTime1.txt");
        ArrayList<Long> timeList = gameData.readTimeFromFile(fr);
        ArrayList<Long> array= new ArrayList<>(){{add((long) 15);add(null);}};
        assertEquals(array,timeList);
    }

    /**
     * <p>Test getMinFromData()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testGetMinFromData() {
        ArrayList<Integer> arrayList= new ArrayList<>(){{add(5);add(2);add(3);add(1);add(4);}};
        int min = gameData.getMinFromData(arrayList);
        assertEquals(1,min);
    }

    /**
     * <p>Test bubbleSort()</p>
     *
     * @since 2020/10/25
     */
    @Test
    public void testBubbleSort() {
        ArrayList<Integer> arrayList= new ArrayList<>(){{add(5);add(2);add(3);add(1);add(4);}};
        ArrayList<String> nameList = new ArrayList<>(){{add("E");add("B");add("C");add("A");add("D");}};
        ArrayList<Long> timeList = new ArrayList<>(){{add((long) 12);add((long) 24);add((long) 13);add((long) 10);add((long) 15);}};

        gameData.bubbleSort(arrayList,nameList,timeList);
        ArrayList<Integer> sortArray= new ArrayList<>(){{add(1);add(2);add(3);add(4);add(5);}};
        ArrayList<String> sortName = new ArrayList<>(){{add("A");add("B");add("C");add("D");add("E");}};
        ArrayList<Long> sortTime = new ArrayList<>(){{add((long) 10);add((long) 24);add((long) 13);add((long) 15);add((long) 12);}};
        assertEquals(sortArray,arrayList);
        assertEquals(sortName,nameList);
        assertEquals(sortTime,timeList);
    }
}