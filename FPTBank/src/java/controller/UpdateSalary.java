package controller;

import Tools.SaveImage;
import dal.AssetDAO;
import dal.PdfDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import model.Asset;
import model.Customer;
import model.PdfLis;
import utils.FileUploadHelper;
import com.google.gson.Gson;
import dal.SalaryDAO;
import java.util.ArrayList;
import model.Salary;

@WebServlet(name = "UpdateSalary", urlPatterns = {"/updateSalary"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50
)
public class UpdateSalary extends HttpServlet {

    private static final String UPLOAD_DIR = "salaryPDF";
    private static final String IMAGE_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        try {
            // Lấy các tham số từ request
            Part filePart = request.getPart("file"); // PDF file
            String id_raw = request.getParameter("id");
            String name = request.getParameter("name");
            String value_raw = request.getParameter("value");
            String description = request.getParameter("description");
            String current_pdfs = request.getParameter("current_pdfs");
            String deletedPdfsJson = request.getParameter("deleted_pdfs");
            Part filePartImage = request.getPart("fileImage"); // Image file

            // Khởi tạo DAO và lấy thông tin từ session
            SalaryDAO dao = new SalaryDAO();
            PdfDAO pdfDAO = new PdfDAO();
            HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");

            if (account == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.print(gson.toJson(new ResponseMessage(false, "User not logged in")));
                out.flush();
                return;
            }

            // Parse ID và lấy asset hiện tại
            int id = Integer.parseInt(id_raw);
            Salary salary = dao.getSalaryById(id);
            if (salary == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print(gson.toJson(new ResponseMessage(false, "Asset not found")));
                out.flush();
                return;
            }

            // Cập nhật thông tin asset
            if (name != null && !name.trim().isEmpty()) {
                salary.setTitle(name);
            }
            if (description != null) {
                salary.setDescription(description);
            }
            if (value_raw != null && !value_raw.trim().isEmpty()) {
                String valueR = value_raw.replace(".", "");
                double value = Double.parseDouble(valueR);
                salary.setValue(BigDecimal.valueOf(value));
            }

            // Xử lý file ảnh
            String imagePath = salary.getImage(); // Giữ đường dẫn ảnh cũ nếu không có ảnh mới
            if (filePartImage != null && filePartImage.getSize() > 0) {
                if (!isValidImageFile(extractFileName(filePartImage))) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print(gson.toJson(new ResponseMessage(false, "Only JPG, JPEG, PNG, GIF, WEBP files are allowed")));
                    out.flush();
                    return;
                }
                String pathHost = getServletContext().getRealPath("") + File.separator + IMAGE_DIR;
                imagePath = "uploads/" + FileUploadHelper.saveProfilePicture(filePartImage, pathHost, 1);
//                FileUploadHelper.saveProfilePicture(filePartImage,pathHost, 2);
                salary.setImage(imagePath);
            }

            // Cập nhật asset trong database
            dao.updateSalary(salary);

            // Xử lý danh sách PDF
            List<Integer> deletedPdfs = new ArrayList<>();
            if (deletedPdfsJson != null && !deletedPdfsJson.isEmpty()) {
                Integer[] deletedPdfsArray = gson.fromJson(deletedPdfsJson, Integer[].class);
                for (Integer pdfId : deletedPdfsArray) {
                    deletedPdfs.add(pdfId);
                }
            }
            if (!deletedPdfs.isEmpty()) {
                   pdfDAO.deletePdfs(deletedPdfs);
            }
            // Xử lý file PDF mới
            String pdfPath = null;
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String mimeType = filePart.getContentType();
                if (!mimeType.equals("application/pdf") || !fileName.toLowerCase().endsWith(".pdf")) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print(gson.toJson(new ResponseMessage(false, "Only PDF files are allowed")));
                    out.flush();
                    return;
                }

                String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                String uniqueFileName = salary.getTitle() + "_" + salary.getId() + "_" + fileNameWithoutExtension + fileExtension;

                // Lưu file PDF
                String pathHost = getServletContext().getRealPath("");
                String finalPath = pathHost.replace("build\\", "");
                String uploadPath = finalPath + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                filePart.write(uploadPath + File.separator + uniqueFileName);

                // Thêm PDF vào database
                PdfLis pdfLis = new PdfLis();
                pdfLis.setPdfName(uniqueFileName);
                pdfLis.setAssetID(id);
                pdfDAO.addPdfLis(pdfLis);
                pdfPath = UPLOAD_DIR + "/" + uniqueFileName;
            }

            // Lấy danh sách PDF mới
            List<PdfLis> updatedPdfList = pdfDAO.getpdfByAssetId(id);
            List<Salary> data = new ArrayList<>();
            data.add(salary);
            descriptionSetup(data);
            // Trả về JSON phản hồi
            ResponseData responseData = new ResponseData();
            responseData.setSuccess(true);
            responseData.setMessage("Asset updated successfully");
            responseData.setImagePath(imagePath);
            responseData.setListPdf(updatedPdfList);
            responseData.setTitle(salary.getTitle()); // Thêm title
            responseData.setDescription(salary.getDescription()); // Thêm description
            responseData.setValue(salary.getValue().toString()); // Thêm value
            out.print(gson.toJson(responseData));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ResponseMessage(false, "An error occurred: " + e.getMessage())));
            out.flush();
        }
    }
    public void descriptionSetup(List<Salary> data) {
        for (Salary salary : data) {
            StringBuilder result = new StringBuilder();
            String descript = salary.getDescription();
            String regex = "\n";
            if (!salary.getDescription().contains(regex)) {
                continue;
            }
            String[] des = descript.split(regex);
            for (String de : des) {
                
                result.append(de.trim()).append("<br>");
            }
            result.deleteCharAt(result.toString().length() - 1);
            salary.setDescription(result.toString());
        }
    }
    // Class để trả về thông báo trạng thái
    private static class ResponseMessage {

        boolean success;
        String message;

        ResponseMessage(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }

    // Class để trả về dữ liệu JSON
    private static class ResponseData {

        boolean success;
        String message;
        String imagePath;
        List<PdfLis> listPdf;
        String title; // Thêm
        String description; // Thêm
        String value; // Thêm

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getImagePath() {
            return imagePath;
        }

        public List<PdfLis> getListPdf() {
            return listPdf;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getValue() {
            return value;
        }

        void setSuccess(boolean success) {
            this.success = success;
        }

        void setMessage(String message) {
            this.message = message;
        }

        void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        void setListPdf(List<PdfLis> listPdf) {
            this.listPdf = listPdf;
        }

        void setTitle(String title) {
            this.title = title;
        }

        void setDescription(String description) {
            this.description = description;
        }

        void setValue(String value) {
            this.value = value;
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "1.jpg";
    }

    public static boolean isValidImageFile(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return false;
        }
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif", ".webp"};
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return Arrays.asList(allowedExtensions).contains(fileExtension);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to update asset information";
    }
}
