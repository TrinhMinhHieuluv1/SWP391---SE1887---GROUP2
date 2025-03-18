package controller.statistic.feedback;

import java.util.List;

/**
 *
 * @author SCN
 */
public class ChartCalculatorHomePage {

    public String calDataChartFeedBack(List<Integer> listOfStar) {
        String data = "";
        if (listOfStar != null && !listOfStar.isEmpty()) {
            StringBuilder dataBuilder = new StringBuilder();
            for (Integer value : listOfStar) {
                dataBuilder.append(value).append(",");
            }
            if (dataBuilder.length() > 0) {
                data = dataBuilder.substring(0, dataBuilder.length() - 1);
            }
        }
        return data;
    }
}
