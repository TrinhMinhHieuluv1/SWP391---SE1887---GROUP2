package utils;

import dal.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

public class UserRoleUtils {

    // lấy số lượng user theo từng role và set attribute vào request
    public static void setUserCountsForEachRole(HttpServletRequest request, UserDAO uDao) {
        // Map để lưu tên attribute và RoleID tương ứng
        Map<String, Integer> roles = new HashMap<>();
        roles.put("numOfAdmin", 1);
        roles.put("numOfSeller", 2);
        roles.put("numOfManager", 3);
        roles.put("numOfProviderInsurance", 4);
        roles.put("numOfCustomer", 5);

        // Lặp qua từng RoleID để lấy số lượng user và set attribute
        for (Map.Entry<String, Integer> entry : roles.entrySet()) {
            String attributeName = entry.getKey();
            int roleId = entry.getValue();
            int totalOfRole = uDao.getTotalUsers("RoleID", roleId);
            request.setAttribute(attributeName, totalOfRole);
        }
    }
}
