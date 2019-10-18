package movie.List;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ListController implements Initializable {

	@FXML
	TableView<ListBean> resultView;
	@FXML
	TableColumn<ListBean, String> movieName;

	@FXML
	StackPane stackPane;
	private Object oldValue;
	private Optional<ButtonType> buttonType;
	@FXML
	public void select() throws Exception {
		
		if(resultView.getSelectionModel().getSelectedItem() == null) {
		buttonType = new Alert(AlertType.WARNING, "영화를 선택하세요!", ButtonType.OK).showAndWait();
		}
			
		//리스트 뷰의 클릭을 인식하고 버튼을  클릭시 팝업 뜨기
	else {
		buttonType= new Alert(AlertType.CONFIRMATION,
				"선택한 영화를 예매 하시겠습니까?", ButtonType.YES,ButtonType.NO).showAndWait(); 
		// 팝업에서 Yse를 누를시 발생 되는 이벤트
		if (buttonType.get() == ButtonType.YES) {
			Parent newScene = FXMLLoader.load(getClass().getResource("../Time/time.fxml"));
			Scene scene = new Scene(newScene);
		
			
			newScene.setTranslateY(stackPane.getWidth());

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(newScene.translateYProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();

			stackPane.getChildren().add(newScene);
			

		}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		movieName.setCellValueFactory(new PropertyValueFactory<ListBean, String>("movieName"));

		ListService service = new ListService();
		List<ListBean> list = service.selectMemberList();
		ObservableList<ListBean> observableArrayList = FXCollections.observableArrayList(list);
		resultView.setItems(observableArrayList);
		
		// tableView에 두번 클릭시 버튼 선택 해제 
		resultView.setOnMouseClicked(event -> {
            if(resultView.getSelectionModel().getSelectedItem() != null) {
                if (event.getPickResult().getIntersectedNode().equals(oldValue)) {
                	resultView.getSelectionModel().clearSelection();
                    oldValue = null;
                } else {
                    oldValue = event.getPickResult().getIntersectedNode();
                }
            }
        });


		
		
	}

	@FXML public void logout() throws Exception {
		Parent newScene = FXMLLoader.load(getClass().getResource("../Login/login.fxml"));
		
		newScene.setTranslateY(stackPane.getWidth());

		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(newScene.translateYProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

		stackPane.getChildren().add(newScene);

	}

}
