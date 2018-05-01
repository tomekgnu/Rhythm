package misc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableRowColor2 extends JFrame
    {
     public TableRowColor2()
         {
             JTable table=new JTable(new DefaultTableModel(new Object[][]{
             {"1","2","3","4"},
             {"2","3","4","5"},
             {"3","4","5","6"},
         {"4","5","6","7"}},
         new Object[]{"A","B","C","D"}));
        
         ColorRenderer cr=new ColorRenderer(2,1);
        
         //Adding this line of code will color only the first column and different colors for
         //each row and dont forget to comment the for loop


         table.getColumnModel().getColumn(0).setCellRenderer(cr);
             table.getColumnModel().getColumn(1).setCellRenderer(cr);
             table.getColumnModel().getColumn(2).setCellRenderer(cr);
             table.getColumnModel().getColumn(3).setCellRenderer(cr);
             


         JScrollPane scroll=new JScrollPane(table);
         this.setContentPane(scroll);
         this.setBounds(100,50,300,150);
     }
     public static void main (String arg[])
         {
         TableRowColor2 tes = new TableRowColor2();
         tes.setVisible(true);
         tes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
}


class ColorRenderer extends JLabel implements TableCellRenderer
    {
     private String columnName;
        int row1;
        int column1;
     public ColorRenderer(int row1, int column1)
         {
         this.row1 = row1;
             this.column1 = column1;
        // this.columnName = column;
         setOpaque(true);
     }
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
         {
             if (row == row1 && column == column1)setBackground(Color.red);
             else  if(row == 0)
                 setBackground(Color.gray);
             else
                 setBackground(Color.blue);
               /*
         Object columnValue=table.getValueAt(row,table.getColumnModel().getColumnIndex(columnName));

         if (value != null) setText(value.toString());
         if(isSelected)
             {
             setBackground(table.getSelectionBackground());
             setForeground(table.getSelectionForeground());
         }
         else
             {
             setBackground(table.getBackground());
             setForeground(table.getForeground());


             if (columnValue.equals("1")) setBackground(java.awt.Color.pink);
             if (columnValue.equals("2")) setBackground(java.awt.Color.green);
             if (columnValue.equals("3")) setBackground(java.awt.Color.red);
             if (columnValue.equals("4")) setBackground(java.awt.Color.blue);

         }
             */
         return this;
     }
}