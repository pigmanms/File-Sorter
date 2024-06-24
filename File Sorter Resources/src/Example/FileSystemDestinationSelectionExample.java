package Example;

import javax.swing.*;
import java.awt.event.*;

public class FileSystemDestinationSelectionExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("File System Destination Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton selectButton = new JButton("Select Destination");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser
                JFileChooser fileChooser = new JFileChooser();

                // Set the file chooser to select directories (folders) only
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // Show the file chooser dialog and get the result
                int result = fileChooser.showOpenDialog(frame);

                // Check if the user selected a directory
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected directory
                    String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();

                    // Display the selected directory in a dialog box
                    JOptionPane.showMessageDialog(frame, "Selected Destination: " + selectedDirectory);
                }
            }
        });

        // Add the button to the frame
        frame.getContentPane().add(selectButton);

        // Set frame properties
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
