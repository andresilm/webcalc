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
			dbConnection = DriverManager.getConnection("jdbc:sqlite:webcalc.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public boolean saveSession(Session session, String sessionId) throws SQLException {
		Statement statement = dbConnection.createStatement();
		boolean noError = true;

		for (Pair<String, String> input : session) {

			Object[] values = new Object[] { input.getX(), String.valueOf(input.getY()), sessionId };

			String calculationInsertQuery = MessageFormat
					.format("insert into calculations values ('{0}', '{1}', '{2}');", values);

			boolean querySuccedeed = statement.execute(calculationInsertQuery.toString());

			if (!querySuccedeed) {
				noError = false;
				break;

			}
		}

		statement.close();
		return noError;

	}

	public Session loadSessionWithId(String Id) throws SQLException {

		Session restoredSession = null;
		Statement statement = dbConnection.createStatement();
		Object[] arguments = new Object[]{ Id };
		String query = MessageFormat.format("select input,result from calculations where session_id='{0}';", arguments);
		ResultSet queryResult = statement.executeQuery(query);
		if (queryResult != null) {
			restoredSession = new Session();
			while (queryResult.next()) {
				restoredSession.addCalculusToHistory(queryResult.getString(0), queryResult.getString(1));
			}

		}
		return restoredSession;
	}

	public boolean hasSessionWithId(String id) {
		return true;
	}
}
