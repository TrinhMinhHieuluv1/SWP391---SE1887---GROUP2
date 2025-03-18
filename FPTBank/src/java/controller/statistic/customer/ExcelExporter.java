package controller.statistic.customer;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class ExcelExporter {

    public static void exportToExcel(
            String typeOfStatistic,
            List<Double> chartData,
            List<String> labels,
            List<Double> percentages,
            String fileDate,
            OutputStream outputStream) throws Exception {

        // Create a new workbook
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Chart Report");

            // Tính tổng số khách hàng
            double totalCus = 0;
            for (double value : chartData) {
                totalCus += value;
            }

            // Định dạng số thập phân
            DecimalFormat df = new DecimalFormat("#.##");

            // Chuyển danh sách percentages sang mảng để dễ thao tác
            Double[] percentArray = percentages.toArray(new Double[0]);

            if (totalCus == 0) {
                // Nếu không có khách nào, gán tất cả phần trăm = 0
                Arrays.fill(percentArray, 0.0);
            } else {
                // Tính tổng phần trăm hiện tại
                double totalPercentage = 0;
                for (double p : percentArray) {
                    totalPercentage += p;
                }

                double diff = 100 - totalPercentage;

                if (percentArray.length > 0 && diff != 0) {
                    int maxIndex = 0;
                    double maxError = 0;

                    // Tìm phần tử có sai số lớn nhất
                    for (int i = 0; i < percentArray.length; i++) {
                        double error = Math.abs(percentArray[i] - Double.parseDouble(df.format(percentArray[i]))); // df.format(x) -> String
                        if (error > maxError) {
                            maxError = error;
                            maxIndex = i;
                        }
                    }

                    // Điều chỉnh phần trăm để tổng = 100%
                    percentArray[maxIndex] = Double.parseDouble(df.format(percentArray[maxIndex] + diff));
                }
            }

            // Find most common ranges (multiple ranges if they share the same max value)
            double maxValue = chartData.isEmpty() ? 0 : Collections.max(chartData);

            
            List<String> mostCommonRanges = new ArrayList<>();
            if (totalCus != 0) {
                for (int i = 0; i < chartData.size(); i++) {
                    if (chartData.get(i) == maxValue) {
                        mostCommonRanges.add(labels.get(i));
                    }
                }
            }

            String mostCommonRange = mostCommonRanges.isEmpty() ? "None" : String.join(", ", mostCommonRanges);

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {typeOfStatistic, "Number of Customers", "Percentage (%)"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(headerStyle);
            }

            // Add data rows
            int rowNum = 1; //Bắt đầu thêm dữ liệu từ hàng số 1 (vì hàng 0 là tiêu đề).
            for (int i = 0; i < chartData.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(labels.get(i));
                row.createCell(1).setCellValue(chartData.get(i));
                row.createCell(2).setCellValue(df.format(percentArray[i]) + "%");
            }

            // Add separator
            Row separator = sheet.createRow(rowNum++);
            for (int i = 0; i < 3; i++) {
                separator.createCell(i).setCellValue("==============");
            }

            // Add summary headers
            Row summaryHeader = sheet.createRow(rowNum++);
            String[] summaryHeaders = {"Most Common Range", "Total of Customer", "Total of percentage"};
            for (int i = 0; i < summaryHeaders.length; i++) {
                summaryHeader.createCell(i).setCellValue(summaryHeaders[i]);
            }

            // Add summary data
            String totalPercentageStr = (totalCus == 0) ? "0%" : (df.format(100.0) + "%");

            Row summaryData = sheet.createRow(rowNum++);
            summaryData.createCell(0).setCellValue(mostCommonRange);
            summaryData.createCell(1).setCellValue(totalCus);
            summaryData.createCell(2).setCellValue(totalPercentageStr);

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to OutputStream
            workbook.write(outputStream);
        }
    }
}
