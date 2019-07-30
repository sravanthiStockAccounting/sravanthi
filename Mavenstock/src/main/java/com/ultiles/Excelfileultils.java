package com.ultiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfileultils
{
	Workbook wb;
	public Excelfileultils() throws Throwable
	{
		
		FileInputStream fis=new FileInputStream("D:\\sravanthi\\workspace\\Mavenstock\\testinputs\\InputSheet1.xlsx");
		wb=WorkbookFactory.create(fis);
		}
	//row count
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}	
		//coloumn count
	public int colcount(String sheetname,int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
		//reading the data
	public String getaData(String sheetname,int row,int column)
	{
		String data="";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) 
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			
			data=String.valueOf(celldata);
			}else
			{
				data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
			}
			return data;
	}
	

	//store the data
	public void setdata(String sheetname,int row,int column,String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("PASS")) 
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			//set bold
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
			}else
			
				if (status.equalsIgnoreCase("FAIL")) 
				{
					//create cell style
					CellStyle style=wb.createCellStyle();
					//create font
					Font font=wb.createFont();
					font.setColor(IndexedColors.RED.getIndex());
					//set bold
					font.setBold(true);
					//set font
					style.setFont(font);
					//set cell style
					rownum.getCell(column).setCellStyle(style);
			}else
			
				if (status.equalsIgnoreCase("NOt Execeuted")) 
				{
					//create cell style
					CellStyle style=wb.createCellStyle();
					//create font
					Font font=wb.createFont();
					font.setColor(IndexedColors.BLUE.getIndex());
					//set bold
					font.setBold(true);
					//set font
					style.setFont(font);
					//set cell style
					rownum.getCell(column).setCellStyle(style);
				}
				FileOutputStream fos=new FileOutputStream("D:\\sravanthi\\workspace\\Mavenstock\\testoutput\\OutPutSheet.xlsx");
				wb.write(fos);
				fos.close();
			}
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

