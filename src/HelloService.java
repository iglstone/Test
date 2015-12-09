import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/helloWS")
public class HelloService {

	@GET
	@Path("/hello/{name}")
	@Produces("application/json")
	public String saysometing(@PathParam("name") String name) throws SQLException {
		ResultSet rs;
		
		DBManage exe = new DBManage("192.168.5.121:3306", "order", "root", "guolong");
		rs = exe.executeQuery("SELECT * FROM dish");
		String xxString = null;
		try {
			while (rs.next()) {
				xxString = rs.getInt("id") + rs.getString("name");
				System.out.println(rs.getInt("id") + "    "
						+ rs.getString("name"));
			}
		} catch (Exception e) {
			
		}
		return "guolong\n" + xxString;
	}
}