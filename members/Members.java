package members;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Members {

	static DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static int nextId = 1;
	private Integer id;
	private String name;
	private LocalDate age;
	private String position;
	private String status;

	public Members() {

	}

	public Members(String name, LocalDate age, String position, String status) {
		this.id = nextId;
		this.name = name;
		this.age = age;
		this.position = position;
		this.status = status;
		nextId++;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "COD: " + id + "\n"
	+ "NOME: " + name + "\n" 
	+ "IDADE: " + age.format(dt) + "\n" 
	+ "STATUS: " + status + "\n" 
	+ "--------------------------" + "\n";
	}

}
