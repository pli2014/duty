import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Administrator on 14-3-22.
 */
public class GeneratorFile {
    public static void main(String[] args) throws Exception {
        int i = 0;
        String path = "D:\\data\\img" + "\\";
        ArrayList<String> al = new ArrayList<String>();
        while (i < 200) {
            Files.createFile(Paths.get(path + i + ".jpg"));
            Files.createFile(Paths.get(path + i + ".tpl"));
            i++;
        }
    }
}
