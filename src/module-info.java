module hotel_app {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.media;
	requires javafx.base;
	requires jakarta.xml.bind;
	
	opens hotel to javafx.graphics, javafx.fxml, javafx.media;
	opens hotel.controller to javafx.fxml, jakarta.xml.bind;
	opens hotel.model to javafx.base;
}
