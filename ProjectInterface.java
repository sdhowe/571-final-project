import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * Created by Alexander Dang on 4/3/2016.
 */
public class ProjectInterface extends JFrame {
    private JComboBox comboBox1;
    private JButton simulateButton;
    private JSpinner spinner1;
    private JComboBox comboBox2;
    private JPanel rootPanel;
    private JTextArea textArea1;
    private JScrollPane scrollPane;
    private PrintStream standardOut;

    public ProjectInterface() {
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setVisible(true);

        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea1));
        System.setOut(printStream);

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kValue = (Integer) spinner1.getValue();

                Object obj = comboBox1.getSelectedItem();

                int algorithmIndex = comboBox1.getSelectedIndex();

                //String combo = obj.toString();

                switch (algorithmIndex) {
                    case 0:
                        //System.out.println("Selected algorithm: " + combo);
                        long startTime = System.nanoTime();
                        KMeans kmeans = new KMeans(kValue);
                        kmeans.init(kValue);
                        kmeans.calculate(kValue);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1000000;
                        System.out.println("Time to run: " + duration + "ms");
                        break;
                    case 1:
                        //System.out.println("Selected algorithm: " + combo);
                        break;
                    case 2:
                        //System.out.println("Selected algorithm: " + combo);
                        break;
                    case 3:
                        //System.out.println("Selected algorithm: " + combo);
                        break;
                }
            }
        });
    }
}