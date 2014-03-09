package actions;

import sun.misc.BASE64Decoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 14-3-9.
 */
public class HeadJpgAction {

    public static boolean decodeBase64Img(String imgStr, String imgFilePath) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Path imgStr = Paths.get("E:\\workspace\\duty\\src\\actions\\base64png.txt");
        byte[] bytes = Files.readAllBytes(imgStr);
        //remove prefixed string data:image/png;base64,
        HeadJpgAction.decodeBase64Img(new String(bytes).substring("data:image/png;base64,".length()), "E:\\workspace\\duty\\src\\actions\\base64png.png");
    }
}
