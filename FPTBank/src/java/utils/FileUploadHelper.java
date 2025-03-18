/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import jakarta.servlet.http.Part;

public class FileUploadHelper {   
    public static String saveProfilePicture(Part filePart,String uploadPath,int loai) throws Exception {
      String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
       String pathUp="";       
      if(loai==1){
                 pathUp = uploadPath.replace("build\\", "") + File.separator + fileName; 
      }else if(loai==2){
                 pathUp = uploadPath + File.separator + fileName; 

      }

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Tạo thư mục nếu chưa có
        }

        // Lưu file vào thư mục
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(pathUp)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return   fileName; // Trả về đường dẫn tương đối để lưu vào DB
    }
}
    

