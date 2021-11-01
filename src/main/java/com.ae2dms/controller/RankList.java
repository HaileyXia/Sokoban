package com.ae2dms.controller;

import javafx.beans.property.*;

/**
 * Class {@link RankList} is used for extension and improve encapsulation of private parameter.
 *
 * <p>Is called by Class {@link RankController}, method packData.
 * It is used to get private parameter move, name, time and index to pass value to class {@link RankController} </p>
 *
 * @since 2020/10/25
 */
public class RankList {
    private IntegerProperty moves;
    private StringProperty name;
    private LongProperty time;
    private IntegerProperty index;

    /**
     * RankList(Integer moves, String name,Long time) is extension, to improve encapsulation.
     *
     * <p>Pass the parameter of name,moves and time.</p>
     *
     * @param moves moves count in each level
     * @param name name of the user
     * @param time time of the user play each round of the game
     * @param index rank of all the user
     * @since 2020/10/25
     */
    public RankList(Integer moves, String name,Long time,Integer index){
        super();
        this.moves = new SimpleIntegerProperty(moves);
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleLongProperty(time);
        this.index = new SimpleIntegerProperty(index);
    }

    /**
     * <p>Get moves</p>
     *
     * @return IntegerProperty moves count
     * @since 2020/10/25
     */
    public IntegerProperty getMoves() {
        return moves;
    }

    /**
     * <p>Set moves</p>
     *
     * @param moves moves count in each level
     * @since 2020/10/25
     */
    public void setMoves(Integer moves) {
        this.moves = new SimpleIntegerProperty(moves);
    }

    /**
     * <p>Get user name</p>
     *
     * @return StringProperty user name
     * @since 2020/10/25
     */
    public StringProperty getRankName() {
        return name;
    }

    /**
     * <p>Set user name</p>
     *
     * @param name username
     * @since 2020/10/25
     */
    public void setRankName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * <p>Get time</p>
     *
     * @return LongProperty time of the user play the game
     * @since 2020/10/25
     */
    public LongProperty getTime() { return time; }

    /**
     * <p>Set time</p>
     *
     * @param time time of the user play the game
     * @since 2020/10/25
     */
    public void setTime(Long time) { this.time = new SimpleLongProperty(time); }

    /**
     * <p>Get index</p>
     *
     * @return IntegerProperty index(rank) of all the user.
     * @since 2020/10/25
     */
    public IntegerProperty getIndex() { return index; }

    /**
     * <p>Set index</p>
     *
     * @param index index(rank) of all the user.
     * @since 2020/10/25
     */
    public void setIndex(Integer index) { this.index = new SimpleIntegerProperty(index); }
}
