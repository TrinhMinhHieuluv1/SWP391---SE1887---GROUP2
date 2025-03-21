package controller.user.management;

import Tools.HashString;
import controller.*;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.User;
import java.text.SimpleDateFormat;
import java.sql.Date;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import utils.ImageUploadUtil;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)

@WebServlet(name = "InsertUser", urlPatterns = {"/admin/insert_users"})
public class InsertUser extends HttpServlet {

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    public String setDateOfBirthToString(Date dateOfBirth) {
        // Định dạng ngày sinh theo kiểu yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Chuyển đổi ngày sinh thành chuỗi, nếu không có ngày thì để rỗng
        String dateOfBirthString = (dateOfBirth != null) ? sdf.format(dateOfBirth) : "";
        return dateOfBirthString;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get list manager
        List<User> listManager = userDao.selectAllUsersByRole(3);

        // loại bỏ các manager bị inactive
        for (int i = 0; i < listManager.size(); i++) {
            if (!listManager.get(i).isStatus()) { // Nếu status = false
                listManager.remove(i);
                i--; // Giảm i để kiểm tra lại phần tử ở vị trí i sau khi xóa
            }
        }
        request.getSession().setAttribute("listManager", listManager);
        request.getRequestDispatcher("FormAddUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathHost = getServletContext().getRealPath("");
        String finalPath = pathHost.replace("build\\", "");
        String uploadPath = finalPath + "uploads";

        String fileName = ImageUploadUtil.uploadImage(request, "img", uploadPath);
        String img ="";

        if (fileName != null && !fileName.startsWith("Invalid") && !fileName.startsWith("No") && !fileName.startsWith("Error")) {
            img = "../uploads/" + fileName;
        }
        if (fileName == null) {
            String errorMessage;
            errorMessage = "File upload failed! Please try again.";
            request.getSession().setAttribute("error", errorMessage);
            response.sendRedirect("insert_users");
            return;
        }

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        HashString hasStr = new HashString();
        password = hasStr.hashString(password);

        String name_raw = request.getParameter("fullname");
        String name = SearchUsers.normalizeString(name_raw);

        String gender = request.getParameter("gender");
        boolean isMale = gender.equals("1");

        String dob_raw = request.getParameter("dob");
        Date dob = Date.valueOf(dob_raw);

        String phone = request.getParameter("phonenumber").trim();
        String email = request.getParameter("email").trim();
        String cccd = request.getParameter("card").trim();

        String roleID_raw = request.getParameter("role");
        int roleID = Integer.parseInt(roleID_raw);

        String address_raw = request.getParameter("address");
        String address = SearchUsers.normalizeString(address_raw);

        String managerId_raw = request.getParameter("managerid");

        User manager = null;
        if (managerId_raw != null && !managerId_raw.isEmpty()) {
            managerId_raw = managerId_raw.trim();
            int manageID = Integer.parseInt(managerId_raw);
            manager = userDao.getManagerForSeller(manageID);
            if (manager == null) {
                request.getSession().setAttribute("error", "ManagerID does not exist !!");
                response.sendRedirect("insert_users");
                return;
            }
        }

        // add user
        User userToAdd = new User(0, username, password, name, img, phone, email, dob, isMale, address, cccd, roleID, true, manager, null);
        
        // check file name extension
        String errorMessage;
        if (fileName != null) {
            if (fileName.startsWith("Invalid file format")) {
                errorMessage = "Invalid file format! Please upload only .jpg, .jpeg, or .png files.";
                request.getSession().setAttribute("error", errorMessage);
                request.getSession().setAttribute("userToAdd", userToAdd);
                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            } else if (fileName.startsWith("File size exceeds")) {
                errorMessage = "File size exceeds 10MB limit! Please select a smaller file.";
                request.getSession().setAttribute("error", errorMessage);
                request.getSession().setAttribute("userToAdd", userToAdd);
                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            } else if (fileName.startsWith("No file found")) {
                errorMessage = "No file found! Please select a file to upload.";
                request.getSession().setAttribute("error", errorMessage);
                request.getSession().setAttribute("userToAdd", userToAdd);
                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            } else if (fileName.startsWith("Error uploading")) {
                errorMessage = "Failed to upload file. Please try again later: " + fileName.substring("Error uploading file: ".length());
                request.getSession().setAttribute("error", errorMessage);
                request.getSession().setAttribute("userToAdd", userToAdd);
                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            }
        }

        // check for seller
        if (roleID == 2 && manager == null) {
            request.getSession().setAttribute("error", "Seller must have a manager !!");
            request.getSession().setAttribute("userToAdd", userToAdd);

            String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
            request.getSession().setAttribute("dateOfBirth", dateOfBirth);
            response.sendRedirect("insert_users");
            return;
        }
        if (roleID != 2 && manager != null) {
            request.getSession().setAttribute("error", "Only sellers can have a manager !!");
            request.getSession().setAttribute("userToAdd", userToAdd);

            String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
            request.getSession().setAttribute("dateOfBirth", dateOfBirth);
            response.sendRedirect("insert_users");
            return;
        }

        int row = userDao.addUserReturnRow(userToAdd);
        switch (row) {
            case 1 -> {
                request.getSession().setAttribute("message", "Insert Successfully !!");
                HttpSession session = request.getSession(false);
                if (session != null) {
                    if (session.getAttribute("userToAdd") != null) {
                        session.removeAttribute("userToAdd");
                    }
                    if (session.getAttribute("dateOfBirth") != null) {
                        session.removeAttribute("dateOfBirth");
                    }
                }
            }
            case 2 -> {
                request.getSession().setAttribute("error", "Username already exists. Please enter another Username !!");
                request.getSession().setAttribute("userToAdd", userToAdd);

                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            }

            case 3 -> {
                request.getSession().setAttribute("error", "CCCD already exists. Please enter another CCCD !!");
                request.getSession().setAttribute("userToAdd", userToAdd);

                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            }

            case 4 -> {
                request.getSession().setAttribute("error", "Email already exists. Please enter another email !!");
                request.getSession().setAttribute("userToAdd", userToAdd);

                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            }

            case 5 -> {
                request.getSession().setAttribute("error", "Phone number already exists. Please enter another phone number !!");
                request.getSession().setAttribute("userToAdd", userToAdd);

                String dateOfBirth = setDateOfBirthToString(userToAdd.getDateOfBirth());
                request.getSession().setAttribute("dateOfBirth", dateOfBirth);
                response.sendRedirect("insert_users");
                return;
            }

            case 0 -> {
                request.getSession().setAttribute("error", "Insert fail !!");
            }
        }

        response.sendRedirect("insert_users");
    }

}
