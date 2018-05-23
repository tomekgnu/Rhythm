/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import javax.swing.JTable;

/**
 *
 * @author Tomek
 */
public class JSequenceTable extends JTable {

    
    private int currentColumn;
    private int currentRow;
    private int totalPatterns;
    private int listIndex;
    
    public int getListIndex() {
        return listIndex;
    }
    public void setListIndex(int i){
        this.listIndex = i;
    }
    
    public int getTotalPatterns() {
        return totalPatterns;
    }
   
    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
    
    public void restructure(int x,int y){
        int rows = this.getRowCount();
        int columns = this.getColumnCount();
        int row = x;
        int col = y;
                
        for(int index = row * columns + col;index < rows * columns; index++){
            int cury = index % columns;
            int curx = index / columns;
            int tmpy = (index + 1) % columns;
            int tmpx = (index + 1) / columns;
            Object tmp = this.getValueAt(tmpx, tmpy);
            this.setValueAt(tmp, curx,cury);
        }
    }

    void insertPattern() {
        this.setValueAt("x", currentRow, currentColumn);
        totalPatterns++;        
    }
    
    void clearPattern(){
        this.setValueAt(null, currentRow, currentColumn);
        if(totalPatterns > 0)
            totalPatterns--;
    }

    
}
