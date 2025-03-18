package controller.statistic.contract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.Contract;

public class CalculateTotalOfRevenue {

    // Total revenue saving = (amount * interestRate * (period / 12) ) + (amount * earlyWithDraw )
    // Total revenue loan = (amount * interestRate * (period / 12) ) + (amount * latePayment )
    public BigDecimal getTotalRevenueOfSaving(List<Contract> listOfContract) {
        BigDecimal totalRevenue = BigDecimal.ZERO;

        if (listOfContract == null || listOfContract.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (Contract c : listOfContract) {
            if (c.getStatusID() != 3 && c.getType().equalsIgnoreCase("saving")) { // id = 3 -> rejected và là saving contract

                BigDecimal interestRevenue = c.getAmount()
                        .multiply(BigDecimal.valueOf(c.getInterestRate())) // Amount * InterestRate
                        .multiply(BigDecimal.valueOf(c.getPeriod())) // * Period
                        .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
                // lấy 2 cso sau dấu thập phân, nếu số t3 ( số bỏ đi ) >= 5 -> làm tròn lên , còn < 5 -> giữ nguyên

                BigDecimal earlyWithdrawFee = c.getAmount().multiply(BigDecimal.valueOf(c.getEarlyWithdrawRate())); // Amount * EarlyWithdrawRate

                totalRevenue = totalRevenue.add(interestRevenue).add(earlyWithdrawFee);
            }
        }
        return totalRevenue;
    }

    public BigDecimal getTotalRevenueOfLoan(List<Contract> listOfContract) {
        BigDecimal totalRevenue = BigDecimal.ZERO;

        if (listOfContract == null || listOfContract.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (Contract c : listOfContract) {
            if (c.getStatusID() != 3) {

                if (c.getType().equalsIgnoreCase("secured loan") || c.getType().equalsIgnoreCase("unsecured loan")) {
                    BigDecimal interestRevenue = c.getAmount().multiply(BigDecimal.valueOf(c.getInterestRate()))
                            .multiply(BigDecimal.valueOf(c.getPeriod())).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

                    BigDecimal latePaymentFee = c.getAmount().multiply(BigDecimal.valueOf(c.getLatePaymentRate()));

                    totalRevenue = totalRevenue.add(interestRevenue).add(latePaymentFee);
                }

            }
        }
        return totalRevenue;
    }

    public Map<String, BigDecimal> calRevenueSavingByMonth(List<Contract> filteredContract) {
        Map<String, BigDecimal> revenueByMonth = new HashMap<>();
        List<String> MONTH_ORDER = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        for (Contract c : filteredContract) {
            LocalDate createAt = c.getCreateAt().toLocalDate();
            String monthShort = createAt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String yearMonthKey = createAt.getYear() + "-" + monthShort;

            BigDecimal interestRevenue = c.getAmount()
                    .multiply(BigDecimal.valueOf(c.getInterestRate()))
                    .multiply(BigDecimal.valueOf(c.getPeriod()))
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

            BigDecimal earlyWithdrawFee = c.getAmount().multiply(BigDecimal.valueOf(c.getEarlyWithdrawRate()));
            BigDecimal contractRevenue = interestRevenue.add(earlyWithdrawFee);

            revenueByMonth.put(yearMonthKey, revenueByMonth.getOrDefault(yearMonthKey, BigDecimal.ZERO).add(contractRevenue));
        }

        List<String> sortedKeys = new ArrayList<>(revenueByMonth.keySet());
        sortedKeys.sort(Comparator.comparing(key -> {
            String[] parts = key.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthIndex = MONTH_ORDER.indexOf(parts[1]);
            return year * 12 + monthIndex;
        }));

        Map<String, BigDecimal> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, revenueByMonth.get(key));
        }
        return sortedMap;
    }

    public Map<String, BigDecimal> calRevenueLoanByMonth(List<Contract> filteredContract) {
        Map<String, BigDecimal> revenueByMonth = new HashMap<>();
        List<String> MONTH_ORDER = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        for (Contract c : filteredContract) {
            LocalDate createAt = c.getCreateAt().toLocalDate();
            String monthShort = createAt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String yearMonthKey = createAt.getYear() + "-" + monthShort;

            BigDecimal interestRevenue = c.getAmount()
                    .multiply(BigDecimal.valueOf(c.getInterestRate()))
                    .multiply(BigDecimal.valueOf(c.getPeriod()))
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

            BigDecimal latePaymentFee = c.getAmount().multiply(BigDecimal.valueOf(c.getLatePaymentRate()));
            BigDecimal contractRevenue = interestRevenue.add(latePaymentFee);

            revenueByMonth.put(yearMonthKey, revenueByMonth.getOrDefault(yearMonthKey, BigDecimal.ZERO).add(contractRevenue));
        }

        List<String> sortedKeys = new ArrayList<>(revenueByMonth.keySet());
        sortedKeys.sort(Comparator.comparing(key -> {
            String[] parts = key.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthIndex = MONTH_ORDER.indexOf(parts[1]);
            return year * 12 + monthIndex;
        }));

        Map<String, BigDecimal> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, revenueByMonth.get(key));
        }
        return sortedMap;
    }

}
