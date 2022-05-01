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
            jFileChoser.setCurrentDirectory(new File("."));
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

                maxTimeWorkedTogether = HelperFunctions.takeMaximunWorkedTimeFromHashMap(assignmentMap, "monthsfirst");

                new Table(maxTimeWorkedTogether);

            }

        }

    }
}





