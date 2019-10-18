package movie.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ListService {
	public List<ListBean> selectMemberList() {
		List<ListBean> list = new ArrayList<ListBean>();
		
		Connection connection = null;
		Statement statement = null;

		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","java","oracle");
//			preparedStatement = connection.prepareStatement("SELECT NAME FROM MOVIE");
//			
//			resultSet = preparedStatement.executeQuery();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT NAME FROM MOVIE");
			while (resultSet.next()) {
				String movieName = resultSet.getString("NAME");
				list.add(new ListBean(movieName));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	
	}


	
}
