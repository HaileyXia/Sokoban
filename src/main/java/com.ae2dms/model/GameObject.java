package com.ae2dms.model;

/**
 * Enum GameObject{@link GameObject} is maintenance.
 *
 * <p>{@link GameObject} represents the objects in a game.
 * Each object has a symbol which is used to decode the save files.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public enum GameObject {
    /**
     * W represent WALL
     */
    WALL('W'),
    /**
     * " " represent FLOOR
     */
    FLOOR(' '),
    /**
     * C represent CRATE
     */
    CRATE('C'),
    /**
     * D represent DIAMOND
     */
    DIAMOND('D'),
    /**
     * S represent KEEPER
     */
    KEEPER('S'),
    /**
     * O represent CRATE_ON_DIAMOND
     */
    CRATE_ON_DIAMOND('O'),
    /**
     * = represent DEBUG_OBJECT
     */
    DEBUG_OBJECT('=');

    /**
     * char symbol to represents the objects in a game.
     */
    public final char symbol;

    /**
     * Method GameObject(final char symbol) is extension.
     *
     * <p>Set char symbol </p>
     *
     * @param symbol char symbol to represents the objects in a game.
     * @since 2020/10/25
     */
    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * Method fromChar(char c) is extension.
     *
     * <p>Returns the enum associated with a char.
     * If the char is not associated with any enum, it will return WALL as default value.</p>
     *
     * @param c symbol char
     * @return the {@link GameObject} corresponding to the char
     * @since 2020/10/25
     */
    public static GameObject fromChar(char c) {
        for (GameObject t : GameObject.values()) {
            if (Character.toUpperCase(c) == t.symbol) {
                return t;
            }
        }
        return WALL;
    }

    /**
     * Method getStringSymbol() is maintenance.
     *
     * <p>Get the string representation of the symbol.</p>
     *
     * @return the string representation of the symbol.
     * @since 2020/10/25
     */
    public String getStringSymbol() {
        return String.valueOf(symbol);
    }

    /**
     * Method getCharSymbol() is maintenance.
     *
     * <p>Get the char representation of the symbol.</p>
     *
     * @return the char representation of the symbol
     * @since 2020/10/25
     */
    public char getCharSymbol() {
        return symbol;
    }
}