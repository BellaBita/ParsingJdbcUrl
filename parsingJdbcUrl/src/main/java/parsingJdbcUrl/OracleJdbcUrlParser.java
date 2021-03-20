package parsingJdbcUrl;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleJdbcUrlParser extends BasicJdbcUrlParser {

	public OracleJdbcUrlParser(String dbId, int defaultPort) {
		super(dbId, defaultPort);
	}

	@Override
	public String getDbAddress(String jdbcUrlParam) {
		String jdbcUrl = jdbcUrlParam.toLowerCase().replaceAll("\\s", "");
		boolean racYn = jdbcUrl.contains("@(");

		String address = "";
		if (!racYn) {
			String[] units = jdbcUrl.substring(jdbcUrl.indexOf('@') + 1).split("\\:");
			if (units.length > 1) {
				String host = units[0].isEmpty() ? this.getLocalIpAddress() : units[0];
				String port = units[1];
				if (port.indexOf('/') >= 0) {
					port = port.substring(0, port.indexOf('/'));
				}
				if (port.isEmpty()) {
					port = String.valueOf(this.getDefaultPort());
				}
				address = host + ":" + port;
			} else {
				address = getLocalIpAddress() + ":" + getDefaultPort();
			}
			address = address.replace("/", "");
		} else {
			String[] units = jdbcUrl.substring(jdbcUrl.indexOf("host=")).split("host=");
			for (String unit : units) {
				if (unit.isEmpty()) {
					continue;
				}
				System.out.println(unit);
				String host = unit.substring(0, unit.indexOf(')'));
				int portIdx = unit.indexOf("port=");
				String port = unit.substring(portIdx+5, unit.indexOf(')', portIdx));
				if (!address.isEmpty()) {
					address += ",";
				} 
				if (host.isEmpty()) host = this.getLocalIpAddress();
				if (port.isEmpty()) port = String.valueOf(this.getDefaultPort());
				address += (host + ":" + port);
			}
		}
		return address;
	}

}
