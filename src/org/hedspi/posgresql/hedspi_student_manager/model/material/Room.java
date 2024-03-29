package org.hedspi.posgresql.hedspi_student_manager.model.material;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Room extends HedspiObject {
	
	public static final String ID_CODE = "RM#";
	public static final String NAME_CODE = "Name";

	public Room(String id) {
		super(id);
	}
	
	private String name;

	public Room(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
