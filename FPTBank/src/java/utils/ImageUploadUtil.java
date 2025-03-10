package utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ImageUploadUtil {

    /**
     * Upload image từ form và lưu vào thư mục chỉ định.
     *
     * @param request HttpServletRequest để lấy thông tin file
     * @param inputName Tên của input trong form (ví dụ: "img")
     * @param uploadFolderPath Đường dẫn thư mục chứa upload
     * @return Tên image đã được upload (nếu thành công), hoặc null nếu thất bại
     * @throws ServletException Nếu xảy ra lỗi khi xử lý file
     * @throws IOException Nếu xảy ra lỗi khi ghi file
     *
     */
//     String pathHost = getServletContext().getRealPath(""); 
//     String finalPath = pathHost.replace("build\\", "");
//     String uploadPath = finalPath + "uploads";
    public static String uploadImage(HttpServletRequest request, String inputName, String uploadFolderPath)
            throws ServletException, IOException {

        // Tạo thư mục upload nếu chưa tồn tại
        File uploadDir = new File(uploadFolderPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Lấy thông tin file từ form
        Part filePart = request.getPart(inputName);
        if (filePart == null) {
            return "No file found !!";
        }

        // Lấy ra tên ảnh được upload lên
        String fileName = filePart.getSubmittedFileName();
        if (fileName == null || fileName.isEmpty()) {
            return "No file name provided !!";
        }

        // Kiểm tra phần mở rộng của file
        String fileExtension = getFileExtension(fileName).toLowerCase();
        String[] validExtensions = {"jpg", "jpeg", "png"}; // Các định dạng hợp lệ
        boolean isValidExtension = false;

        for (String ext : validExtensions) {
            if (ext.equals(fileExtension)) {
                isValidExtension = true;
                break;
            }
        }

        if (!isValidExtension) {
            return "Invalid file format! Only .jpg, .jpeg, .png are allowed.";
        }

        // Kiểm tra kích thước file (tùy chọn)
        long fileSize = filePart.getSize();
        long maxSize = 10 * 1024 * 1024; // 10MB
        if (fileSize > maxSize) {
            return "File size exceeds 10MB limit!";
        }

        // Đường dẫn lưu file
        File filePath = new File(uploadFolderPath + File.separator + fileName);

        // File ảnh đã tồn tại trước đó
        if (filePath.exists()) {
            String newFileName = System.currentTimeMillis() + "_" + fileName; // Thêm thời gian vào tên file ảnh
            filePath = new File(uploadFolderPath + File.separator + newFileName);
            fileName = newFileName;
        }

        try {
            // Lưu file vào server
            Files.copy(filePart.getInputStream(), filePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return fileName; // Trả về tên file sau khi upload thành công
        } catch (IOException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}
