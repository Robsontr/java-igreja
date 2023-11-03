package entry.log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntryLog {
	
	DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private LocalDate date;
	private String title;
	private Double value;

	public EntryLog() {

	}

	public EntryLog(LocalDate date, String title, Double value) {
		this.date = date;
		this.title = title;
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
		
	public String toString() {
		return 	"Data entrada: " + date.format(dt) + "\n"
				+ "Título: " + title + "\n"
				+ "Valor: R$" + String.format("%.2f",value) + "\n"
				+ "------------------------------------" + "\n";
	}

}
