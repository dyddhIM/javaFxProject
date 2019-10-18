package movie.Login;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController {

	@FXML PasswordField inputPassword;
	@FXML TextField inputId;
	@FXML StackPane stackPane;

	@FXML public void register() throws Exception {
		
		Parent newScene = FXMLLoader.load(getClass().getResource("../register/register.fxml"));

		// 처음 위치값을 제일 오른쪽으로 위치시킨다.
		newScene.setTranslateY(stackPane.getWidth());

		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(newScene.translateYProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

		stackPane.getChildren().add(newScene);
	}
	
	
	
	

	@FXML public void login() throws Exception {		//////////////// 영화 리스트 화면으로 전
		
		
		
		Parent newScene = FXMLLoader.load(getClass().getResource("../List/movieList.fxml"));

		// 처음 위치값을 제일 오른쪽으로 위치시킨다.
		newScene.setTranslateY(stackPane.getWidth());
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(newScene.translateYProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
		stackPane.getChildren().add(newScene);

	}

}
