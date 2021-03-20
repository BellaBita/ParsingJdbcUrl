package parsingJdbcUrl;

import java.util.HashMap;
import java.util.Map;

public class JdbcUrlParser {
	
	private static JdbcUrlParser INSTANCE = null;
	private static Object LOCK = new Object();
	
	public static JdbcUrlParser getInstance() {
		
		if (INSTANCE == null) {
			synchronized (LOCK) {
				if (INSTANCE == null) {
					INSTANCE = new JdbcUrlParser();
				}
			}
		}
		return new JdbcUrlParser();
	}
	
	Map<String, IJdbcUrlParser> parserMap = new HashMap<String, IJdbcUrlParser>();
	
	private JdbcUrlParser() {
		parserMap.put("db2",new BasicJdbcUrlParser("db2",50000));
		parserMap.put("mariadb",new BasicJdbcUrlParser("mariadb",3306));
		parserMap.put("mysql",new BasicJdbcUrlParser("mysql",3306));
		parserMap.put("sqlserver",new BasicJdbcUrlParser("sqlserver",1433));
		parserMap.put("postgresql",new BasicJdbcUrlParser("postgresql",5432));
		parserMap.put("oracle", new OracleJdbcUrlParser("oracle", 1521));
		parserMap.put("tibero", new OracleJdbcUrlParser("tibero", 8629));
	}
	
	public String getAddress(String jdbcUrl) {
		String dbId = jdbcUrl.split(":")[1];
		IJdbcUrlParser parser = this.parserMap.get(dbId);
		if (parser == null) {
			System.out.println("    XXX Parser Not Found.");
			return "";
		} else {
			String addr = parser.getDbAddress(jdbcUrl);
			System.out.println(addr);
			return addr;
		}
	}

}
