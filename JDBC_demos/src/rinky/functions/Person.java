package rinky.functions;

public abstract class Person {

	static private long id = 0;
	private String name;
	private int age;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(String name,int age) {
		this.id= id++;
		this.name = name;
		this.age = age;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	abstract public void Panel();
	public abstract void login();
	
}
