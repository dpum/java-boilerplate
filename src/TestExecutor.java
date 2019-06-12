import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class TestExecutor {
    private static FileInputStream getFileInputStream(Class baseClass, String fileName) throws URISyntaxException, FileNotFoundException {
        return new FileInputStream(new File(baseClass.getResource(fileName).toURI()));
    }

    public static void main(String[] args) {
        File tempOutputFile = null;
        PrintStream out = System.out;
        try{
            Properties propertiesSet = new Properties();
            propertiesSet.load(getFileInputStream(TestExecutor.class, "config.properties"));
            String CLASS_NAME = propertiesSet.getProperty("class");
            Integer NUMBER_OF_TEST_CASES = Integer.parseInt(propertiesSet.getProperty("numberOfTestFiles"));
            Class<?> solutionClass = Class.forName(CLASS_NAME);

            for (int i = 0; i < NUMBER_OF_TEST_CASES; i++) {
                String inputFileName = String.format("%d.in", i);
                System.out.println("\nRunning file "+inputFileName);

                tempOutputFile = File.createTempFile(inputFileName, ".tmp");
                System.setOut(new PrintStream(tempOutputFile));
                System.setIn(getFileInputStream(solutionClass, inputFileName));

                solutionClass.getMethod("main", String[].class).invoke(null, (Object) null);

                String outputFilePath = solutionClass.getResource(String.format("%d.out", i)).toURI().getPath();
                Process diffProcess = Runtime.getRuntime()
                        .exec(String.format("diff %s %s", tempOutputFile.getAbsolutePath(), outputFilePath));

                System.setOut(out);
                System.out.println(new String(diffProcess.getInputStream().readAllBytes(), StandardCharsets.ISO_8859_1));
                System.out.println(new String(diffProcess.getErrorStream().readAllBytes(), StandardCharsets.ISO_8859_1));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            tempOutputFile.deleteOnExit();
        }
    }
}