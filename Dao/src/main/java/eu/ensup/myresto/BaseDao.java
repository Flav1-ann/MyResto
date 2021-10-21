package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * The type Base dao.
 */
public class BaseDao {

    private final String url;
    private final String login;
    private final String password;
    private Connection cn = null;
    private Statement st = null;
    private static ResultSet rs = null;

    private static PreparedStatement ps = null;

    private static CallableStatement cs = null;

    private static int result;
    private static final Logger log = LogManager.getLogger(BaseDao.class);
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * Instantiates a new Base dao.
     */
    public BaseDao() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        this.url = bundle.getString("db.url");
        this.login = bundle.getString("db.username");
        this.password = bundle.getString("db.password");
    }

    /**
     * Connexion int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    public int connexion() throws DaoException {
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(this.url, login, password);
            st = cn.createStatement();
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new DaoException(BaseDao.class.getName(), "connexion", e.getMessage(), "Une erreur s'est produite lors de connection à la base de données");
        }
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        try {
            if (rs != null)
                rs.close();
            if (cs != null)
                cs.close();
            if (ps != null)
                ps.close();
            if (st != null)
                st.close();
            if (cn != null)
                cn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets cn.
     *
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * Sets cn.
     *
     * @param cn the cn
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * Gets st.
     *
     * @return the st
     */
    public Statement getSt() {
        return this.st;
    }

    /**
     * Sets st.
     *
     * @param st the st
     */
    public void setSt(Statement st) {
        this.st = st;
    }

    /**
     * Gets rs.
     *
     * @return the rs
     */
    public static ResultSet getRs() {
        return rs;
    }

    /**
     * Sets rs.
     *
     * @param rs the rs
     */
    public static void setRs(ResultSet rs) {
        BaseDao.rs = rs;
    }

    /**
     * Gets ps.
     *
     * @return the ps
     */
    public static PreparedStatement getPs() {
        return ps;
    }

    /**
     * Sets ps.
     *
     * @param ps the ps
     */
    public static void setPs(PreparedStatement ps) {
        BaseDao.ps = ps;
    }

    /**
     * Gets cs.
     *
     * @return the cs
     */
    public static CallableStatement getCs() {
        return cs;
    }

    /**
     * Sets cs.
     *
     * @param cs the cs
     */
    public static void setCs(CallableStatement cs) {
        BaseDao.cs = cs;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public static int getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public static void setResult(int result) {
        BaseDao.result = result;
    }
}