package calculator;

import java.sql.*;
import java.text.MessageFormat;

import util.Pair;

public class SessionStore {
	private static SessionStore instance = null;

	static Connection dbConnection = null;

	public static SessionStore getInstance() {
		if (instance == null) {
			instance = new SessionStore();

		}
		return instance;
	}

	private SessionStore() {
		dbConnection = null;

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:db/webcalc.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void saveSession(Calculations session, String sessionId) throws SQLException {

		
		int calculationId = 0;

		if (hasSessionWithId(sessionId)) {
			deleteSession(sessionId);
		}

		for (Pair<String,String> input : session) {
			Statement statement = dbConnection.createStatement();
			String calculationInsertQuery = MessageFormat.format(
					"insert into calculations (history_id,input,result,session_id) values ({0}, {1}, {2}, {3});",
					"'" + String.valueOf(calculationId) + "'", "'" + input.getX() + "'",
					"'" + String.valueOf(input.getY()) + "'", "'" + sessionId + "'");

			statement.execute(calculationInsertQuery.toString());

			++calculationId;
			statement.close();
		}

	}

	private void deleteSession(String sessionId) throws SQLException {
		
		PreparedStatement statement = this.dbConnection.prepareStatement("delete from calculations where session_id=?;");
		statement.setString(1, sessionId);

		statement.execute();

		statement.close();

	}

	public Calculations loadSessionWithId(String id) throws SQLException {

		Calculations restoredSession = null;
		
		
		PreparedStatement prepStatement = this.dbConnection.prepareStatement("select input,result from calculations where session_id=?;");
		prepStatement.setString(1, id);

		ResultSet queryResult = prepStatement.executeQuery();
		
			restoredSession = new Calculations();
		
			while (queryResult.next()) {
				restoredSession.addCalculusToHistory(queryResult.getString("input"), queryResult.getString("result"));
			}

		
		return restoredSession;
	}

	public boolean hasSessionWithId(String id) throws SQLException {
		PreparedStatement prepStatement = this.dbConnection.prepareStatement("select input from calculations where session_id=?;");
		prepStatement.setString(1, id);
		ResultSet queryResult = prepStatement.executeQuery();

		return queryResult.next();

	}
}
