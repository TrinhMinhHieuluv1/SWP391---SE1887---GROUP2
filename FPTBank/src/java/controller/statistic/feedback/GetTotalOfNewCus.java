package controller.statistic.feedback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author SCN
 */
public class GetTotalOfNewCus {

    public int getTotalNumberOfNewCus(List<Customer> listOfCus) {
        List<Customer> filteredCustomers = new ArrayList<>();

        LocalDate fromDate = LocalDate.of(2024, 10, 1);
        LocalDate toDate = LocalDate.now();

        for (Customer c : listOfCus) {
            if (c.isStatus() && c.getCreatedAt() != null) {
                LocalDate createdAt = c.getCreatedAt().toLocalDate();
                if ((createdAt.isEqual(fromDate) || createdAt.isAfter(fromDate))
                        && (createdAt.isEqual(toDate) || createdAt.isBefore(toDate))) {

                    filteredCustomers.add(c);
                }
            }
        }
        return filteredCustomers.size();
    }

}
