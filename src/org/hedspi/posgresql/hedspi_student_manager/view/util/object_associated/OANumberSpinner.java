package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OANumberSpinner<T> extends OAComponentAbstract<String, T> {
	
	JSpinner spinner;
	public JSpinner getSpinner() {
		return spinner;
	}

	SpinnerNumberModel model;
	
	private OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object,
			SpinnerNumberModel model) {
		super(objectUpdater, object);
		this.model = model;
		spinner = new JSpinner(model);
		model.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateObjectValue();
			}
		});
	}

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater, int defaultValue, int minValue, int maxValue, int step) {
		this(objectUpdater, null, defaultValue, minValue, maxValue, step);
	}

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object, int defaultValue, int minValue, int maxValue, int step) {
		this(objectUpdater, object, new SpinnerNumberModel(defaultValue, minValue, maxValue, step));
	}
	
	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object){
		this(objectUpdater, object, new SpinnerNumberModel());
	}
	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater){
		this(objectUpdater, null);
	}

	@Override
	public String getValue() {
		return String.valueOf(model.getNumber());
	}

	@Override
	public void setValue(String value) {
		try{
			int val = Integer.parseInt(value);
			model.setValue(val);
		}catch(Exception e){
			
		}
	}
}
