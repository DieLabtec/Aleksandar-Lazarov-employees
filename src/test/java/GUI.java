import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class GUI extends JFrame implements ActionListener {
    JButton button;
    String line;
    ArrayList<String> arrayList = new ArrayList<>();

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
            Map<String, ArrayList<Assignment>> assignmentMap = new HashMap<>();
            if (response == jFileChoser.APPROVE_OPTION) {
                File file = new File(jFileChoser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        arrayList.add(line);
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

                    System.out.println(arrayList);
                } catch (IOException exep) {
                    exep.printStackTrace();
                }

                for(int i = 1; i<assignmentsList.size(); i++){

                }

                HashMap<String, ArrayList<String>> employees = new HashMap<String, ArrayList<String>>();
                HelperFunctions.turnInformationFromArrayListToHashMap(employees, arrayList);

                Set<String> employeeNumbers = employees.keySet();
                Iterator<String> iterator = assignmentMap.keySet().iterator();
//                String next = iterator.next();
//                String second = iterator.next();

                while(iterator.hasNext()){
                    ArrayList<Assignment> currentValue = assignmentMap.get(iterator.next());
                    ArrayList<String> maxTimeWorkedTogether = new ArrayList<>();
                    if(currentValue.size()>1){
                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                        Date firstForStartDate = new Date();
                        Date secondForStartDate = new Date();
                        Date firstForEndDate = new Date();
                        Date secondForEndDate = new Date();

                        long holdDateForCompare;


                        for(int firstFor = 0;firstFor<currentValue.size();firstFor++){
                            for(int secondFor = firstFor+1; secondFor <currentValue.size();secondFor++){
                                try {
                                    firstForStartDate = format.parse(currentValue.get(firstFor).getStartDate());
                                    secondForStartDate = format.parse(currentValue.get(secondFor).getStartDate());
                                    firstForEndDate = format.parse(currentValue.get(firstFor).getEndDate());
                                    secondForEndDate = format.parse(currentValue.get(secondFor).getEndDate());
                                }catch(ParseException excep){
                                    excep.printStackTrace();
                                }
                                if(firstForStartDate.after(secondForEndDate) || secondForStartDate.after(firstForEndDate)){
                                    System.out.println("they have not worked together");
                                }
                                if(firstForStartDate.before(secondForStartDate) && firstForEndDate.after(secondForEndDate)){
                                   holdDateForCompare = secondForStartDate.getTime() - secondForEndDate.getTime();
                                }
                                if(secondForStartDate.before(firstForStartDate) && secondForEndDate.after(firstForEndDate)){
                                   holdDateForCompare = firstForEndDate.getTime() - firstForEndDate.getTime();
                                }
                                if(secondForStartDate.equals(firstForStartDate) && secondForEndDate.equals(firstForEndDate)){
                                   holdDateForCompare = firstForEndDate.getTime() - firstForEndDate.getTime();
                                }
                                holdDateForCompare =Math.min((firstForStartDate.getTime()-firstForEndDate.getTime()),(secondForStartDate.getTime()-secondForEndDate.getTime()));
                                if(holdDateForCompare > Long.parseLong(maxTimeWorkedTogether.get(2))){
                                    ArrayList<String> newBiggestValues = new ArrayList<>();
                                    newBiggestValues.add(currentValue.get(firstFor).getEmployeeID());
                                    newBiggestValues.add(currentValue.get(secondFor).getEmployeeID());
                                    newBiggestValues.add(currentValue.get(secondFor).getProjectID());
                                    newBiggestValues.add(Long.toString(holdDateForCompare));


                                }
                            }
                        }

//                        System.out.println(date);
//                        System.out.println(date2);
//                        if(date.equals(date2)){
//                            System.out.println("date is after");
//                        }
//                        System.out.println(firstForEndDate.getTime()-date2.getTime());
                    }

//                    iterator.next();
                }

                System.out.println("assignment map" + assignmentMap.keySet());
//                System.out.println(assignmentMap.get(second));
                System.out.println("correct print"+ employees.get("143"));

                System.out.println(employees);
                System.out.println(employees.keySet());
                System.out.println((employees.get("201")).size());

            }


        }
    }


}
