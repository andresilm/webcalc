package calculator;

import java.sql.*;

public class SessionStore {
	private static SessionStore instance = null;

	public static SessionStore getInstance() {
		if (instance == null) {
			instance = new SessionStore();
		}
		return instance;
	}

	private SessionStore() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:webcalc.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void saveSession(Session s) {
		
	}
	
	public Session loadSessionWithId(int Id) {
		return null;
	}

	public boolean hasSessionWithId(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
