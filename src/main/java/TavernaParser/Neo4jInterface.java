package TavernaParser;

import org.neo4j.driver.v1.*;

public abstract class Neo4jInterface {
    private static boolean logging;
    private static Driver driver;
    private static Session session;

    protected static void initializeDb(String uri, String username, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public static void closeDbConnection() {
        driver.close();
    }

    public static void initializeSession() {
        session = Neo4jInterface.driver.session();
    }

    public static void closeSession() {
        session.close();
    }

    public static StatementResult run(String query) {
        return session.run(query);
    }

    protected static boolean logging() {
        return logging;
    }

    protected static void logging(boolean value) {
        logging = value;
    }

    protected static String sanitize(String string) {
        return string.replaceAll("\"([^\"]+)\":", "$1:").replaceAll("\\\\", "");
    }
}
