import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUI extends JFrame implements ActionListener {
    JButton button;
    String line;

    GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        button = new JButton("Select File");
        button.addActionListener(this);


        add(button);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            JFileChooser jFileChoser = new JFileChooser();
            int response = jFileChoser.showOpenDialog(null);
            List<Assignment> assignmentsList = new ArrayList<>();
            HashMap<String, ArrayList<Assignment>> assignmentMap = new HashMap<>();
            if (response == jFileChoser.APPROVE_OPTION) {
                File file = new File(jFileChoser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        String split[] = line.split(",");
                        Assignment assignment = new Assignment(split[0], split[1], split[2], split[3]);
                        if (assignmentMap.containsKey(split[1])) {
                            assignmentMap.get(split[1]).add(assignment);
                        } else {
                            ArrayList<Assignment> assignmentsLists = new ArrayList<>();
                            assignmentMap.put(split[1], assignmentsLists);
                            assignmentMap.get(split[1]).add(assignment);
                        }

                    }
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
                HashMap<String, ArrayList<String>> employees = new HashMap<String, ArrayList<String>>();
                ArrayList<String> maxTimeWorkedTogether = new ArrayList<>();
//                for(int i = 1; i<assignmentsList.size(); i++){
//
//                }
//
//                HashMap<String, ArrayList<String>> employees = new HashMap<String, ArrayList<String>>();
//                ArrayList<String> maxTimeWorkedTogether = new ArrayList<>();
//                maxTimeWorkedTogether.add("0");
//                maxTimeWorkedTogether.add("0");
//                maxTimeWorkedTogether.add("0");
//                maxTimeWorkedTogether.add("0");
//
//                Iterator<String> iterator = assignmentMap.keySet().iterator();
//
//
//                while(iterator.hasNext()){
//                    ArrayList<Assignment> currentValue = assignmentMap.get(iterator.next());
//
//                    if(currentValue.size()>1){
//                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//                        Date firstForStartDate = new Date();
//                        Date secondForStartDate = new Date();
//                        Date firstForEndDate = new Date();
//                        Date secondForEndDate = new Date();
//
//                        long holdDateForCompare;
//
//
//                        for(int firstFor = 0;firstFor<currentValue.size();firstFor++){
//                            for(int secondFor = firstFor+1; secondFor <currentValue.size();secondFor++){
//                                try {
//                                    firstForStartDate = format.parse(currentValue.get(firstFor).getStartDate());
//                                    secondForStartDate = format.parse(currentValue.get(secondFor).getStartDate());
//                                    firstForEndDate = format.parse(currentValue.get(firstFor).getEndDate());
//                                    secondForEndDate = format.parse(currentValue.get(secondFor).getEndDate());
//                                }catch(ParseException excep){
//                                    excep.printStackTrace();
//                                }
//                                if(firstForStartDate.after(secondForEndDate) || secondForStartDate.after(firstForEndDate)){
//                                    System.out.println("they have not worked together");
//                                }
//                                if(firstForStartDate.before(secondForStartDate) && firstForEndDate.after(secondForEndDate)){
//                                   holdDateForCompare = secondForStartDate.getTime() - secondForEndDate.getTime();
//
//                                }
//                                if(secondForStartDate.before(firstForStartDate) && secondForEndDate.after(firstForEndDate)){
//                                   holdDateForCompare = firstForStartDate.getTime() - firstForEndDate.getTime();
//                                }
//                                if(secondForStartDate.equals(firstForStartDate) && secondForEndDate.equals(firstForEndDate)){
//                                   holdDateForCompare = firstForStartDate.getTime() - firstForEndDate.getTime();
//                                }
//                                holdDateForCompare =Math.min((firstForStartDate.getTime()-firstForEndDate.getTime()),(secondForStartDate.getTime()-secondForEndDate.getTime()));
//                                //System.out.println("Hold"+" "+ Long.toString(holdDateForCompare = Math.abs(((firstForStartDate.getTime() - firstForEndDate.getTime())/(1000*60*60*24)))));
//                                if(holdDateForCompare < Long.parseLong(maxTimeWorkedTogether.get(2))){
//                                    ArrayList<String> newBiggestValues = new ArrayList<>();
//                                    newBiggestValues.add(currentValue.get(firstFor).getEmployeeID());
//                                    newBiggestValues.add(currentValue.get(secondFor).getEmployeeID());
//                                    newBiggestValues.add(currentValue.get(secondFor).getProjectID());
//                                    newBiggestValues.add(Long.toString(holdDateForCompare));
//                                    maxTimeWorkedTogether = newBiggestValues;
//
//                                }
//                                System.out.println("this is what you need"+ Math.abs((Float.parseFloat(maxTimeWorkedTogether.get(3))/(1000*60*60*24))));
//                            }
//
//                        }
                maxTimeWorkedTogether = HelperFunctions.takeMaximunWorkedTimeFromHashMap(assignmentMap,"monthsfirst");

                System.out.println("MaxTimeWOrked"+ maxTimeWorkedTogether);
                System.out.println("assignment map" + assignmentMap.keySet());
                //String timeInDays = Float.toString(Math.abs((Float.parseFloat(maxTimeWorkedTogether.get(2))/(1000*60*60*24))));
                //System.out.println(timeInDays);
                new Table(maxTimeWorkedTogether);

//                        System.out.println(date);
//                        System.out.println(date2);
//                        if(date.equals(date2)){
//                            System.out.println("date is after");
//                        }
//                        System.out.println(firstForEndDate.getTime()-date2.getTime());
                    }

//                    iterator.next();
                }




            }
}





