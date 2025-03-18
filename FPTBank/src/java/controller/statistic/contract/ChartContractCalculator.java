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

/**
 *
 * @author SCN
 */
public class ChartContractCalculator {

    public List<String> calculateCharContract(Map<String, Integer> contractForMonthMap) {
        List<String> listChar1 = new ArrayList<>();
        String labels01;
        String data01;
        String percentData01;

        int totalContract = 0;
        if (contractForMonthMap != null && !contractForMonthMap.isEmpty()) {
            for (Integer value : contractForMonthMap.values()) {
                totalContract += value;
            }
        }

        if (contractForMonthMap != null && !contractForMonthMap.isEmpty()) {
            StringBuilder label1 = new StringBuilder();
            StringBuilder data1 = new StringBuilder();
            StringBuilder percentData1 = new StringBuilder();

            for (Map.Entry<String, Integer> entry : contractForMonthMap.entrySet()) {
                label1.append("'").append(entry.getKey()).append("',");
                data1.append(entry.getValue()).append(",");
                double percentage = totalContract > 0 ? (entry.getValue() * 100.0) / totalContract : 0;
                percentData1.append(String.format("%.2f", percentage)).append(",");
            }

            if (label1.length() > 0) {
                label1.setLength(label1.length() - 1);
                data1.setLength(data1.length() - 1);
                percentData1.setLength(percentData1.length() - 1);
            }

            labels01 = label1.toString();
            data01 = data1.toString();
            percentData01 = percentData1.toString();

            listChar1.add(labels01);
            listChar1.add(data01);
            listChar1.add(percentData01);

        }
        return listChar1;
    }

    public Map<String, Integer> countContractByMonth(List<Contract> filteredContract) {
        Map<String, Integer> contractCountByMonth = new HashMap<>();
        List<String> MONTH_ORDER = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        for (Contract c : filteredContract) {
            LocalDate createAt = c.getCreateAt().toLocalDate();
            String monthShort = createAt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String yearMonthKey = createAt.getYear() + "-" + monthShort;
            contractCountByMonth.put(yearMonthKey, contractCountByMonth.getOrDefault(yearMonthKey, 0) + 1);
        }

        List<String> sortedKeys = new ArrayList<>(contractCountByMonth.keySet());
        sortedKeys.sort(Comparator.comparing(key -> {
            String[] parts = key.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthIndex = MONTH_ORDER.indexOf(parts[1]);
            return year * 12 + monthIndex;
        }));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, contractCountByMonth.get(key));
        }
        return sortedMap;
    }

    public List<String> calculateDataSaving(Map<String, BigDecimal> revenueSavingByMonthMap) {
        List<String> listChar1 = new ArrayList<>();

        if (revenueSavingByMonthMap == null || revenueSavingByMonthMap.isEmpty()) {
            return listChar1;
        }

        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (BigDecimal value : revenueSavingByMonthMap.values()) {
            totalRevenue = totalRevenue.add(value);
        }

        StringBuilder label1 = new StringBuilder();
        StringBuilder data1 = new StringBuilder();
        StringBuilder percentData1 = new StringBuilder();

        for (Map.Entry<String, BigDecimal> entry : revenueSavingByMonthMap.entrySet()) {
            label1.append("'").append(entry.getKey()).append("',");
            data1.append(entry.getValue()).append(",");

            // Tính phần trăm doanh thu từng tháng
            BigDecimal percentage = totalRevenue.compareTo(BigDecimal.ZERO) > 0
                    ? entry.getValue().multiply(BigDecimal.valueOf(100)).divide(totalRevenue, 2, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO;

            percentData1.append(percentage).append(",");
        }

        if (label1.length() > 0) {
            label1.setLength(label1.length() - 1);
            data1.setLength(data1.length() - 1);
            percentData1.setLength(percentData1.length() - 1);
        }

        listChar1.add(label1.toString());
        listChar1.add(data1.toString());
        listChar1.add(percentData1.toString());

        return listChar1;
    }
    
    
      public List<String> calculateDataLoan(Map<String, BigDecimal> revenueLoanByMonthMap) {
        List<String> listChar1 = new ArrayList<>();

        if (revenueLoanByMonthMap == null || revenueLoanByMonthMap.isEmpty()) {
            return listChar1;
        }

        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (BigDecimal value : revenueLoanByMonthMap.values()) {
            totalRevenue = totalRevenue.add(value);
        }

        StringBuilder label1 = new StringBuilder();
        StringBuilder data1 = new StringBuilder();
        StringBuilder percentData1 = new StringBuilder();

        for (Map.Entry<String, BigDecimal> entry : revenueLoanByMonthMap.entrySet()) {
            label1.append("'").append(entry.getKey()).append("',");
            data1.append(entry.getValue()).append(",");

            // Tính phần trăm doanh thu từng tháng
            BigDecimal percentage = totalRevenue.compareTo(BigDecimal.ZERO) > 0
                    ? entry.getValue().multiply(BigDecimal.valueOf(100)).divide(totalRevenue, 2, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO;

            percentData1.append(percentage).append(",");
        }

        if (label1.length() > 0) {
            label1.setLength(label1.length() - 1);
            data1.setLength(data1.length() - 1);
            percentData1.setLength(percentData1.length() - 1);
        }

        listChar1.add(label1.toString());
        listChar1.add(data1.toString());
        listChar1.add(percentData1.toString());

        return listChar1;
    }

}
