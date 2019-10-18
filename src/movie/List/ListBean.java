package movie.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListBean {
	private StringProperty movieName;

	public ListBean(String movieName) {
		this.movieName =new SimpleStringProperty(movieName);
	}

	public String getMovieName() {
		return movieName.getValue();
	}

	public void setMovieName(String movieName) {
		this.movieName.set(movieName);
	}


}
