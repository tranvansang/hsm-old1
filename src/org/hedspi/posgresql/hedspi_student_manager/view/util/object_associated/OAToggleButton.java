package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

public class OAToggleButton<T> extends OAComponentAbstract<String, T> {
	
	private final static String TRUE = "true";
	private final static String FALSE = "false";
	
	JToggleButton toggleButton;

	public JToggleButton getToggleButton() {
		return toggleButton;
	}

	public OAToggleButton(IObjectUpdater<T, String> objectUpdater, T object) {
		super(objectUpdater, object);
		toggleButton = new JToggleButton();
		toggleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateObjectValue();
			}
		});
	}

	public OAToggleButton(IObjectUpdater<T, String> objectUpdater) {
		this(objectUpdater, null);
	}

	@Override
	public String getValue() {
		if (toggleButton.isSelected())
			return TRUE;
		return FALSE;
	}

	@Override
	public void setValue(String value) {
		toggleButton.setSelected(value.equals(TRUE));
	}
}
