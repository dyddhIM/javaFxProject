package movie.register;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RegisterController implements Initializable {

	@FXML
	TextField inputID; // 아이디
	@FXML
	TextField inputPhNumber; // 핸드폰 번호
	@FXML
	PasswordField inputPassword1; // 비밀번호

	@FXML
	TextField inputName; // 이름

	@FXML
	StackPane stackPane;

	Connection connection = null;
	PreparedStatement pre = null;

	private Optional<ButtonType> alert;
	int cunum = 0;

	@FXML
	public void idCheck() { // 아이디 중복확인

	}

	@FXML
	public void registerfinally() throws Exception { // 회원가입 완료
		String cuName = inputName.getText();
		String cuId = inputID.getText();
		String cuPw = inputPassword1.getText();
		String cuPh = inputPhNumber.getText();

		if (cuName.length() != 0) {
		} else {
			alert = new Alert(AlertType.WARNING, "이름을 입력하세요", ButtonType.OK).showAndWait();
			return;
		}
		if (cuId.length() != 0) {
		} else {
			alert = new Alert(AlertType.WARNING, "아이디를 입력하세요", ButtonType.OK).showAndWait();
			return;

		}

		if (cuPw.length() != 0) {
		} else {
			alert = new Alert(AlertType.WARNING, "비밀번호를 입력하세요", ButtonType.OK).showAndWait();
			return;
		}
		if (cuPh.length() != 0) {
		} else {
			alert = new Alert(AlertType.WARNING, "핸드폰 번호를 입력하세요", ButtonType.OK).showAndWait();
			return;
		}
		try {
			System.out.println("db올라감");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "oracle");
			pre = connection.prepareStatement("insert into customer values(?,?,?,?,?)");

			pre.setInt(1, cunum);
			pre.setString(2, cuName);
			pre.setString(3, cuId);
			pre.setString(4, cuPw);
			pre.setString(5, cuPh);
			pre.executeUpdate();
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				connection.close();
				pre.close();
				alert = new Alert(AlertType.CONFIRMATION, "회원가입이 완료 되었습니다", ButtonType.OK).showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		cunum = cunum + 1;

		
		 //---------------------로그인 창으로 이동----------- 
		 Parent newScene = FXMLLoader.load(getClass().getResource("../Login/login.fxml"));
		 
		 newScene.setTranslateY(stackPane.getWidth());
		  
		  Timeline timeline = new Timeline(); KeyValue keyValue = new
		  KeyValue(newScene.translateYProperty(), 0); KeyFrame keyFrame = new
		  KeyFrame(Duration.millis(10), keyValue);
		  timeline.getKeyFrames().add(keyFrame); timeline.play();
		  
		  stackPane.getChildren().add(newScene);
			}

	@FXML
	public void cencle() throws Exception { // 회원가입 취소
		Parent newScene = FXMLLoader.load(getClass().getResource("../Login/login.fxml"));

		newScene.setTranslateY(stackPane.getWidth());

		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(newScene.translateYProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

		stackPane.getChildren().add(newScene);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
