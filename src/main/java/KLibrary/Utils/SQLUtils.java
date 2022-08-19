package KLibrary.Utils;

import java.sql.*;

/**
 * This class provides methods for secure SQL statements (preventing SQL-injection)<br>
 * A part of the KLibrary (https://github.com/KaitoKunTatsu/KLibrary)
 *
 * @version	v1.0.0 | last edit: 19.08.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class SQLUtils {

    private final Connection con;
    private PreparedStatement stmt;

    public SQLUtils(String pDBPath) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:"+pDBPath);
        con.setAutoCommit(false);
    }

    /**
     * @param pStatement SQL statement
     * @param pSet each ? will be replaced with the content of this array
     * @return a {@link ResultSet} containing the result of your SQL statement
     * */
    public ResultSet onQuery(String pStatement, String[] pSet) throws SQLException {
        stmt = con.prepareStatement(pStatement);
        if (pSet != null)
        {
            for (int i = 0; i < pSet.length; i++) {
                stmt.setString(i + 1, pSet[i]);
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
     * @param pSet each ? will be replaced with the content of this array
     * */
    public void onExecute(String pStatement, String[] pSet) throws SQLException {
        stmt = con.prepareStatement(pStatement);
        if (pSet != null)
        {
            for (int i=0; i < pSet.length; i++)
            {
                stmt.setString(i+1, pSet[i]);
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
}
