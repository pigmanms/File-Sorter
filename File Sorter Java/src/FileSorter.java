import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.Scanner;

/**
 * So far, this code can only being executed in terminal.
 *
 * Working on UI materialization.
*/


public class FileSorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*Don't* input the pathname by \"<source folder pathname> \" ");
        System.out.println("Please input the pathname by <source folder pathname> ");
        System.out.print("Enter the source folder pathname: ");

        String sourceFolderPath = scanner.nextLine(); //in read source folder path
        String documentsFolderPath = sourceFolderPath + "\\Documents";  //FIXME: PRIO No.2
        String photoFolderPath = sourceFolderPath + "\\Photo";
        String videoFolderPath = sourceFolderPath + "\\Video";
        String audioFolderPath = sourceFolderPath + "\\Audio";
        String executablesFolderPath = sourceFolderPath + "\\Executables";
        String zipFolderPath = sourceFolderPath + "\\Zip Folders";


        File sourceFolder = new File(sourceFolderPath);

        File documentsFolder = new File(documentsFolderPath);
        File photoFolder = new File(photoFolderPath);
        File videoFolder = new File(videoFolderPath); //FIXME: Now working on here!! //FIXME: Here is PRIO No. 1
        File audioFolder = new File(audioFolderPath);
        File executablesFolder = new File(executablesFolderPath);
        File zipFolder = new File(zipFolderPath);


        if (!documentsFolder.exists()) {
            documentsFolder.mkdirs();
        }
        if (!photoFolder.exists()) { //FIXME: PRIO No.3
            photoFolder.mkdirs();
        }
        if(!videoFolder.exists()) {
            videoFolder.mkdirs();
        }
        if(!audioFolder.exists()) {
            audioFolder.mkdirs();
        }
        if(!executablesFolder.exists()) {
            executablesFolder.mkdirs();
        }
        if(!zipFolder.exists()) {
            zipFolder.mkdirs();
        }


        if (sourceFolder.exists() && sourceFolder.isDirectory()) {
            File[] files = sourceFolder.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().toLowerCase(); //FIXME: PRIO No.4
                    if (checkExtension(fileName, ".pdf", ".doc", ".docx", ".hwp", ".hwpx", ".show", ".cell", ".xlsx", ".ppt", ".pptx", ".xls", ".txt", ".log")) {
                        moveFile(file, new File(documentsFolder, fileName));
                    } else if(checkExtension(fileName, ".png", ".jpeg", ".jpg", ".webp", ".gif", ".dng")) {
                        moveFile(file, new File(photoFolder, fileName));
                    } else if(checkExtension(fileName, ".mp4")) {
                        moveFile(file, new File(videoFolder, fileName));
                    } else if(checkExtension(fileName, ".mp3", ".aac", ".wav")) {
                        moveFile(file, new File(audioFolder, fileName));
                    } else if(checkExtension(fileName, ".exe", ".jar")) {
                        moveFile(file, new File(executablesFolder, fileName));
                    } else if(checkExtension(fileName, ".zip")) {
                        moveFile(file, new File(zipFolder, fileName));
                    }
                }
            }

            System.out.println("Files sorted successfully.");
        } else {
            System.out.println("Source folder does not exist or is not a directory.");
        }

        scanner.close();
    }

    private static boolean checkExtension(String fileName, String... extensions) {
        for (String extension : extensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private static void moveFile(File sourceFile, File destinationFile) {
        try {
            Path sourcePath = sourceFile.toPath();
            Path destinationPath = destinationFile.toPath();
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved: " + sourceFile.getName() + " -> " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error while moving file: " + e.getMessage());
        }
    }
}



//by Pigman_MS(known as CaptainPMS, Kansas)