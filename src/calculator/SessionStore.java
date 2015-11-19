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

	public boolean saveSession(Session session, String sessionId) throws SQLException {
		Statement statement = dbConnection.createStatement();
		boolean noError = true;
		int calculationId = 0;

		for (Pair<String, String> input : session) {

			String calculationInsertQuery = MessageFormat.format(
					"insert into calculations (history_id,input,result,session_id) values ({0}, {1}, {2}, {3});",
					"'" + String.valueOf(calculationId) + "'", "'" + input.getX() + "'",
					"'" + String.valueOf(input.getY()) + "'", "'" + sessionId + "'");

			boolean querySuccedeed = statement.execute(calculationInsertQuery.toString());

			if (!querySuccedeed) {
				noError = false;
				break;

			}
			++calculationId;
		}

		statement.close();
		return noError;

	}

	public Session loadSessionWithId(String Id) throws SQLException {

		Session restoredSession = null;
		Statement statement = dbConnection.createStatement();
		// Object[] arguments = new Object[]{ Id };
		String query = MessageFormat.format("select input,result from calculations where session_id={0};",
				"'" + Id + "'");

		ResultSet queryResult = statement.executeQuery(query);
		if (queryResult != null) {
			restoredSession = new Session();
			while (queryResult.next()) {
				restoredSession.addCalculusToHistory(queryResult.getString("input"), queryResult.getString("result"));
			}

		}
		return restoredSession;
	}

	public boolean hasSessionWithId(String id) {
		return true;
	}
}
