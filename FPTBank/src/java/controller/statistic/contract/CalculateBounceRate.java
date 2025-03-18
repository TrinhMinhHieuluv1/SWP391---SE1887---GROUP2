package controller.statistic.contract;

import java.util.List;
import model.Contract;

public class CalculateBounceRate {
    // tỉ lệ số hợp đồng bị hủy so với tổng số hợp đồng dc tạo

    public double getBounceRate(List<Contract> listOfContract) {
        if (listOfContract == null || listOfContract.isEmpty()) {
            return 0.0; 
        }

        int totalContracts = listOfContract.size();
        int rejectedContracts = 0;

        for (Contract contract : listOfContract) {
            if (contract != null && contract.getStatusID() == 3) {
                rejectedContracts++;
            }
        }

        double bounceRate = (rejectedContracts / (double) totalContracts) * 100;
        return bounceRate;
    }
}
