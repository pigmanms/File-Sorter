package Example;

import java.io.File;
import java.io.IOException;

import java.nio.file.*;

import java.util.Scanner;

public class UnderstandingJavaCopySystem {
    public static void main(String [] args){
        Path sourcePath = Paths.get("D:\\ANYTHINGTESTROOM\\source\\testcopy.txt");
        //String absoluteSourcePathString = "D:\\ANYTHINGTESTROOM\\source\\testcopy.txt";
        Path targetPath = Paths.get("D:\\ANYTHINGTESTROOM\\destination\\testcopy.txt");

        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.ATOMIC_MOVE);
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
