package Servlets;

public class Product {
	private String Id;
	private String Name;
	private String Type;
	private int Price;
	public Product() {
		
	}
	public Product(String id, String name, String type, int price, String image) {
		super();
		Id = id;
		Name = name;
		Type = type;
		Price = price;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "Product [Id=" + Id + ", Name=" + Name + ", Type=" + Type + ", Price=" + Price + "]";
	}
	
}
