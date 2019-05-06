package model;

public class Slot {

	private Chip status; // a status is stored in the slot

	public Slot() {
		this.status = Chip.EMPTY; // On creation Slots are defined as empty
	}

	public void setStatus(Chip status) {
		this.status = status;
	}

	public Chip getStatus() {
		return this.status;
	}

}
