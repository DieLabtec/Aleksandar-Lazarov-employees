import java.util.ArrayList;
import java.util.HashMap;

public class HelperFunctions {
   public static HashMap<String, ArrayList<String>> turnInformationFromArrayListToHashMap(HashMap<String, ArrayList<String>> hashmapToBeFilled, ArrayList<String> from ){
       for (int i = 1; i < from.size(); i++) {
           String split[] = from.get(i).split(",");
           String employeeID = split[0];
           ArrayList<String> dataToBeFilled = new ArrayList<>();

           for (int j = 1; j < split.length; j++) {
               dataToBeFilled.add(split[j]);
           }

           if (hashmapToBeFilled.containsKey(employeeID)) {
               hashmapToBeFilled.get(employeeID).addAll(dataToBeFilled);
           } else {
               hashmapToBeFilled.put(employeeID, dataToBeFilled);
           }
       }
       return hashmapToBeFilled;
   }
    /*
   public static Integer getNumberOfProjectsTheEmployeeWorkedOn{

    } */


}
