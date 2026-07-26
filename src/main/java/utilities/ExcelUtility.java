package utilities;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FrameworkConstants;

public final class ExcelUtility {

	private static final Logger logger = LogManager.getLogger(ExcelUtility.class);

	private static final String TEST_DATA_PATH =
			FrameworkConstants.TEST_DATA_DIRECTORY;

	private ExcelUtility() {

	}

	public static Map<String, String> getTestData(String fileName,String sheetName,String testCase){

		logger.info("Reading test data from '{}' for test case '{}'.", fileName, testCase);
		
			try (InputStream inputStream = 
					ExcelUtility.class.getClassLoader().getResourceAsStream(TEST_DATA_PATH + fileName);
				
			 XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

			if (inputStream == null) {
				throw new RuntimeException("Excel file not found : " + fileName);
			}

			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new RuntimeException("Sheet not found : " + sheetName);
			}

			DataFormatter formatter = new DataFormatter();

			Row headerRow = sheet.getRow(0);

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

				Row currentRow = sheet.getRow(rowIndex);

				if (currentRow == null) {
					continue;
				}

				String currentTestCase =
						formatter.formatCellValue(currentRow.getCell(0));

				if (testCase.equalsIgnoreCase(currentTestCase)) {

					Map<String, String> testData = new LinkedHashMap<>();

					for (int columnIndex = 0; columnIndex < headerRow.getLastCellNum(); columnIndex++) {

						String columnName =
								formatter.formatCellValue(headerRow.getCell(columnIndex));

						String cellValue =
								formatter.formatCellValue(currentRow.getCell(columnIndex));

						testData.put(columnName, cellValue);

					}

					logger.info("Successfully loaded test data for '{}'.", testCase);

					return testData;

				}
			}

			throw new RuntimeException("Test case not found : " + testCase);

		} catch (Exception exception) {

			logger.error("Failed to read Excel test data.", exception);

			throw new RuntimeException("Unable to read Excel test data.", exception);

		}

	}

}