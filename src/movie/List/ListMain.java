package movie.List;

import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListMain extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("movieList.fxml"));
		primaryStage.setTitle("영화 예매 프로그램");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setResizable(false); // 크기변경 권한 X
	}

}