/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import javax.swing.JComboBox;

/**
 *
 * @author Tomek
 */
public class JPatternComboBox extends JComboBox{
   
    /** Constructs a ComboBox for the given array of items.
     * @param items */
public JPatternComboBox(String[] items) {
  super(items);
}

/** Flag indicating that item was set by program. */
public boolean isSetByProgram;

    JPatternComboBox() {
        
    }

/** Do not fire if set by program. */
@Override
protected void fireActionEvent() {
  if (isSetByProgram)
    return;
  super.fireActionEvent();
}

/** Sets selected Object item without firing Action Event.
     * @param item */
public void setSelection(Object item) {
  isSetByProgram = true;
  setSelectedItem(item);
  isSetByProgram = false;
}

/** Sets selected index without firing Action Event.
     * @param index */
public void setSelection(int index) {
  isSetByProgram = true;
  setSelectedIndex(index);
  isSetByProgram = false;
}
}
