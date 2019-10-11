package rinky.functions;

public class Book {
   
	private int id;
	private String b_name;
	private String author;
	private int quantity;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public void display() {
		System.out.println("Book [id=" + this.getId() + ", b_name=" + this.getB_name() + ", author=" + this.getAuthor() + ", quantity=" + this.getQuantity() + "]");
		
	}
	
	
	
	
}
