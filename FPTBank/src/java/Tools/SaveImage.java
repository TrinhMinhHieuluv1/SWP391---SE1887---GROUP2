package Tools;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class SaveImage {

    public String saveImageByFile(Part filePart, String dirPathToSaveImage, String fileName) {
        File dirToSaveImage = new File(dirPathToSaveImage);
        if (!dirToSaveImage.exists()) {
            dirToSaveImage.mkdir();
        }

        String fileName_raw;
        String filePath = "";
        try {
            fileName_raw = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (fileName_raw.endsWith(".jpg")) {
                fileName = fileName.concat(".jpg");
            } else {
                fileName = fileName.concat(".png");
            }
            filePath = dirPathToSaveImage + File.separator + fileName;
            if (filePath.endsWith(".png")) {
                File redundantFile = new File(filePath.replace(".png", ".jpg"));
                redundantFile.delete();
            } else if (filePath.endsWith(".jpg")) {
                File redundantFile = new File(filePath.replace(".jpg", ".png"));
                redundantFile.delete();
            }
            filePart.write(filePath);
            filePath = filePath.replace("D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\", "");
        } catch (IOException e) {
        }
        return ("\\timibank\\" + filePath).replace("\\", "\\\\");
    }

    public String saveImageByUrl(String url, String dirPathToSaveImage, String fileName) {
        File dirToSaveImage = new File(dirPathToSaveImage);
        if (!dirToSaveImage.exists()) {
            dirToSaveImage.mkdir();
        }

        String filePath = "";
        try {
            URL imageURL = new URL(url);
            InputStream inputStream = imageURL.openStream();
            if (url.endsWith(".jpg")) {
                fileName = fileName.concat(".jpg");
            } else {
                fileName = fileName.concat(".png");
            }
            filePath = dirPathToSaveImage + File.separator + fileName;
            if (filePath.endsWith(".png")) {
                File redundantFile = new File(filePath.replace(".png", ".jpg"));
                redundantFile.delete();
            } else if (filePath.endsWith(".jpg")) {
                File redundantFile = new File(filePath.replace(".jpg", ".png"));
                redundantFile.delete();
            }
            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            filePath = filePath.replace("D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\", "");
        } catch (IOException e) {
        }
        return ("\\timibank\\" + filePath).replace("\\", "\\\\");
    }
}
