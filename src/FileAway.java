import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;



public class FileAway {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String line = null;
        ArrayList<String> lines = new ArrayList<>();


        try{

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);


            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){


                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int lineNumber = 0;
                int characterCount = 0;
                int wordCount = 0;

                while(reader.ready()) {

                    line = reader.readLine();
                    lines.add(line);
                    lineNumber++;
                    System.out.printf("\n %-4d ---> %-60s", lineNumber, line);


                    String[] words = line.split(" ");
                    wordCount = wordCount + words.length;


                    characterCount = characterCount + line.length();


                }

                String name = selectedFile.getName();


                reader.close();
                System.out.println("File read successfully");
                System.out.println("-------------------------------------");
                System.out.println("Name of File: " + name);
                System.out.println("Number of Lines: " + lineNumber);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + characterCount);


            }else{
                //if an error occurred or the user cancelled
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again");
                System.exit(0);
            }


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}