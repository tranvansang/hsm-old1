package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

import javax.swing.DefaultListModel;

public class SortedHedspiObjectsListModel<T extends HedspiObject> extends DefaultListModel<T> implements IObjectsContainer<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SortedList<T> sortedList;
	
	public SortedHedspiObjectsListModel(){
		sortedList = new SortedList<>();
	}

	@Override
	public void removeObject(T obj) {
		sortedList.remove(obj);
		updateModel();
	}

	private void updateModel() {
		super.removeAllElements();
		for(T it : sortedList)
			super.addElement(it);
	}

	@Override
	public void addObject(T obj) {
		sortedList.add(obj);
		updateModel();
	}

	@Override
	public void removeAll() {
		sortedList.clear();
		updateModel();
	}
	
}
