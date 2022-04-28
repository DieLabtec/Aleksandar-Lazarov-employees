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
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            JFileChooser jFileChoser = new JFileChooser();
            int response = jFileChoser.showOpenDialog(null);

            if(response == jFileChoser.APPROVE_OPTION){
                File file = new File(jFileChoser.getSelectedFile().getAbsolutePath());
               try {
                   BufferedReader br = new BufferedReader(new FileReader(file));
                   while((line = br.readLine())!= null ){
                       arrayList.add(line);

                   }
                   System.out.println(arrayList);
               }catch (IOException exep){
                   exep.printStackTrace();
               }

                //System.out.println(arrayList.get(2));

                HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
                for (int i = 1 ; i<arrayList.size(); i++){
                    String split[] = arrayList.get(i).split(",", 3);

                    ArrayList<String> dataToBeFilled = new ArrayList<>();
                    for (int j = 1; j<split.length; j++){
                        dataToBeFilled.add(split[j]);
                    }

                    System.out.println("hashmap print:"+hashMap.get(split[0]));
                    if(hashMap.containsKey(split[0])){
                        hashMap.get(split[0]).addAll(dataToBeFilled) ;
                    }else {
                    hashMap.put(split[0], dataToBeFilled);}
                    //System.out.println(arrayList.get(i));
                }
                System.out.println(hashMap);
                    //System.out.println(String stuffs =  arrayList.get(1).split(",", 3));
                    String split[] = arrayList.get(1).split(",", 3);
                    System.out.println(split[1]);
            }


        }
    };

}
