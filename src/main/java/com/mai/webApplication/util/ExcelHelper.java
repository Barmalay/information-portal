package com.mai.webApplication.util;

import com.mai.webApplication.models.Statement;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static ByteArrayInputStream statementsStudentToExcel(List<Statement> statements) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Студент");

            //    Заголовки столбцов
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("№");
            header.createCell(1).setCellValue("Имя преподавателя");
            header.createCell(2).setCellValue("Дисциплина");
            header.createCell(3).setCellValue("Оценка");
            // Значения строк
            int rowNum = 1;
            for (Statement statement : statements) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue((rowNum-1));
                row.createCell(1).setCellValue(statement.getTeacher().getShortName());
                row.createCell(2).setCellValue(statement.getNameSubject());
                row.createCell(3).setCellValue(statement.getRating());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}

