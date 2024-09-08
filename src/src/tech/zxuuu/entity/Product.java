package src.tech.zxuuu.entity;

/**
 * 货物类（商店用）
 */
public class Product {

	private String name;
	private String type;
	private float price;
	private String picture;
	private int number;
	private String information;
//	private int heat; //新加 热度，热度越高，排名越靠前

	public Product() {
	}

	public Product(String name, String type, float price, String picture, int number, String information,int heat) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.picture = picture;
		this.number = number;
		this.information = information;
//		this.heat = heat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", type=" + type + ", price=" + price + ", picture=" + picture + ", number="
				+ number + ", information=" + information + "]";
	}

}
