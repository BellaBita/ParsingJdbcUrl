package parsingJdbcUrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class JdbcUrlParserTest {

	@Test
	public void parse() {

		File file = new File("JDBCUrl.txt");
		JdbcUrlParser parser = JdbcUrlParser.getInstance();

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String jdbcUrl = line.replace(" ", "");
				if (!jdbcUrl.isEmpty() && jdbcUrl.startsWith("jdbc:")) {
					System.out.println(jdbcUrl);
					System.out.println(parser.getAddress(jdbcUrl));
					System.out.println();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
