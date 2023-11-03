package out.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutLog {

	DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private LocalDateTime date;
	private String title;
	private Double value;

	public OutLog() {

	}
	
	public OutLog(LocalDateTime date, String title, Double value) {
		this.date = date;
		this.title = title;
		this.value = value;
	}

	public LocalDateTime getDate() {
		return date;
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
		return "Data saída: " + date.format(dt) + "\n" + "Título: " + title + "\n" + "Valor: R$ "
				+ String.format("%.2f", value) + "\n" + "------------------------------------" + "\n";
	}

}
