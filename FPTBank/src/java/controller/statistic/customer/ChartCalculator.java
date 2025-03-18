package controller.statistic.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SCN
 */
public class ChartCalculator {

    public List<String> calculateCharNewCusByDay(Map<String, Integer> newCusMapByDay) {
        List<String> listChar1 = new ArrayList<>();
        String labels01;
        String data01;
        String percentData01;

        int totalUsers = 0;
        if (newCusMapByDay != null) {
            for (int value : newCusMapByDay.values()) {
                totalUsers += value;
            }
        }

        if (newCusMapByDay != null && !newCusMapByDay.isEmpty()) {
            StringBuilder label1 = new StringBuilder();
            StringBuilder data1 = new StringBuilder();
            StringBuilder percentData1 = new StringBuilder();

            for (Map.Entry<String, Integer> entry : newCusMapByDay.entrySet()) {
                label1.append("'").append(entry.getKey()).append("',");
                data1.append(entry.getValue()).append(",");
                double percentage = totalUsers > 0 ? (entry.getValue() * 100.0) / totalUsers : 0;
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

    public List<String> calculateChar1(Map<String, Integer> newCusMapByMonth) {
        List<String> listChar1 = new ArrayList<>();
        String labels01;
        String data01;
        String percentData01;

        int totalUsers = 0;
        if (newCusMapByMonth != null) {
            for (int value : newCusMapByMonth.values()) {
                totalUsers += value;
            }
        }

        if (newCusMapByMonth != null && !newCusMapByMonth.isEmpty()) {
            StringBuilder label1 = new StringBuilder();
            StringBuilder data1 = new StringBuilder();
            StringBuilder percentData1 = new StringBuilder();

            for (Map.Entry<String, Integer> entry : newCusMapByMonth.entrySet()) {
                label1.append("'").append(entry.getKey()).append("',");
                data1.append(entry.getValue()).append(",");
                double percentage = totalUsers > 0 ? (entry.getValue() * 100.0) / totalUsers : 0;
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

    public List<Double> calPercentageOfChar2(List<Integer> creditScoreData, int total_cus) {
        List<Double> listPercentChar2 = new ArrayList<>();
        double percentPoor;
        double percentFair;
        double percentGood;
        double percentVeryGood;
        double percentExcellent;

        if (creditScoreData != null && !creditScoreData.isEmpty()) {
            percentPoor = total_cus > 0 ? ((double) creditScoreData.get(0) / total_cus) * 100 : 0;
            percentFair = total_cus > 0 ? ((double) creditScoreData.get(1) / total_cus) * 100 : 0;
            percentGood = total_cus > 0 ? ((double) creditScoreData.get(2) / total_cus) * 100 : 0;
            percentVeryGood = total_cus > 0 ? ((double) creditScoreData.get(3) / total_cus) * 100 : 0;
            percentExcellent = total_cus > 0 ? ((double) creditScoreData.get(4) / total_cus) * 100 : 0;

            listPercentChar2.add(percentPoor);
            listPercentChar2.add(percentFair);
            listPercentChar2.add(percentGood);
            listPercentChar2.add(percentVeryGood);
            listPercentChar2.add(percentExcellent);
        }
        return listPercentChar2;
    }

    public String calDataChart2(List<Integer> creditScoreData) {
        String data = "";
        if (creditScoreData != null && !creditScoreData.isEmpty()) {
            StringBuilder dataBuilder = new StringBuilder();
            for (Integer value : creditScoreData) {
                dataBuilder.append(value).append(",");
            }
            if (dataBuilder.length() > 0) {
                data = dataBuilder.substring(0, dataBuilder.length() - 1);
            }
        }
        return data;
    }

    public List<Double> calPercentageOfChar4(List<Integer> balanceOfData, int total_cus) {
        List<Double> listPercentChar4 = new ArrayList<>();
        double percentBasic;
        double percentEmerging;
        double percentMiddle;
        double percentUpper;
        double percentVip;

        if (balanceOfData != null && !balanceOfData.isEmpty()) {
            percentBasic = total_cus > 0 ? ((double) balanceOfData.get(0) / total_cus) * 100 : 0;
            percentEmerging = total_cus > 0 ? ((double) balanceOfData.get(1) / total_cus) * 100 : 0;
            percentMiddle = total_cus > 0 ? ((double) balanceOfData.get(2) / total_cus) * 100 : 0;
            percentUpper = total_cus > 0 ? ((double) balanceOfData.get(3) / total_cus) * 100 : 0;
            percentVip = total_cus > 0 ? ((double) balanceOfData.get(4) / total_cus) * 100 : 0;

            listPercentChar4.add(percentBasic);
            listPercentChar4.add(percentEmerging);
            listPercentChar4.add(percentMiddle);
            listPercentChar4.add(percentUpper);
            listPercentChar4.add(percentVip);
        }
        return listPercentChar4;
    }

    public String calDataChart4(List<Integer> balanceOfData) {
        String data = "";
        if (balanceOfData != null && !balanceOfData.isEmpty()) {
            StringBuilder dataBuilder = new StringBuilder();
            for (Integer value : balanceOfData) {
                dataBuilder.append(value).append(",");
            }
            if (dataBuilder.length() > 0) {
                data = dataBuilder.substring(0, dataBuilder.length() - 1);
            }
        }
        return data;
    }

}
