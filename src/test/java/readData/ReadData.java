package readData;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static int totalRows;
	
	public static ArrayList<String> fullRowData(String sheetname,String testcaseName) {
		ArrayList<String> rowData = null;
		try
		{
		rowData = new ArrayList<String>();

		 fis = new FileInputStream("./TestData/TestData.xlsx");
		 workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetname)) {
				 sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row
				 
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				
				 totalRows=sheet.getLastRowNum();
				 
//				System.out.println("Total Rows ::: "+totalRows);
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TC ID")) {
						coloumn = k;

					}

					k++;
				}
				 //System.out.println("Column value is"+coloumn);
				// once coloumn is identified then scan entire testcase coloum to identify
				// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {

								rowData.add(c.getStringCellValue());
							} else {

								rowData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}}}}}}
		workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowData;
	}
	
	public static String getCellData(String sheetname,String testflow,String colName)
	{
		ArrayList<String> fulldata=fullRowData(sheetname,testflow);
		Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
		Row firstrow = rows.next();
		Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
		int k = 0;
		int coloumn = 0;
		while (ce.hasNext()) {
			Cell value = ce.next();

			if (value.getStringCellValue().equalsIgnoreCase(colName)) {
				coloumn = k;
				break;
			}

			k++;
		}
		String cellValue=fulldata.get(coloumn);
//		System.out.println(colName+" Value is ::: "+cellValue);
		return cellValue;	
	}
	
	public static String[][] getSheetData(String sheetname)
	{
		String[][] fulldata = null;
		try
		{
		fis = new FileInputStream("./TestData/TestData.xlsx");
		 workbook = new XSSFWorkbook(fis);
		 sheet = workbook.getSheet(sheetname);

		int row = sheet.getLastRowNum();
		int cell = sheet.getRow(0).getLastCellNum();
		fulldata= new String[row][cell];

		int k = 0, l;
		for (int i = 1; i <= row; i++) {

			l = 0;
			for (int j = 0; j < cell; j++) {

				String data = sheet.getRow(i).getCell(j).getStringCellValue();
				fulldata[k][l] = data;
//				System.out.print(fulldata[k][l] + "       ");
				l++;
			}
			System.out.println();
			k++;

		}
		}
		catch(Exception e)
		{
			
		}
		return fulldata;
	}
	
	public static void writeDataIntoCell(String sheetname,String testflow,String colName,String value)
	{

		try {
			 fis = new FileInputStream("./TestData/TestData.xlsx");
			 workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetname);

		// based on row header

		Row row = sheet.getRow(0);

		int cellNum = row.getPhysicalNumberOfCells();

		int rowNum = sheet.getPhysicalNumberOfRows();

		int expectedColumnNumber = 0;

		int expectedRowNumber = 0;

		for (int i = 0; i < rowNum; i++) {

		if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testflow)) {

		expectedRowNumber = i;

		break;

		}

		}

		for (int i = 0; i < cellNum; i++) {

		if ((row.getCell(i).toString()).equals(colName)) {

		expectedColumnNumber = i;

		break;

		}

		}

		sheet.getRow(expectedRowNumber).getCell(expectedColumnNumber).setCellValue(value);

		fis.close();

		FileOutputStream fileOutputStream = new FileOutputStream("./TestData/TestData.xlsx");

		workbook.write(fileOutputStream);

		workbook.close();

		fileOutputStream.close();
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

	
	public static void main(String[] args) {
//		System.out.println(fullRowData("TestData","TC_001"));
//		System.out.println(fullRowData("LoginData","TC_001"));
//		String url=getCellData("TestData","TC_004","API URL");
		       
}
}