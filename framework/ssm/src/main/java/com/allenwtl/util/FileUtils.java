package com.allenwtl.util;

import cn.gfurox.common.MessageException;
import cn.gfurox.utils.dataformat.StringUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static String FILE_UPLOAD_PATH = SystemConfig.system_uploadfile;


    /**
     * 得到文件后缀名
     *
     * @return
     */
    public static String getFileExt(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            throw new MessageException("文件名不能为空");
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    public static String uploadFile(FileItem fileItem, String relativeDirectory) {
        String filename = UUIDUtils.getUuidStr() + "." + getFileExt(fileItem.getName());
        filename = filename.substring(filename.lastIndexOf("\\") + 1);
        String relativeFilePath = relativeDirectory + "/" + filename;
        logger.info("relativeFilePath:{}", relativeFilePath);
        File file = new File(FILE_UPLOAD_PATH + relativeFilePath);
        try {
            fileItem.write(file);
        } catch (Exception e) {
            logger.error("保存图片失败:{}", e.getMessage());
            throw new MessageException(e.getMessage());
        }

        return relativeFilePath;
    }

    /**
     * 获取相对的路径
     *
     * @param path e.g logo /userlogo
     *             e.g topic /topic
     * @return
     */
    public static String getRelativeDirectory(String filePathPrefix, String path) {
        StringBuffer sb = new StringBuffer(path);
        sb.append("/").append(sdf.format(new Date()));
        File file = new File(filePathPrefix + sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return sb.toString();
    }

    /**
     * 根据固定的宽度和高度 获取缩略图
     *
     * @param imageStyle                 w-h 50-50
     *
     * @param imageStyle
     * @param filePathPrefix
     * @param relativeFilePath
     * @param relativeDirectory 缩略图存放的相对路径
     * @return
     */
    public static String getThumbnailsWidthAndHeight(String imageStyle, String filePathPrefix , String relativeFilePath, String relativeDirectory) {
        logger.info("imageStyle:[{}], filePathPrefix:[{}],relativeFilePath:[{}], relativeDirectory:[{}]", new Object[]{imageStyle, filePathPrefix, relativeFilePath, relativeDirectory});
        String oldImageAbsolutelyFilePath = filePathPrefix + relativeFilePath ;
        String[] arrays = imageStyle.split("-");
        Integer width = Integer.parseInt(arrays[0]);
        Integer height = Integer.parseInt(arrays[1]);
        String fileName = UUIDUtils.getUuidStr();
        String resultFilePath = "";
        try {
            resultFilePath = relativeDirectory + "/" + fileName + "." + getFileExt(oldImageAbsolutelyFilePath);

            File file = new File(oldImageAbsolutelyFilePath);
            Thumbnails.Builder<File> builderBuf = Thumbnails.of(file);
            BufferedImage image = ImageIO.read(file);
            if (image.getHeight() > image.getWidth()) {
                builderBuf.width(width);
            } else {
                builderBuf.height(height);
            }

            builderBuf.outputQuality(0.9); //参数是浮点数，0-1之间
            builderBuf.keepAspectRatio(true);  //默认为true，如果要剪裁到特定的比例，设为false即可
            image = builderBuf.asBufferedImage();

            Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(image);
            builder.sourceRegion(Positions.CENTER, width, height);
            builder.size(width, height);
            builder.toFile(filePathPrefix + resultFilePath);
        } catch (IOException e) {
            logger.error("生成缩略图失败:{}", e);
        }
        return resultFilePath;
    }

    /**
     * 只按等比例缩小
     * @param imageStyle
     * @param filePathPrefix
     * @param relativeFilePath
     * @param relativeDirectory
     * @return
     */
    public static String getThumbnailsAspectRatio(String imageStyle, String filePathPrefix , String relativeFilePath, String relativeDirectory) {
        String oldImageAbsolutelyFilePath = filePathPrefix + relativeFilePath ;
        String[] arrays = imageStyle.split("-");
        Integer width = Integer.parseInt(arrays[0]);
        Integer height = Integer.parseInt(arrays[1]);
        String fileName = UUIDUtils.getUuidStr();
        String resultFilePath = "";
        try {
            resultFilePath = relativeDirectory + "/" + fileName + "." + getFileExt(oldImageAbsolutelyFilePath);

            File file = new File(oldImageAbsolutelyFilePath);
            Thumbnails.Builder<File> builderBuf = Thumbnails.of(file);
            BufferedImage image = ImageIO.read(file);
            if (image.getHeight() > image.getWidth()) {
                builderBuf.width(width);
            } else {
                builderBuf.height(height);
            }


            builderBuf.outputQuality(0.8); //参数是浮点数，0-1之间
            builderBuf.keepAspectRatio(true);  //默认为true，如果要剪裁到特定的比例，设为false即可

            builderBuf.toFile(filePathPrefix + resultFilePath);
        } catch (IOException e) {
            logger.error("生成缩略图失败:{}", e);
        }
        return resultFilePath;
    }



    public static String writeFileToLocal(String filePathPrefix, byte[] fileData, String fileType, String fileExt) {
        String path = "/image";
        if (!fileType.equals("img") && !fileType.equalsIgnoreCase("radio")) {
            return null;
        }

        if (fileType.equalsIgnoreCase("radio")) {
            path = "/radio";
        }

        String relativePath = getRelativeDirectory(filePathPrefix, path);
        String fileName = "/" + UUIDUtils.getUuidStr() + "." + fileExt;

        File file = new File(filePathPrefix + relativePath + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(fileData);
            fos.close();
            return relativePath + fileName;
        } catch (FileNotFoundException e) {
            logger.error("{}", e);
        } catch (IOException e) {
            logger.error("{}", e);
        }
        return null;
    }


    public static Map<String, Object> getFileProperties(String absolutelyFilePath) {
        Map<String, Object> fileProperties = new HashMap<>();
        File file = new File(absolutelyFilePath);
        try {
            BufferedImage image = ImageIO.read(file);
            fileProperties.put("width", image.getWidth());
            fileProperties.put("height", image.getHeight());
            fileProperties.put("fileSize", file.length());
            fileProperties.put("fileName", file.getName());
            fileProperties.put("ext", getFileExt(file.getName()));
            return fileProperties;
        } catch (IOException e) {
            logger.error("{}", e);
        }

        return null;
    }

    public static long getFileSize(String absolutelyFilePath) {
        return new File(absolutelyFilePath).length();
    }


    /**
     * 传入 http://www.baidu.com/pathname
     * 返回 pathname
     * @param httpPathUrl
     * @return
     */
    public static String getPathName(String httpPathUrl) {
        try {
            if(httpPathUrl.startsWith("http")){
                URL urlTemp = new URL(httpPathUrl);
                return urlTemp.getPath() ;
            }
            return httpPathUrl ;
        } catch (MalformedURLException e) {
            logger.error("{}", e);
        }

        return null;
    }

    /**
     * 获取上传的原来的名称
     * 如果原来的名称不存在  就要取上传路径中的名称
     * @param fileItem
     * @param originalFilePath
     * @return
     */
    public static String getFileName(FileItem fileItem, String originalFilePath){
        String fileName = fileItem.getName();
        if( StringUtils.isEmpty(fileName) ){
            fileName = originalFilePath.substring(originalFilePath.lastIndexOf("/") + 1);
        }
        fileName = fileName.lastIndexOf("/") > -1 ?  fileName.substring(fileName.lastIndexOf("/") + 1): fileName;
        return fileName ;
    }

    /**
     * 获取文件的名称
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath){
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }


    public static void main(String[] args) throws IOException {
//        Integer width = 357;
//        Integer height = 357 ;
//        String filePathName = "C:\\Users\\Administrator\\Desktop\\images\\f5cd3bfdde4d40af888fad2cbd0de987.png";
//        String resultFilePath = "C:\\Users\\Administrator\\Desktop\\images\\thum\\123.png";
//        File file = new File(filePathName);
//        Thumbnails.Builder<File> builderBuf = Thumbnails.of(file);
//
//        BufferedImage image = ImageIO.read(file);
//        if (image.getHeight() > image.getWidth()) {
//            builderBuf.width(width);
//        } else {
//            builderBuf.height(height);
//        }
//
//        builderBuf.outputQuality(0.9); //参数是浮点数，0-1之间
//        builderBuf.keepAspectRatio(true);  //默认为true，如果要剪裁到特定的比例，设为false即可
//        builderBuf.toFile(resultFilePath);

//        String url ="https://assetcdn.500px.org/assets/home/home_cover-22d4c02977bbe00636e22f9aa653bd84.jpg";
//
//
//        byte[] imagesArray = HttpUtils.downFile(url);
//
//        writeFileToLocal("D:\\usr\\", imagesArray, "img", "jpg");


        String filePathName = "C:\\Users\\Administrator\\Desktop\\老师\\IMG_20151122_093415.jpg";

        String relativeDirectory = getRelativeDirectory( "C:\\Users\\Administrator\\Desktop\\老师\\", "th");
        getThumbnailsAspectRatio("500-500", "C:\\Users\\Administrator\\Desktop\\老师\\", "IMG_20151122_093415.jpg", relativeDirectory);



    }

}
