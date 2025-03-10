package controller.statistic.feedback;

import java.util.ArrayList;
import java.util.List;
import model.Feedback;

/**
 *
 * @author SCN
 */
public class GetListOfStar {

    public List<Integer> getListOfStar(List<Feedback> listOfFeedBack) {
        int count1star = 0, count2star = 0, count3star = 0, count4star = 0, count5star = 0;

        if (listOfFeedBack != null && !listOfFeedBack.isEmpty()) {
            for (Feedback feedback : listOfFeedBack) {
                int star = feedback.getStarScore();
                switch (star) {
                    case 1 ->
                        count1star++;
                    case 2 ->
                        count2star++;
                    case 3 ->
                        count3star++;
                    case 4 ->
                        count4star++;
                    case 5 ->
                        count5star++;
                }
            }
        }

        List<Integer> listOfStar = new ArrayList<>();
        listOfStar.add(count1star);
        listOfStar.add(count2star);
        listOfStar.add(count3star);
        listOfStar.add(count4star);
        listOfStar.add(count5star);
        
        return listOfStar;
    }
    
}
