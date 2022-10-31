package KLibrary.utils;

import java.sql.*;

/**
 * This class provides methods for secure SQL statements (preventing SQL-injection)<br>
 * A part of the (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 *
 * @version 1.2.0 | last edit: 28.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class SQLUtils {

    private Connection con;

    /**
     * Creates an instance of this class and establishes a database connection.
     *
     * @param pDBPath Path to SQLite database you want to connect to
     *
     * @see #setDatabase(String)
     * */
    public SQLUtils(String pDBPath) throws SQLException {
        setDatabase(pDBPath);
    }


    /**
     * Creates an instance of this class and establishes a database connection.
     *
     * @param pDatabaseUrl  Host address
     * @param pUsername     Name of the user you want to connect as
     * @param pPassword     Password
     *
     * @see #setDatabase(String)
     * */
    public SQLUtils(String pDatabaseUrl, String pUsername, String pPassword) throws SQLException {
        setDatabase(pDatabaseUrl, pUsername, pPassword);
    }

    private PreparedStatement addStatementVariables(String pStatement, Object... pSet) throws SQLException
    {
        PreparedStatement lStmt = con.prepareStatement(pStatement);
        addStatementVariables(lStmt,pSet);
        return lStmt;
    }

    private void addStatementVariables(PreparedStatement pStatement, Object... pSet) throws SQLException
    {
        if (pSet == null) return;
        for (int i = 0; i < pSet.length; i++)
        {
            if (Blob.class.equals(pSet[i].getClass())) pStatement.setBlob(i + 1, (Blob) pSet[i]);
            else if (byte[].class.equals(pSet[i].getClass())) pStatement.setBytes(i + 1, (byte[]) pSet[i]);
            else if (byte.class.equals(pSet[i].getClass())) pStatement.setByte(i + 1, (byte) pSet[i]);
            else if (String.class.equals(pSet[i].getClass())) pStatement.setString(i + 1, (String) pSet[i]);
            else if (Integer.class.equals(pSet[i].getClass())) pStatement.setInt(i + 1, (int) pSet[i]);
            else if (Long.class.equals(pSet[i].getClass())) pStatement.setLong(i + 1, (long) pSet[i]);
            else if (Boolean.class.equals(pSet[i].getClass())) pStatement.setBoolean(i + 1, (boolean) pSet[i]);
            else if (Double.class.equals(pSet[i].getClass())) pStatement.setDouble(i + 1, (double) pSet[i]);
            else if (Date.class.equals(pSet[i].getClass())) pStatement.setDate(i + 1, (Date) pSet[i]);
        }
    }

    /**
     * @param pStatement SQL statement
     * @param pSet Each ? in the statment will be replaced with the content of this array
     * @return a {@link ResultSet} containing the result of your SQL statement
     * */
    public ResultSet onQuery(String pStatement, Object... pSet) throws SQLException {
        return addStatementVariables(pStatement, pSet).executeQuery();
    }

    /**
     * @param pStatement SQL statement
     * @return a {@link ResultSet} containing the result of your SQL statement
     * */
    public ResultSet onQuery(String pStatement) throws SQLException {
        return con.createStatement().executeQuery(pStatement);
    }

    /**
     * @param pStatement SQL statement you want to execute
     * @param pSet Each ? in the statment will be replaced with the content of this array
     * */
    public void onExecute(String pStatement, Object... pSet) throws SQLException {
        addStatementVariables(pStatement, pSet).execute();
        con.commit();
    }

    /**
     * @param pStatement SQL statement you want to execute
     * */
    public void onExecute(String pStatement) throws SQLException {
        con.createStatement().execute(pStatement);
        con.commit();
    }

    /**
     * Connects this class to an SQLite database.
     *
     * @param pSQLiteFilePath   Name or path of the database
     * */
    public void setDatabase(String pSQLiteFilePath) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:"+pSQLiteFilePath);
        con.setAutoCommit(false);
    }

    /**
     * Connects this class to a database.
     *
     * @param pDatabaseUrl  Host address
     * @param pUsername     Name of the user you want to connect as
     * @param pPassword     Password
     * */
    public void setDatabase(String pDatabaseUrl, String pUsername, String pPassword) throws SQLException {
        con = DriverManager.getConnection(pDatabaseUrl, pUsername, pPassword);
        con.setAutoCommit(false);
    }

    public Connection getConnection() { return con; }
}
