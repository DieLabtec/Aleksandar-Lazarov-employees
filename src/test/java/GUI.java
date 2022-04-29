import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
                Iterator<String> iterator = employeeNumbers.iterator();
                String next = iterator.next();
                String second = iterator.next();


                System.out.println("assignment map" + assignmentMap.get(" 11"));
                System.out.println(assignmentsList);
                System.out.println("correct print"+ employees.get("143"));

                System.out.println(employees);
                System.out.println(employees.keySet());
                System.out.println((employees.get("201")).size());

            }


        }
    }


}
