package hotel;
	
import java.io.File;
import java.io.IOException;

import hotel.model.DatabaseManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
//import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private Stage primaryStage;
    private VBox rootLayout;
    class Delta { double x, y; } 
    DatabaseManager db = new DatabaseManager();
    
	public void start(Stage primaryStage) {
		db.createDB();
		this.primaryStage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.getIcons().add(new Image(new File("src/hotel/source/campana.png").toURI().toString()));

        initRootLayout(primaryStage);
	}
	
	public void initRootLayout(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/index.fxml"));
            rootLayout = (VBox) loader.load();
            
            final Delta dragDelta = new Delta();
            rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override public void handle(MouseEvent mouseEvent) {
                dragDelta.x = stage.getX() - mouseEvent.getScreenX();
                dragDelta.y = stage.getY() - mouseEvent.getScreenY();
              }
            });
            rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() + dragDelta.x);
                stage.setY(mouseEvent.getScreenY() + dragDelta.y);
              }
            });
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}

