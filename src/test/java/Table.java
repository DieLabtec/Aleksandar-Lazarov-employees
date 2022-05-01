import javax.swing.*;
import java.util.ArrayList;

public class Table {
    JFrame f;
    JTable j;

    Table(ArrayList<String> dataForTable) {
        f = new JFrame();

        f.setTitle("JTable Example");

        String[][] data = {
                {dataForTable.get(0), dataForTable.get(1), dataForTable.get(2), dataForTable.get(3)},

        };


        String[] columnNames = {"EmployeeID1", "EmployeeID2", "ProjectID", "DaysWorkedTogether"};

        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }

}
