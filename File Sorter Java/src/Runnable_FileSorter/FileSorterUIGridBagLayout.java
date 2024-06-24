package Runnable_FileSorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSorterUIGridBagLayout extends JFrame {

    private String sourceFolderDirectory;
    private String destinationFolderDirectory;
    private JLabel sourceFolderLabel;
    private JLabel destinationFolderLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileSorterUIGridBagLayout ui = new FileSorterUIGridBagLayout();
            ui.setVisible(true);
        });
    }

    public FileSorterUIGridBagLayout() {
        setTitle("File Sorter v20240503_CE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton selectSourceFolderLocationButton = new JButton("Select the source folder location");
        JButton selectDestinationFolderLocationButton = new JButton("Select the destination folder location");
        JButton runTheJobButton = new JButton("Run/Execute");

        sourceFolderLabel = new JLabel("Source folder: Not selected");
        destinationFolderLabel = new JLabel("Destination folder: Not selected");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(selectSourceFolderLocationButton, gbc);

        gbc.gridy = 1;
        add(sourceFolderLabel, gbc);

        gbc.gridy = 2;
        add(selectDestinationFolderLocationButton, gbc);

        gbc.gridy = 3;
        add(destinationFolderLabel, gbc);

        gbc.gridy = 4;
        add(runTheJobButton, gbc);

        JFileChooser sourceDirectoryChooser = new JFileChooser();
        JFileChooser destinationDirectoryChooser = new JFileChooser();

        // SOURCE FOLDER DIRECTORY
        selectSourceFolderLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sourceDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int sourceFolderJudge = sourceDirectoryChooser.showOpenDialog(FileSorterUIGridBagLayout.this);
                if (sourceFolderJudge == JFileChooser.APPROVE_OPTION) {
                    sourceFolderDirectory = sourceDirectoryChooser.getSelectedFile().getAbsolutePath();
                    sourceFolderLabel.setText("Source folder: " + sourceFolderDirectory);
                }
            }
        });

        // DESTINATION FOLDER DIRECTORY
        selectDestinationFolderLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                destinationDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int destinationFolderJudge = destinationDirectoryChooser.showOpenDialog(FileSorterUIGridBagLayout.this);
                if (destinationFolderJudge == JFileChooser.APPROVE_OPTION) {
                    destinationFolderDirectory = destinationDirectoryChooser.getSelectedFile().getAbsolutePath();
                    destinationFolderLabel.setText("Destination folder: " + destinationFolderDirectory);
                }
            }
        });

        // RUN/EXECUTE BUTTON
        runTheJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sourceFolderDirectory != null && destinationFolderDirectory != null) {
                    sortFiles(new File(sourceFolderDirectory), new File(destinationFolderDirectory));
                } else {
                    JOptionPane.showMessageDialog(FileSorterUIGridBagLayout.this, "Please select both source and destination folders.");
                }
            }
        });
    }

    private void sortFiles(File sourceFolder, File destinationFolder) {
        if (sourceFolder.exists() && sourceFolder.isDirectory()) {
            File[] files = sourceFolder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().toLowerCase();
                    File destinationSubFolder = null;
                    if (checkExtension(fileName, ".pdf", ".doc", ".docx", ".hwp", ".hwpx", ".show", ".cell", ".xlsx", ".ppt", ".pptx", ".xls", ".txt", ".log")) {
                        destinationSubFolder = new File(destinationFolder, "Documents");
                    } else if (checkExtension(fileName, ".png", ".jpeg", ".jpg", ".webp", ".gif", ".dng")) {
                        destinationSubFolder = new File(destinationFolder, "Photos");
                    } else if (checkExtension(fileName, ".mp4")) {
                        destinationSubFolder = new File(destinationFolder, "Videos");
                    } else if (checkExtension(fileName, ".mp3", ".aac", ".wav")) {
                        destinationSubFolder = new File(destinationFolder, "Audio");
                    } else if (checkExtension(fileName, ".exe", ".jar")) {
                        destinationSubFolder = new File(destinationFolder, "Executables");
                    } else if (checkExtension(fileName, ".zip")) {
                        destinationSubFolder = new File(destinationFolder, "ZipFolders");
                    }

                    if (destinationSubFolder != null) {
                        if (!destinationSubFolder.exists()) {
                            destinationSubFolder.mkdirs();
                        }
                        moveFile(file, new File(destinationSubFolder, file.getName()));
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Files sorted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Source folder does not exist or is not a directory.");
        }
    }

    private boolean checkExtension(String fileName, String... extensions) {
        for (String extension : extensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private void moveFile(File sourceFile, File destinationFile) {
        try {
            Path sourcePath = sourceFile.toPath();
            Path destinationPath = destinationFile.toPath();
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved: " + sourceFile.getName() + " -> " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error while moving file: " + e.getMessage());
        }
    }
}
