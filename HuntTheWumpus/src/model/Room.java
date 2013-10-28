package model;

public class Room {
	private boolean hasPit;
	private boolean hasBlood;
	private boolean hasSlime;
	private boolean hasGoop;
	private boolean hasHunter;
	private boolean hasWumpus;
	private boolean isVisible;
	
	/*
	 * Everything below here is getters and setters
	 */
	
	/**
	 * @return the hasPit
	 */
	public boolean hasPit() {
		return hasPit;
	}
	/**
	 * @param hasPit the hasPit to set
	 */
	public void setPit(boolean hasPit) {
		this.hasPit = hasPit;
	}
	/**
	 * @return the hasBlood
	 */
	public boolean hasBlood() {
		return hasBlood;
	}
	/**
	 * @param hasBlood the hasBlood to set
	 */
	public void setBlood(boolean hasBlood) {
		this.hasBlood = hasBlood;
	}
	/**
	 * @return the hasSlime
	 */
	public boolean hasSlime() {
		return hasSlime;
	}
	/**
	 * @param hasSlime the hasSlime to set
	 */
	public void setSlime(boolean hasSlime) {
		this.hasSlime = hasSlime;
	}
	/**
	 * @return the hasGoop
	 */
	public boolean hasGoop() {
		return hasGoop;
	}
	/**
	 * @param hasGoop the hasGoop to set
	 */
	public void setGoop(boolean hasGoop) {
		this.hasGoop = hasGoop;
	}
	/**
	 * @return the hasHunter
	 */
	public boolean hasHunter() {
		return hasHunter;
	}
	/**
	 * @param hasHunter the hasHunter to set
	 */
	public void setHunter(boolean hasHunter) {
		this.hasHunter = hasHunter;
	}
	/**
	 * @return the hasWumpus
	 */
	public boolean hasWumpus() {
		return hasWumpus;
	}
	/**
	 * @param hasWumpus the hasWumpus to set
	 */
	public void setWumpus(boolean hasWumpus) {
		this.hasWumpus = hasWumpus;
	}
	/**
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * @param isVisible the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
