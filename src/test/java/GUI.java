import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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

            if (response == jFileChoser.APPROVE_OPTION) {
                File file = new File(jFileChoser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        arrayList.add(line);

                    }
                    System.out.println(arrayList);
                } catch (IOException exep) {
                    exep.printStackTrace();
                }

                HashMap<String, ArrayList<String>> employees = new HashMap<String, ArrayList<String>>();
                for (int i = 1; i < arrayList.size(); i++) {
                    String split[] = arrayList.get(i).split(",");
                    String employeeID = split[0];
                    ArrayList<String> dataToBeFilled = new ArrayList<>();

                    for (int j = 1; j < split.length; j++) {
                        dataToBeFilled.add(split[j]);
                    }

                    if (employees.containsKey(employeeID)) {
                        employees.get(employeeID).addAll(dataToBeFilled);
                    } else {
                        employees.put(employeeID, dataToBeFilled);
                    }
                }
                System.out.println(employees);
            }


        }
    }

    ;

}
