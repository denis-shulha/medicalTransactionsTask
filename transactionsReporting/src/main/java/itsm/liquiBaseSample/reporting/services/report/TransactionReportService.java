package itsm.liquiBaseSample.reporting.services.report;

import itsm.liquiBaseSample.domains.Transaction;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface TransactionReportService {
    Workbook generateTransactionsReport(String message, List<Transaction> transactions) throws Exception;
}
