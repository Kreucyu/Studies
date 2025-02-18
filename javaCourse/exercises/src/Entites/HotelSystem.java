package Entites;

public class HotelSystem {
	private String name;
	private String email;
	private int roomSelected;

	public HotelSystem() {

	}

	public HotelSystem(String name, String email, int roomSelected) {
		this.name = name;
		this.email = email;
		this.roomSelected = roomSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(int roomSelected) {
		this.roomSelected = roomSelected;
	}
	
}
