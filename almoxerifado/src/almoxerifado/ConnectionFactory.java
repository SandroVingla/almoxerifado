package almoxerifado;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Faz a conexão com o banco de dados
 * @author welison
 */
public class ConnectionFactory {
    
    /**
     * Retorna a conexão com o banco de dados
     * @return a conexão com o banco de dados
     */
    public Connection getConexao() {
        try {
            ConfigGenerator cg = new ConfigGenerator();
            ConnectorModel cn = cg.getConnector();
            return DriverManager.getConnection(cn.getHost(), cn.getUser(), cn.getPassword());
            //return DriverManager.getConnection("jdbc:mysql://localhost/projetozika", "root", "");
        } catch(SQLException error) {
           
            throw new RuntimeException("ConnectionFactory.getConexao: " + error);
        }
    }
    
    /**
     * Testa a conexão com o banco de dados
     * @param host
     * @param database
     * @param user
     * @param password
     * @return boolean 
     */
    public boolean testConexao(String host, String database, String user, String password) {
        try {
            DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password).close();
            return true;
        } catch(SQLException error) {
            
            return false;
        }
    } 
    
    /**
     * Close all possible connections
     * @param rs the ResultSet
     * @param stmt the PreparedStatement
     * @param st the Statement
     * @param conn the Connection
     */
    public void closeAll(ResultSet rs, PreparedStatement stmt, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch(SQLException error) {
            
            throw new RuntimeException("EstoqueDAO.closeAll: " + error);
        }
    }
}
