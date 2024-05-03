package Runnable_FileSorter

import javax.swing.*

object FileSorterKotlin : JFrame() {
    @JvmStatic
    fun main(args: Array<String>) {
        val mainFrame = JFrame("File Sorter v20240503_CE")
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE)
        mainFrame.setSize(500, 600)
        mainFrame.setLocationRelativeTo(null) //Center the window
        mainFrame.isVisible = true
        mainFrame.layout = BoxLayout(mainFrame, BoxLayout.LINE_AXIS) //FIXME: WTF IS THIS???
        val selectSourceFolderLocationButton = JButton("Select the source folder location")
        val selectDestinationFolderLocationButton = JButton("Select the destination folder location")
        val RunTheJobButton = JButton("Run/Execute")
        val sourceDirectoryChooser = JFileChooser()
        val destinationDirectoryChooser = JFileChooser()

        //SOURCE FOLDER DIRECTORY
        selectSourceFolderLocationButton.addActionListener {
            sourceDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
            val sourceFolderJudge = sourceDirectoryChooser.showOpenDialog(mainFrame)
            if (sourceFolderJudge == JFileChooser.APPROVE_OPTION) {
                val sourceFolderDirectoryResult = sourceDirectoryChooser.selectedFile.absolutePath
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Selected SourceFolder Directory: \n$sourceFolderDirectoryResult"
                )
            }
        }

        //DESTINATION FOLDER DIRECTORY
        selectDestinationFolderLocationButton.addActionListener {
            destinationDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
            val destinationFolderJudge = destinationDirectoryChooser.showOpenDialog(mainFrame)
            if (destinationFolderJudge == JFileChooser.APPROVE_OPTION) {
                val sourceFolderDirectoryResult = destinationDirectoryChooser.selectedFile.absolutePath
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Selected SourceFolder Directory: \n$sourceFolderDirectoryResult"
                )
            }
        }
        mainFrame.add(selectSourceFolderLocationButton)
        mainFrame.add(selectDestinationFolderLocationButton)
        mainFrame.add(RunTheJobButton)
        /** TEMP ISOLATION
         * JLabel testA = new JLabel();
         *
         * testA.setBounds(0, 20, 200, 50);
         * testA.setText("simple test");
         * testA.setVisible(true);
         */
        mainFrame.requestFocusInWindow()
        mainFrame.isVisible = true
    }
}
