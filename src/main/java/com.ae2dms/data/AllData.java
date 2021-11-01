package com.ae2dms.data;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface {@link AllData} is maintenance using Factory Pattern.
 *
 * <p>This type of design pattern is a creation pattern that provides an optimal way to create objects.
 * In the factory pattern{@link DataFactory}, we do not expose the creation logic to clients when we create objects,
 * and we point to newly created objects by using a common interface.</p>
 *
 * <p>Create a AllData interface and the entity class that implements the AllData interface.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public interface AllData {
    /**
     * <p>entity class</p>
     *
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public void readMove() throws IOException;

    /**
     * <p>entity class</p>
     *
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public void readName() throws IOException;

    /**
     * <p>entity class</p>
     *
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public void readTime() throws IOException;

    /**
     * <p>entity class, get arrayList</p>
     *
     * @return ArrayList
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public ArrayList<Integer> getArrayList() throws IOException;

    /**
     * <p>entity class, get nameList</p>
     *
     * @return ArrayList
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public ArrayList<String> getNameList() throws IOException;

    /**
     * <p>entity class, get timeList</p>
     *
     * @return ArrayList
     * @throws IOException catch I/O exception
     * @since 2020/10/25
     */
    public ArrayList<Long> getTimeList() throws IOException;
}
