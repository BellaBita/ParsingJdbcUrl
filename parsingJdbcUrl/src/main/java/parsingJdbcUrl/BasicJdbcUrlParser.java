package parsingJdbcUrl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BasicJdbcUrlParser implements IJdbcUrlParser {

	protected String dbId = "";
	protected int defaultPort = 0;
	protected int addressIndex = 2;

	public BasicJdbcUrlParser(String dbId, int defaultPort) {
		this.dbId = dbId;
		this.defaultPort = defaultPort;
	}

	@Override
	public String getDbId() {
		return dbId;
	}

	@Override
	public int getDefaultPort() {
		return defaultPort;
	}

	@Override
	public boolean matches(String jdbcUrl) {
		return jdbcUrl.startsWith("jdbc:" + dbId + ":");
	}

	@Override
	public String getDbAddress(String jdbcUrlParam) {
		String jdbcUrl = jdbcUrlParam.toLowerCase().replaceAll("\\s", "");
		int startIdx = jdbcUrl.indexOf("//");
		String address = "";
		if (startIdx > 0) {
			int endIdx = jdbcUrl.indexOf('/', startIdx + 2);
			if (endIdx < startIdx) {
				endIdx = jdbcUrl.indexOf(';', startIdx + 2);
			}
			if (endIdx > startIdx) {
				address = jdbcUrl.substring(startIdx + 2, endIdx);
				if (address.isEmpty()) {
					address = getLocalIpAddress() + ":" + getDefaultPort();
				} else if (address.indexOf(":") < 0) {
					address = address + ":" + getDefaultPort();
				}
			} else {
				address = escapeBeforeSpecialChar(escapeDbId(jdbcUrl));
			}
		} else {
			address = getLocalIpAddress() + ":" + getDefaultPort();
		}
		return address;
	}

	/**
	 * DBIDê¹Œì?ë¥? ?ž˜?¼?‚¸?‹¤.
	 * 
	 * @param jdbcUrl
	 * @return
	 */
	protected String escapeDbId(String jdbcUrl) {
		return jdbcUrl.substring(this.dbId.length() + 6);
	}

	/**
	 * Local IP ì£¼ì†Œë¥? ì¡°íšŒ?•œ?‹¤.
	 * 
	 * @return
	 */
	protected String getLocalIpAddress() {
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			return local.getHostAddress();
		} catch (UnknownHostException e) {
			// e.printStackTrace();
			return "";
		}
	}

	/**
	 * ?Š¹?ˆ˜ë¬¸ìž?˜ ?•žê¹Œì?ë§? ?Š?Š”?‹¤.
	 * 
	 * @param jdbcUrl
	 * @return
	 */
	protected String escapeBeforeSpecialChar(String jdbcUrl) {
		int idx = jdbcUrl.length();
		for (char c : "&;?/".toCharArray()) {
			int cIdx = jdbcUrl.indexOf(c);
			idx = Math.min(idx, cIdx > 0 ? cIdx : idx);
		}
		return jdbcUrl.substring(0, idx);
	}

	@Override
	public boolean isSharableDb(String jdbcUrl) {
		return true;
	}

}
