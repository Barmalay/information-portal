package com.mai.webApplication.util;

import com.mai.webApplication.models.Statement;
import com.mai.webApplication.models.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static ByteArrayInputStream statementsStudentToExcel(List<Statement> statements, Student student) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Успеваемость");

            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Студент");
            header.createCell(1).setCellValue(student.getFullName());
            header = sheet.createRow(1);
            header.createCell(0).setCellValue("Гуппа");
            header.createCell(1).setCellValue(student.getGroupStudent());

            //    Заголовки столбцов
            header = sheet.createRow(2);
            header.createCell(0).setCellValue("№");
            header.createCell(1).setCellValue("Имя преподавателя");
            header.createCell(2).setCellValue("Дисциплина");
            header.createCell(3).setCellValue("Форма сдачи");
            header.createCell(4).setCellValue("Оценка");
            // Значения строк
            int rowNum = 3;
            int i = 1;
            for (Statement statement : statements) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(i++);
                row.createCell(1).setCellValue(statement.getTeacher().getShortName());
                row.createCell(2).setCellValue(statement.getNameSubject());
                row.createCell(3).setCellValue(statement.getTeacher().getTypeControl());
                row.createCell(4).setCellValue(statement.getRating());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}

