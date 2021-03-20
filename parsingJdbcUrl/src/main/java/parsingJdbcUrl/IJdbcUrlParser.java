package parsingJdbcUrl;

public interface IJdbcUrlParser {

	/**
	 * DB?œ ?˜•?„?„ ë°˜í™˜ ex) mysql,oracle, 
	 * @return
	 */
	String getDbId();
	
	/**
	 * Property?— Portê°? ?—†?„ ê²½ìš° ?‚¬?š©?•˜?Š” ê¸°ë³¸ Port ê°?
	 * @return
	 */
	int getDefaultPort();
	
	/**
	 * DB?— ?•´?‹¹?˜?Š” URL?¸ì§? ?Œ?‹¨. 
	 * @param jdbcUrl
	 * @return
	 */
	boolean matches(String jdbcUrl);
	
	/**
	 * JDBC Url?„ ?ŒŒ?‹±?•˜?—¬ IP:Port[,[IP:Port]]ë¥? ë°˜í™˜?•œ?‹¤.
	 * @param jdbcUrl
	 * @return
	 */
	String getDbAddress(String jdbcUrl);
	
	/**
	 * Memory?‚˜ File?„ ?™œ?š©?•˜?Š” ê²½ìš° Falseë¥? ë°˜í™˜?•œ?‹¤. 
	 * @return
	 */
	boolean isSharableDb(String jdbcUrl);
}
