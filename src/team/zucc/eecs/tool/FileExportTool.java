package team.zucc.eecs.tool;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileExportTool {
	//static String dirPlace = "/root/Desktop/ceData/tmpData/";
	
	public static int exportExcel(String sheetName, String[] title, String[][] values, String dirPlace, String fileName) {
    	// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
   
        try {
            FileOutputStream fout = new FileOutputStream(dirPlace + fileName);
            wb.write(fout);
            fout.close();
            wb.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
           
    }
	
	
	public static HSSFWorkbook getHssfWorkbook(String sheetName, String[] title, String[][] values) {
    	// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
   
        return wb;
    }
	
	
	public static HSSFWorkbook getHssfWorkbookOfTwoSheet(String sheetName1, String sheetName2, 
			String[] title1, String[][] values1, String[] title2, String[][] values2) {
    	// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        
        
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFRow row1 = sheet1.createRow(0);
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCell cell1 = null;
        for(int i=0;i<title1.length;i++){
            cell1 = row1.createCell(i);
            cell1.setCellValue(title1[i]);
            cell1.setCellStyle(style1);
        }
        for(int i=0;i<values1.length;i++){
            row1 = sheet1.createRow(i + 1);
            for(int j=0;j<values1[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row1.createCell(j).setCellValue(values1[i][j]);
            }
        }
   
        
        HSSFSheet sheet2 = wb.createSheet(sheetName2);
        HSSFRow row2 = sheet2.createRow(0);
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFCell cell2 = null;
        for(int i=0;i<title2.length;i++){
            cell2 = row2.createCell(i);
            cell2.setCellValue(title2[i]);
            cell2.setCellStyle(style2);
        }
        for(int i=0;i<values2.length;i++){
            row2 = sheet2.createRow(i + 1);
            for(int j=0;j<values2[i].length;j++){
                row2.createCell(j).setCellValue(values2[i][j]);
            }
        }
        
        return wb;
    }
}
