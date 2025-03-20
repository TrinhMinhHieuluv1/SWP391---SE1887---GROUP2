package controller.serviceitem.management;

import dal.ServiceItemDAO;
import dal.UserDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ServiceItem;
import model.User;

@WebServlet(name = "UpdateStatusOfServiceItems", urlPatterns = {"/admin/updateStatus_ServiceItem"})
public class UpdateStatusOfServiceItems extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");
        Boolean checkUpdate = false;
        try {
            String serviceItemID_raw = request.getParameter("serviceItemID");
            int serviceItemID = Integer.parseInt(serviceItemID_raw);
            Boolean isActive = false;

            if (status != null && !status.isEmpty()) {
                ServiceItem sItem = sDao.selectAServiceItemByID(serviceItemID);
                if (status.equalsIgnoreCase("true")) {
                    isActive = true;
                }
                sItem.setStatus(isActive);
                sDao.updateStatusServiceItem(sItem);
                checkUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = checkUpdate ? "Update status successfully !!" : "Update status fail !!";
        response.getWriter().write("{\"success\": " + status + ", \"message\": \"" + message + "\"}");
    }

}
