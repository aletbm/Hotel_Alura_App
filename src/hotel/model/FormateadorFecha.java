package hotel.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormateadorFecha {
	private String patternIn = "d/M/y";
	private String patternOut = "yyyy-MM-dd";
	
	public FormateadorFecha() {}
	
	public FormateadorFecha(String patternIn, String patternOut) {
		this.patternIn = patternIn;
		this.patternOut = patternOut;
	}
	
	public String parse(String date) {
		DateTimeFormatter dateFormatterIn = DateTimeFormatter.ofPattern(this.getPatternIn());
		DateTimeFormatter dateFormatterOut = DateTimeFormatter.ofPattern(this.getPatternOut());
		if (date != null && !date.isEmpty()) {
	         LocalDate dateIn = LocalDate.parse(date, dateFormatterIn);
	         return dateIn.format(dateFormatterOut);
	    }
		else {
			return null;
		}
	}

	public String getPatternIn() {
		return patternIn;
	}

	public void setPatternIn(String patternIn) {
		this.patternIn = patternIn;
	}

	public String getPatternOut() {
		return patternOut;
	}

	public void setPatternOut(String patternOut) {
		this.patternOut = patternOut;
	}
}
