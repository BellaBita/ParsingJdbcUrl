package parsingJdbcUrl;

public interface IJdbcUrlParser {

	/**
	 * DB?��?��?��?�� 반환 ex) mysql,oracle, 
	 * @return
	 */
	String getDbId();
	
	/**
	 * Property?�� Port�? ?��?�� 경우 ?��?��?��?�� 기본 Port �?
	 * @return
	 */
	int getDefaultPort();
	
	/**
	 * DB?�� ?��?��?��?�� URL?���? ?��?��. 
	 * @param jdbcUrl
	 * @return
	 */
	boolean matches(String jdbcUrl);
	
	/**
	 * JDBC Url?�� ?��?��?��?�� IP:Port[,[IP:Port]]�? 반환?��?��.
	 * @param jdbcUrl
	 * @return
	 */
	String getDbAddress(String jdbcUrl);
	
	/**
	 * Memory?�� File?�� ?��?��?��?�� 경우 False�? 반환?��?��. 
	 * @return
	 */
	boolean isSharableDb(String jdbcUrl);
}
