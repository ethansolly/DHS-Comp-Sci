package com.ethansolly.cancer;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;

/**Best class ever
 */
public class InternalFrameInternalFrameTitlePaneInternalFrameTitlePaneMaximizeButtonWindowNotFocusedState extends State {
	InternalFrameInternalFrameTitlePaneInternalFrameTitlePaneMaximizeButtonWindowNotFocusedState() {
		super("WindowNotFocused");
	}
		
	@Override protected boolean isInState(JComponent c) {
		Component parent = c;
		while (parent.getParent() != null) {
		   if (parent instanceof JInternalFrame) {
			   break;
		   }
		   parent = parent.getParent();
		}
		
		if (parent instanceof JInternalFrame) {
			return !(((JInternalFrame)parent).isSelected());
		}
		
		return false;
    }
}
