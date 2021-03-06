package parsingJdbcUrl;

public interface IJdbcUrlParser {

	/**
	 * DB? ??? λ°ν ex) mysql,oracle, 
	 * @return
	 */
	String getDbId();
	
	/**
	 * Property? Portκ°? ?? κ²½μ° ?¬?©?? κΈ°λ³Έ Port κ°?
	 * @return
	 */
	int getDefaultPort();
	
	/**
	 * DB? ?΄?Ή?? URL?Έμ§? ??¨. 
	 * @param jdbcUrl
	 * @return
	 */
	boolean matches(String jdbcUrl);
	
	/**
	 * JDBC Url? ??±??¬ IP:Port[,[IP:Port]]λ₯? λ°ν??€.
	 * @param jdbcUrl
	 * @return
	 */
	String getDbAddress(String jdbcUrl);
	
	/**
	 * Memory? File? ??©?? κ²½μ° Falseλ₯? λ°ν??€. 
	 * @return
	 */
	boolean isSharableDb(String jdbcUrl);
}
