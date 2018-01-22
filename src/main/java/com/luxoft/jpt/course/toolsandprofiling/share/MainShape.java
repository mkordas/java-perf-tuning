
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.share;

import java.util.HashMap;

import static com.luxoft.jpt.course.util.TestUtil.MB;


public class MainShape {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private HashMap<Cell, String> cellsMap;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        MainShape mainShape = new MainShape();
        mainShape.setCellsMap(new HashMap<Cell, String>());

        while (true) {
            Cell cell = new Cell(new String("Cell Key"));
            mainShape.addCellsMap(cell, new String("Cell Value"));
            System.out.printf("Left free memory [%,d] (MB) \n", (java.lang.Runtime.getRuntime().freeMemory() / MB));
        }

    }

    public HashMap<Cell, String> getCellsMap() {
        return cellsMap;
    }

    public void setCellsMap(HashMap<Cell, String> cellsMap) {
        this.cellsMap = cellsMap;
    }

    public void addCellsMap(Cell key, String value) {
        getCellsMap().put(key, value);
    }

}
