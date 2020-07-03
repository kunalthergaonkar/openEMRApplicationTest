package com.tieto.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilis {

	public static Object[][]	 getSheetIntoObject(String fileDetail,String sheetname) throws IOException
	{
		FileInputStream file= new FileInputStream(fileDetail);
		XSSFWorkbook book= new XSSFWorkbook(file);
		XSSFSheet sheet=book.getSheet(sheetname);
		
		int rowcount=sheet.getPhysicalNumberOfRows();
		System.out.println(rowcount);
		
		int colcount=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(colcount);
		
		Object [][] main= new Object[rowcount-1][colcount];
		DataFormatter format= new DataFormatter();
		for (int r=1;r<rowcount;r++)
		{
			XSSFRow row= sheet.getRow(r);
			for(int c=0;c<colcount;c++)
			{
				XSSFCell cell=row.getCell(c);		
	
				String cellValue=format.formatCellValue(cell);
				main[r-1][c]=cellValue;
			}

		}
		book.close();
		file.close();
		return main;
	}
}
