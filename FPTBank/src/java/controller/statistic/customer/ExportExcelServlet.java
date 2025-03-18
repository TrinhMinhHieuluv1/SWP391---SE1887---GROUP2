package controller.statistic.customer;

import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ExportExcelServlet", urlPatterns = {"/admin/export_excel"})
public class ExportExcelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dataRangeType = request.getParameter("dataRangeType");
        String labelsStr = request.getParameter("labels");
        String dataStr = request.getParameter("data");
        String percentagesStr = request.getParameter("percentages");
        String fileDate = request.getParameter("fileDate");

        // Kiểm tra và thay thế giá trị rỗng hoặc "null"
        labelsStr = (labelsStr == null || labelsStr.equals("null") || labelsStr.trim().isEmpty()) ? "" : labelsStr;
        dataStr = (dataStr == null || dataStr.equals("null") || dataStr.trim().isEmpty()) ? "" : dataStr;
        percentagesStr = (percentagesStr == null || percentagesStr.equals("null") || percentagesStr.trim().isEmpty()) ? "" : percentagesStr;
        fileDate = (fileDate == null || fileDate.equals("null") || fileDate.trim().isEmpty()) ? "NoDate" : fileDate;

        // Chuyển chuỗi thành List
        List<String> labels = Arrays.asList(labelsStr.split(",")); // "A,B,C" → ["A", "B", "C"]
        List<Double> data = Arrays.stream(dataStr.split(","))
                .filter(s -> !s.trim().isEmpty()) // Loại bỏ chuỗi rỗng
                .map(Double::parseDouble) // parse từng phần tử thành Double
                .collect(Collectors.toList()); // thu thập kq từ stream thành List<Double>

        List<Double> percentages = Arrays.stream(percentagesStr.split(","))
                .filter(s -> !s.trim().isEmpty()) // Loại bỏ chuỗi rỗng
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        
        try {
            // Thiết lập response để tải file
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Chart_Report_" + fileDate + ".xlsx");

            // Ghi file Excel trực tiếp vào response
            OutputStream outputStream = response.getOutputStream();

            ExcelExporter.exportToExcel(dataRangeType, data, labels, percentages, fileDate, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error exporting Excel file");
        }
    }
}
