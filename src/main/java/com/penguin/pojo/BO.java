package com.penguin.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class BO implements Serializable {
    static ArrayList<short[]> b;

    public static ArrayList<short[]> getBlocks() {
        return b;
    }

    public static void setBlocks(ArrayList<short[]> b) {
        BO.b = b;
    }
}
