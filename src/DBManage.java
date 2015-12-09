/*
 * Created on 2005-7-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManage {

	// 用户名

	private String user = "";

	// 密码

	private String password = "";

	// 主机

	private String host = "";

	// 数据库名字

	private String database = "";

	private String url = "";

	private Connection con = null;

	Statement stmt;

	/**
	 * 
	 * 根据主机、数据库名称、数据库用户名、数据库用户密码取得连接。
	 * 
	 * @param host
	 *            String
	 * 
	 * @param database
	 *            String
	 * 
	 * @param user
	 *            String
	 * 
	 * @param password
	 *            String
	 */

	public DBManage(String host, String database, String user, String password) {

		this.host = host;

		this.database = database;

		this.user = user;

		this.password = password;
		// 显示中文
		this.url = "jdbc:mysql://" + host + "/" + database
				+ "?useUnicode=true&characterEncoding=UTF8";
//		jdbc:mysql://localhost:3306/order?useUnicode=true&amp;characterEncoding=UTF8&amp;autoReconnect=true&amp;autoReconnectForPools=true"
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("class not found:" + e.getMessage());
		}

		try {
			con = DriverManager.getConnection(this.url, this.user,
					this.password);
			// 连接类型为ResultSet.TYPE_SCROLL_INSENSITIVE,
			// ResultSet.CONCUR_READ_ONLY
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException a) {
			System.err.println("sql exception:" + a.getMessage());
		}
	}

	/**
	 * 返回取得的连接
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * 
	 * 执行一条简单的查询语句
	 * 
	 * 返回取得的结果集
	 */
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * 执行一条简单的更新语句
	 * 
	 * 执行成功则返回true
	 */

	public boolean executeUpdate(String sql) {
		boolean v = false;
		try {
			v = stmt.executeUpdate(sql) > 0 ? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			return v;
		}
	}

	// public static void main(String[] args){
	// ResultSet rs;
	// DBManager exe = new DBManager("192.168.0.222","test","root","111");
	//
	// rs = exe.executeQuery("SELECT * FROM encodingtest");
	// try{
	// while(rs.next()){
	// System.out.println(rs.getInt("sid") + "    " + rs.getString("str"));
	//
	// }
	// }catch (Exception e){
	//
	// }
	// }
	//
}
