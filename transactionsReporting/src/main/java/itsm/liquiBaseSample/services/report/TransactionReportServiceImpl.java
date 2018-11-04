package itsm.liquiBaseSample.services.report;
import itsm.liquiBaseSample.domains.Transaction;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {

    private static String[] columns = {"Product", "Patient", "TransactionDate"};

    @Override
    public Workbook generateTransactionsReport(String message, List<Transaction> transactions) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        XSSFSheet sheet = ((XSSFWorkbook) workbook).createSheet("Tranactions");

        Font messageFont = workbook.createFont();
        messageFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        messageFont.setFontHeightInPoints((short) 14);

        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle messageCellStyle = workbook.createCellStyle();
        messageCellStyle.setFont(messageFont);

        CellStyle tableCellStyle=workbook.createCellStyle();
        tableCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        tableCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        tableCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        tableCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);


        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.cloneStyleFrom(tableCellStyle);
        headerCellStyle.setFont(headerFont);

        //Create a Main Header Message
        Row mainMessageRow = sheet.createRow(1);
        Cell messageCell = mainMessageRow.createCell(3);
        messageCell.setCellValue(message);
        messageCell.setCellStyle(messageCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(1,1,3,9));
        // Create a Row
        Row headerRow = sheet.createRow(3);

        int colNum = 3;
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(colNum + i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.cloneStyleFrom(tableCellStyle);
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        int rowNum = 4;
        for(Transaction item: transactions) {
            Row row = sheet.createRow(rowNum++);
            createStyledCellWithValue(row,colNum,tableCellStyle, item.getProduct().getName());
            createStyledCellWithValue(row,colNum + 1, tableCellStyle, item.getPatient().getName());
            createStyledCellWithValue(row,colNum + 2, dateCellStyle, ""/*item.getCreatedDate()*/);
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(colNum + i);
        }

        return workbook;
    }

    private Cell createStyledCellWithValue(Row row, int colNum, CellStyle style, Object value) {
        Cell cell = row.createCell(colNum);
        cell.setCellValue(value.toString());
        cell.setCellStyle(style);
        return cell;
    }
}
