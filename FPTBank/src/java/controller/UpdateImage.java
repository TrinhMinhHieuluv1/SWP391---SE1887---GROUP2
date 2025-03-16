/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.UserDAO;
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
import model.Customer;
import model.User;
import utils.ImageUploadUtil;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UpdateImage", urlPatterns = {"/updateimage"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateImage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateImage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url_image = request.getParameter("image");
        int uID = (int) session.getAttribute("uid");
        UserDAO dao = new UserDAO();
        String error = "";
        if (url_image.isEmpty()) {
            error = "Updatd Failed";
        } else {
            User account = (User) session.getAttribute("account");
            account.setImage(url_image);
            dao.updateAUser(account);
            session.setAttribute("account", account);
            error = "Upload Succesfully";
        }
        User user = dao.selectAnUserByConditions(uID, "", "", "");
        session.setAttribute("account", user);
        request.setAttribute("error2", error);
        request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object account = session.getAttribute("account");
        String pathHost = getServletContext().getRealPath("");
        String finalPath = pathHost.replace("build\\", "");
        String uploadPath = finalPath + "uploadsImg";

        String fileName = ImageUploadUtil.uploadImage(request, "image", uploadPath);
        String img = "";
        String error = "";
        if (fileName == null) {
            error = "File upload failed! Please try again.";
        }
        if (fileName.startsWith("Invalid file format")) {
            error = "Invalid file format! Please upload only .jpg, .jpeg, or .png files.";
        } else if (fileName.startsWith("File size exceeds")) {
            error = "File size exceeds 10MB limit! Please select a smaller file.";
        } else if (fileName.startsWith("No file found")) {
            error = "No file found! Please select a file to upload.";
        } else if (fileName.startsWith("Error uploading")) {
            error = "Failed to upload file. Please try again later: " + fileName.substring("Error uploading file: ".length());
        } else if (account instanceof User) {
            img = "../uploadsImg/" + fileName;
            UserDAO dao = new UserDAO();
            ((User) account).setImage(img);
            dao.updateAUser((User) account);
            error = "Upload Success";
        } else if (account instanceof Customer) {
            img = "../uploadsImg/" + fileName;
            CustomerDAO dao = new CustomerDAO();
            ((Customer) account).setImage(img);
            dao.updateCustomer((Customer) account);
            error = "Upload Success";
            request.setAttribute("status", true);
        }

        request.setAttribute(
                "error2", error);
        request.getRequestDispatcher(
                "updateprofile.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
