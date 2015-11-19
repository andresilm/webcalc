package calculator;

import java.sql.*;
import java.text.MessageFormat;

import util.Pair;

public class SessionStore {
	private static SessionStore instance = null;
	private static int nrSessionsInDb = -1;

	static Connection dbConnection = null;
	final String sessionsInsertQuery = "insert into sessions default values";

	public static SessionStore getInstance() {
		if (instance == null) {
			instance = new SessionStore();
			try {
				nrSessionsInDb = getNumberOfStoredSessions();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	private static int getNumberOfStoredSessions() throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet queryResult = statement.executeQuery("select count(id) from sessions");

		return queryResult.getInt(0);
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

	public boolean saveSession(Session session, int sessionId) throws SQLException {
		Statement statement = dbConnection.createStatement();
		boolean noError = true;
		if (statement.execute(this.sessionsInsertQuery)) {

			for (Pair<String, String> input : session) {

				Object[] values = new Object[] { input.getX(), String.valueOf(input.getY()),
						String.valueOf(SessionStore.nrSessionsInDb - 1) };
				++SessionStore.nrSessionsInDb;

				String calculationInsertQuery = MessageFormat
						.format("insert into calculations values ('{0}', '{1}', '{2}')", values);

				boolean querySuccedeed = statement.execute(calculationInsertQuery.toString());

				if (!querySuccedeed) {
					noError = false;
					break;

				}
			}

		}
		statement.close();
		return noError;

	}

	public Session loadSessionWithId(int Id) throws SQLException {

		Session restoredSession = null;
		Statement statement = dbConnection.createStatement();
		ResultSet queryResult = statement.executeQuery("select input,result from calculations where session_id=" + Id);
		if (queryResult != null) {
			restoredSession = new Session();
			while (queryResult.next()) {
				restoredSession.addCalculusToHistory(queryResult.getString(0), queryResult.getString(1));
			}

		}
		return restoredSession;
	}

	public boolean hasSessionWithId(int id) {
		return true;
	}
}
