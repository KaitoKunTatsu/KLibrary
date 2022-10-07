package KLibrary.utils;

import java.sql.*;

/**
 * This class provides methods for secure SQL statements (preventing SQL-injection)<br>
 * A part of the (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 *
 * @version 1.1.3 | last edit: 26.09.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class SQLUtils {

    private Connection con;
    private PreparedStatement stmt;

    /**
     * Creates an instance of this class and establishes a database connection.
     *
     * @param pDBPath   Name or path of the database you want to work with
     *
     * @see #setDatabase(String)
     * */
    public SQLUtils(String pDBPath) throws SQLException {
        setDatabase(pDBPath);
    }

    /**
     * @param pStatement SQL statement
     * @param pSet Each ? in the statment will be replaced with the content of this array
     * @return a {@link ResultSet} containing the result of your SQL statement
     * */
    public ResultSet onQuery(String pStatement, Object... pSet) throws SQLException {
        stmt = con.prepareStatement(pStatement);
        if (pSet != null)
        {
            for (int i = 0; i < pSet.length; i++) {
                if (Blob.class.equals(pSet[i].getClass())) stmt.setBlob(i + 1, (Blob) pSet[i]);
                else if (byte[].class.equals(pSet[i].getClass())) stmt.setBytes(i + 1, (byte[]) pSet[i]);
                else if (byte.class.equals(pSet[i].getClass())) stmt.setByte(i + 1, (byte) pSet[i]);
                else if (String.class.equals(pSet[i].getClass())) stmt.setString(i + 1, (String) pSet[i]);
                else if (Integer.class.equals(pSet[i].getClass())) stmt.setInt(i + 1, (int) pSet[i]);
                else if (Long.class.equals(pSet[i].getClass())) stmt.setLong(i + 1, (long) pSet[i]);
                else if (Boolean.class.equals(pSet[i].getClass())) stmt.setBoolean(i + 1, (boolean) pSet[i]);
                else if (Double.class.equals(pSet[i].getClass())) stmt.setDouble(i + 1, (double) pSet[i]);
                else if (Date.class.equals(pSet[i].getClass())) stmt.setDate(i + 1, (Date) pSet[i]);
            }
        }
        ResultSet rs = stmt.executeQuery();
        stmt.clearParameters();
        return rs;
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
        stmt = con.prepareStatement(pStatement);
        if (pSet != null)
        {
            for (int i=0; i < pSet.length; i++)
            {
                if (Blob.class.equals(pSet[i].getClass())) stmt.setBlob(i + 1, (Blob) pSet[i]);
                else if (byte[].class.equals(pSet[i].getClass())) stmt.setBytes(i + 1, (byte[]) pSet[i]);
                else if (byte.class.equals(pSet[i].getClass())) stmt.setByte(i + 1, (byte) pSet[i]);
                else if (String.class.equals(pSet[i].getClass())) stmt.setString(i + 1, (String) pSet[i]);
                else if (Integer.class.equals(pSet[i].getClass())) stmt.setInt(i + 1, (int) pSet[i]);
                else if (Long.class.equals(pSet[i].getClass())) stmt.setLong(i + 1, (long) pSet[i]);
                else if (Boolean.class.equals(pSet[i].getClass())) stmt.setBoolean(i + 1, (boolean) pSet[i]);
                else if (Double.class.equals(pSet[i].getClass())) stmt.setDouble(i + 1, (double) pSet[i]);
                else if (Date.class.equals(pSet[i].getClass())) stmt.setDate(i + 1, (Date) pSet[i]);
            }
        }
        stmt.execute();
        con.commit();
        stmt.clearParameters();
    }

    /**
     * @param pStatement SQL statement you want to execute
     * */
    public void onExecute(String pStatement) throws SQLException {
        con.createStatement().execute(pStatement);
        con.commit();
    }

    /**
     * Connects this class to a database.
     *
     * @param pDBPath   Name or path of the database
     * */
    public void setDatabase(String pDBPath) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:"+pDBPath);
        con.setAutoCommit(false);
    }

    public Connection getConnection() { return con; }
}
