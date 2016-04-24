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
    private JPanel rootPanel;
    private JTextArea textArea1;
    private JScrollPane scrollPane;
    private JComboBox comboBox2;
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
                int dataSize = Integer.parseInt((String) comboBox2.getSelectedItem());
                        Object obj = comboBox1.getSelectedItem();

                int algorithmIndex = comboBox1.getSelectedIndex();

                //String combo = obj.toString();

                // Decide which algorithm to execute
                switch (algorithmIndex) {
                    // Kmeans algorithm
                    case 0:
                        //System.out.println("Selected algorithm: " + combo);
                        long startTime = System.nanoTime();
                        KMeans kmeans = new KMeans(kValue);
                        kmeans.init(kValue, dataSize);
                        kmeans.calculate(kValue);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1000000;
                        System.out.println("Time to run: " + duration + "ms");
                        break;
                    // Hierarchical Clustering algorithm (complete linkage)
                    case 1:
                        //System.out.println("Selected algorithm: " + combo);
                        long startTime1 = System.nanoTime();
                        DendrogramPanel hccluster = new DendrogramPanel();
                        hccluster.startHCCluster();
                        long endTime1 = System.nanoTime();
                        long duration1 = (endTime1 - startTime1) / 1000000;
                        System.out.println("Time to run: " + duration1 + "ms");
                        break;
                    // Hierarchical Clustering algorithm (average linkage)
                    case 2:
                        //System.out.println("Selected algorithm: " + combo);

                        // Awfully redundant, yes... But for now refactoring is not a priority
                        long startTime2 = System.nanoTime();
                        DendrogramPanel hccluster1 = new DendrogramPanel();
                        hccluster1.startHCClusterAverage();
                        long endTime2 = System.nanoTime();
                        long duration2 = (endTime2 - startTime2) / 1000000;
                        System.out.println("Time to run: " + duration2 + "ms");
                        break;
                    case 3:
                        //System.out.println("Selected algorithm: " + combo);
                        break;
                }
            }
        });
    }
}