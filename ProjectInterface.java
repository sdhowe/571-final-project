import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander Dang on 4/3/2016.
 */
public class ProjectInterface extends JFrame {
    private JComboBox comboBox1;
    private JButton simulateButton;
    private JSpinner spinner1;
    private JComboBox comboBox2;
    private JPanel rootPanel;

    public ProjectInterface() {
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kValue = (Integer) spinner1.getValue();

                Object obj = comboBox1.getSelectedItem();

                int algorithmIndex = comboBox1.getSelectedIndex();

                String combo = obj.toString();

                Object obj2 = comboBox2.getSelectedItem();
                String combo2 = obj2.toString();
                System.out.println("Selected data set: " + combo2);

                switch (algorithmIndex) {
                    case 0:
                        System.out.println("Selected algorithm: " + combo);
                        long startTime = System.nanoTime();
                        KMeans kmeans = new KMeans(kValue);
                        kmeans.init(kValue);
                        kmeans.calculate(kValue);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1000000;
                        System.out.println("Time to run: " + duration + "ms");
                        break;
                    case 1:
                        System.out.println("Selected algorithm: " + combo);
                        break;
                    case 2:
                        System.out.println("Selected algorithm: " + combo);
                        break;
                    case 3:
                        System.out.println("Selected algorithm: " + combo);
                        break;
                }
            }
        });
    }
}