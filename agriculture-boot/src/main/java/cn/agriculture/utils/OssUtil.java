/**
 * OssUtil.java
 * com.prereadweb.utils
 * Copyright (c) 2019, 北京链天下科技有限公司版权所有.
 */
package cn.agriculture.utils;
 
import cn.agriculture.entity.Product;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;
 
/**
 * @Description: 阿里云OSS服务器工具类
 * @author: Yangxf
 * @date: 2019/4/18 16:26
 */
@Component
public class OssUtil {
 
  protected static final Logger log = LoggerFactory.getLogger(OssUtil.class);
 
//  @Value("${aliyun.endpoint}")
//  private static String endpoint;
//  @Value("${aliyun.accessKeyId}")
//  private String accessKeyId;
//  @Value("${aliyun.accessKeySecret}")
//  private String accessKeySecret;
//  @Value("${aliyun.bucketName}")
//  private String bucketName;

  /**
   *   bucketName: "8042965"
   *   accessKeyId: "LTAI4G37PEdGSzSS2B3vh8Ue"
   *   accessKeySecret: "AZum1Rm5qvyh7DX1bPDaZpqtKWmnYl"
   *   #OSS对应的区域
   *   endpoint: http://oss-cn-beijing.aliyuncs.com
   */

  private String endpoint="http://oss-cn-beijing.aliyuncs.com";
  private String accessKeyId="LTAI4G37PEdGSzSS2B3vh8Ue";
  private String accessKeySecret="AZum1Rm5qvyh7DX1bPDaZpqtKWmnYl";
  private String bucketName="8042965";

  //文件存储目录
  private String filedir = "fruits/";



  /**
   *
   * 上传图片
   * @param file
   * @return
   */
  public String uploadImg2Oss(MultipartFile file) {
    if (file.getSize() > 1024 * 1024 *20) {
      return "图片太大";//RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_TOO_MAX);
    }
    String originalFilename = file.getOriginalFilename();
    String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
    Random random = new Random();
    String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
    try {
      InputStream inputStream = file.getInputStream();
      this.uploadFile2OSS(inputStream, name);
      return name;//RestResultGenerator.createSuccessResult(name);
    } catch (Exception e) {
      return "上传失败";//RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_UPLOAD);
    }
  }
 
  /**
   * 上传图片获取fileUrl
   * @param instream
   * @param fileName
   * @return
   */
  private String uploadFile2OSS(InputStream instream, String fileName) {
    String ret = "";
    try {
      //创建上传Object的Metadata
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(instream.available());
      objectMetadata.setCacheControl("no-cache");
      objectMetadata.setHeader("Pragma", "no-cache");
      objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
      objectMetadata.setContentDisposition("inline;filename=" + fileName);
      //上传文件
 
      OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
      ret = putResult.getETag();
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    } finally {
      try {
        if (instream != null) {
          instream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return ret;
  }
 
  public static String getcontentType(String FilenameExtension) {
    if (FilenameExtension.equalsIgnoreCase(".bmp")) {
      return "image/bmp";
    }
    if (FilenameExtension.equalsIgnoreCase(".gif")) {
      return "image/gif";
    }
    if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
        FilenameExtension.equalsIgnoreCase(".jpg") ||
        FilenameExtension.equalsIgnoreCase(".png")) {
      return "image/jpeg";
    }
    return "image/jpeg";
  }
 
  /**
   * 获取图片路径
   * @param fileUrl
   * @return
   */
  public String getImgUrl(String fileUrl) {
    if (!StringUtils.isEmpty(fileUrl)) {
      String[] split = fileUrl.split("/");
      String url = this.getUrl(this.filedir + split[split.length - 1]);
      return url;
    }
    return null;
  }
 
  /**
   * 获得url链接
   *
   * @param key
   * @return
   */
  public String getUrl(String key) {
    // 设置URL过期时间为10年 3600l* 1000*24*365*10
    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
    // 生成URL
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
    if (url != null) {
      return url.toString();
    }
    return null;
  }
 
 
  /**
   * 多图片上传
   * @param fileList
   * @return
   */
  public String checkList( List<MultipartFile> fileList) {
    String fileUrl = "";
    String str = "";
    String photoUrl = "";
    for(int i = 0;i< fileList.size();i++){
      fileUrl = uploadImg2Oss(fileList.get(i));
      str = getImgUrl(fileUrl);
      if(i == 0){
        photoUrl = str;
      }else {
        photoUrl += "," + str;
      }
    }
    return photoUrl.trim();
  }
 
  /**
   * 单个图片上传
   * @param file
   * @return
   */
  public String checkImage(MultipartFile file){
    String fileUrl = uploadImg2Oss(file);
    String imgUrl = getImgUrl(fileUrl);
    return imgUrl.trim();
  }
}