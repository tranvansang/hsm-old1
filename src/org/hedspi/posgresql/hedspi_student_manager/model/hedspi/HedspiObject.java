package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

public class HedspiObject {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HedspiObject other = (HedspiObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private String id;
	
	public HedspiObject(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	public boolean equals(Object obj){
//		return this.hashCode() == obj.hashCode();
//	}
//	
//	public int hashCode(){
//		return id.hashCode();
//	}
//	
	public String toString(){
		return id;
	}

}