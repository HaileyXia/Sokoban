package com.ae2dms.data;

/**
 * Class {@link DataFactory} is maintenance using Factory Design Pattern.
 *
 * <p>Create a data factory that generates objects based on the entity class given information.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class DataFactory {
    /**
     * Method getData(int levelIndex) is extension of Factory design pattern.
     *
     * <p>Use the getData method to get an object of the AllData type</p>
     *
     * @param levelIndex index of current level
     * @return Specific object of AllData
     * @since 2020/10/25
     */
    public AllData getData(int levelIndex){
        switch (levelIndex){
            case 1: return new Level1Move();
            case 2: return new Level2Move();
            case 3: return new Level3Move();
            case 4: return new Level4Move();
            case 5: return new Level5Move();
            case 6: return new Level6Move();
        }
        return null;
    }
}
