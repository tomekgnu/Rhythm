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
}
