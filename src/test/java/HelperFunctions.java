import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelperFunctions {
    public static ArrayList<String> takeMaximunWorkedTimeFromHashMap(HashMap<String, ArrayList<Assignment>> takeFrom, String dateFormatType) {
        for (int i = 1; i < takeFrom.size(); i++) {

        }
        String dateFormat = "";
        if (dateFormatType == "monthsfirst") {
            dateFormat = "MM/dd/yyyy";
        }
        if (dateFormatType == "daysfirst") {
            dateFormat = "dd/MM/yyyy";
        }
        if (dateFormatType == "yearfirst") {
            dateFormat = "YYY/MM/DD";
        }
        HashMap<String, ArrayList<String>> employees = new HashMap<String, ArrayList<String>>();
        ArrayList<String> maxTimeWorkedTogether = new ArrayList<>();
        maxTimeWorkedTogether.add("0");
        maxTimeWorkedTogether.add("0");
        maxTimeWorkedTogether.add("0");
        maxTimeWorkedTogether.add("0");

        Iterator<String> iterator = takeFrom.keySet().iterator();


        while (iterator.hasNext()) {
            ArrayList<Assignment> currentValue = takeFrom.get(iterator.next());

            if (currentValue.size() > 1) {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date firstForStartDate = new Date();
                Date secondForStartDate = new Date();
                Date firstForEndDate = new Date();
                Date secondForEndDate = new Date();

                long holdDateForCompare;

                for (int firstFor = 0; firstFor < currentValue.size(); firstFor++) {
                    for (int secondFor = firstFor + 1; secondFor < currentValue.size(); secondFor++) {
                        try {
                            firstForStartDate = format.parse(currentValue.get(firstFor).getStartDate());
                            secondForStartDate = format.parse(currentValue.get(secondFor).getStartDate());
                            firstForEndDate = format.parse(currentValue.get(firstFor).getEndDate());
                            secondForEndDate = format.parse(currentValue.get(secondFor).getEndDate());
                        } catch (ParseException excep) {
                            excep.printStackTrace();
                        }
                        if (firstForStartDate.after(secondForEndDate) || secondForStartDate.after(firstForEndDate)) {
                            System.out.println("they have not worked together");
                        }
                        if (firstForStartDate.before(secondForStartDate) && firstForEndDate.after(secondForEndDate)) {
                            holdDateForCompare = secondForStartDate.getTime() - secondForEndDate.getTime();

                        }
                        if (secondForStartDate.before(firstForStartDate) && secondForEndDate.after(firstForEndDate)) {
                            holdDateForCompare = firstForStartDate.getTime() - firstForEndDate.getTime();
                        }
                        if (secondForStartDate.equals(firstForStartDate) && secondForEndDate.equals(firstForEndDate)) {
                            holdDateForCompare = firstForStartDate.getTime() - firstForEndDate.getTime();
                        }
                        holdDateForCompare = Math.min((firstForStartDate.getTime() - firstForEndDate.getTime()), (secondForStartDate.getTime() - secondForEndDate.getTime()));
                        //System.out.println("Hold"+" "+ Long.toString(holdDateForCompare = Math.abs(((firstForStartDate.getTime() - firstForEndDate.getTime())/(1000*60*60*24)))));
                        if (holdDateForCompare < Long.parseLong(maxTimeWorkedTogether.get(2))) {
                            ArrayList<String> newBiggestValues = new ArrayList<>();
                            newBiggestValues.add(currentValue.get(firstFor).getEmployeeID());
                            newBiggestValues.add(currentValue.get(secondFor).getEmployeeID());
                            newBiggestValues.add(currentValue.get(secondFor).getProjectID());
                            newBiggestValues.add(Long.toString(holdDateForCompare));
                            maxTimeWorkedTogether = newBiggestValues;

                        }
                        System.out.println("this is what you need" + Math.abs((Float.parseFloat(maxTimeWorkedTogether.get(3)) / (1000 * 60 * 60 * 24))));
                        maxTimeWorkedTogether.set(3, Float.toString(Math.abs((Float.parseFloat(maxTimeWorkedTogether.get(3)) / (1000 * 60 * 60 * 24)))));
                    }

                }

            }


        }
        return maxTimeWorkedTogether;
    }
}