package order_861385.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ImgUpload {

//    private static String ioPath= ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static String saveImg(MultipartFile multipartFile,String ioPath) {
        File file = new File(ioPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + ".jpg";
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ioPath + File.separator + fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();
            return fileName;
        }catch (Exception e){
            return null;
        }
    }
    public static boolean delImg(String path,String fileName){
        String filePath = path+File.separator+fileName;
        File file = new File(filePath);
        try {
            boolean flag = file.delete(); // 删除照片
            if (flag) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
