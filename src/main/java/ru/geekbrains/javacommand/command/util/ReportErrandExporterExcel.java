package ru.geekbrains.javacommand.command.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.exceptions.ExcelExportException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

public class ReportErrandExporterExcel {
    public static ByteArrayInputStream errandsToExcel(List<Errand> errandList){
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Errands");

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


            createHeader(sheet, headerCellStyle);
            createDataRows(sheet, errandList);
            createFooter(sheet);
            setAuthosize(sheet);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        }catch (IOException e){
            throw new ExcelExportException("Ошибка Экспорта в Excel");
        }
    }

    private static void setAuthosize(Sheet sheet) {
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
    }

    private static void createFooter(Sheet sheet) {
        Row footer = sheet.createRow(sheet.getLastRowNum() + 2);
        Cell cell = footer.createCell(0);
        cell.setCellValue("Дата");

        footer = sheet.createRow(sheet.getLastRowNum() + 1);
        cell = footer.createCell(0);
        cell.setCellValue(OffsetDateTime.now().toString());
    }


    private static void createDataRows(Sheet sheet, List<Errand> errandList) {
        for (int i = 0; i < errandList.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(errandList.get(i).getEmployee().getFIO());
            dataRow.createCell(1).setCellValue(errandList.get(i).getEmployee().getDepartment().getTitle());
            dataRow.createCell(2).setCellValue(errandList.get(i).getErrandDetails().getMatter().getMatter());
            dataRow.createCell(3).setCellValue(errandList.get(i).getDateStart().toString());
            dataRow.createCell(4).setCellValue(errandList.get(i).getDateEnd().toString());
            dataRow.createCell(5).setCellValue(errandList.get(i).getErrandDetails().getPlace().getTitle());
            dataRow.createCell(6).setCellValue(errandList.get(i).getErrandDetails().getComment());
            dataRow.createCell(7).setCellValue(errandList.get(i).getStatusType().getStatus());
            dataRow.createCell(8).setCellValue(errandList.get(i).getErrandDetails().getConfirmedOrRejectedBy().getFIO());
        }
    }

    private static void createHeader(Sheet sheet, CellStyle headerCellStyle) {
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("ФИО");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Подразделение");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(2);
        cell.setCellValue("Тип командировки");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(3);
        cell.setCellValue("Дата начала");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Дата окончания");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(5);
        cell.setCellValue("Куда");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(6);
        cell.setCellValue("Комментарий");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(7);
        cell.setCellValue("Статус");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(8);
        cell.setCellValue("Подтвердил/Отклонил");
        cell.setCellStyle(headerCellStyle);
    }
}
