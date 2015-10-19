package agregator.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExtractor {
    public static List<String> extract(String folderPath) {
        File targetFolder = new File(folderPath);
        File[] files = targetFolder.listFiles();
        List<String> fileNames = new ArrayList<String>();
        
        for (File file: files) {
            fileNames.add(file.getName());
        }
        
        return fileNames;
    }
}