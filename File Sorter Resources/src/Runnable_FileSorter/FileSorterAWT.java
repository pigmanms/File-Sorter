package Runnable_FileSorter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//FIXME: Is this the AWT? looks like Swing...

public class FileSorterAWT extends JFrame {
    public static void main(String [] args) {
        JFrame mainFrame = new JFrame("File Sorter v20240503_CE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(500, 600);
        mainFrame.setLocationRelativeTo(null); //Center the window
        mainFrame.setVisible(true);
        mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.LINE_AXIS) ); //FIXME: WTF IS THIS???

        JButton selectSourceFolderLocationButton = new JButton("Select the source folder location");
        JButton selectDestinationFolderLocationButton = new JButton("Select the destination folder location");
        JButton RunTheJobButton = new JButton("Run/Execute");
        JFileChooser sourceDirectoryChooser = new JFileChooser();
        JFileChooser destinationDirectoryChooser = new JFileChooser();

        //SOURCE FOLDER DIRECTORY
        selectSourceFolderLocationButton.addActionListener(new ActionListener() {

            //@Override //FIXME: should this be used? I think no Override is ok...
            public void actionPerformed(ActionEvent e) {
                sourceDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int sourceFolderJudge = sourceDirectoryChooser.showOpenDialog(mainFrame);
                if(sourceFolderJudge == JFileChooser.APPROVE_OPTION) {
                    String sourceFolderDirectoryResult = sourceDirectoryChooser.getSelectedFile().getAbsolutePath();
                    JOptionPane.showMessageDialog(mainFrame, "Selected SourceFolder Directory: \n" + sourceFolderDirectoryResult);

                }
            }
        });

        //DESTINATION FOLDER DIRECTORY
        selectDestinationFolderLocationButton.addActionListener(new ActionListener() {

            //@Override //FIXME: should this be used? I think no Override is ok...
            public void actionPerformed(ActionEvent e) {
                destinationDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int destinationFolderJudge = destinationDirectoryChooser.showOpenDialog(mainFrame);
                if(destinationFolderJudge == JFileChooser.APPROVE_OPTION) {
                    String sourceFolderDirectoryResult = destinationDirectoryChooser.getSelectedFile().getAbsolutePath();
                    JOptionPane.showMessageDialog(mainFrame, "Selected SourceFolder Directory: \n" + sourceFolderDirectoryResult);

                }
            }
        });


        mainFrame.add(selectSourceFolderLocationButton);
        mainFrame.add(selectDestinationFolderLocationButton);
        mainFrame.add(RunTheJobButton);



        /** TEMP ISOLATION
         JLabel testA = new JLabel();

         testA.setBounds(0, 20, 200, 50);
         testA.setText("simple test");
         testA.setVisible(true);
         */

        mainFrame.requestFocusInWindow();
        mainFrame.setVisible(true);

    }


}

