//package com.wondertek.baiying.util;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wonertek.baiying.visualize.common.ResponseObject;
//import com.wonertek.baiying.visualize.common.ResponseStatus;
//import com.wonertek.baiying.visualize.common.VisualizeConstants;
//
//import sun.misc.BASE64Decoder;
//
//@RestController
//@RequestMapping("/upload")
//public class UploadImgController {
//	private static Logger log = LoggerFactory.getLogger(UploadImgController.class);
//
//	@RequestMapping("/uploadImg")
//	@ResponseBody
//	public ResponseObject<String> uploadImg(@RequestBody String base64 , String name){
//		String rootPath = VisualizeConstants.FILE_STORE_PATH;
//		String fileName = "img" + new Date().getTime() + name.substring(name.indexOf("."));
//		String dstPath = rootPath + "img/" + fileName;
//		boolean b = GenerateImage(base64, dstPath);
//		if(b){
//			return ResponseObject.instance(ResponseStatus.SUCCESS,"上传图片成功",VisualizeConstants.SERVER_ACCESS_PATH + "img/" + fileName );
//		}else{
//			return ResponseObject.instance(ResponseStatus.FAILURE,"上传图片失败",null);
//		}
//	}
//
//	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
//        if (imgStr == null) // 图像数据为空
//            return false;
//        BASE64Decoder decoder = new BASE64Decoder();
//        String[] arr = imgStr.split("base64,");
//        try {
//            byte[] buffer = decoder.decodeBuffer(arr[1]);
//            OutputStream os = new FileOutputStream(imgFilePath);
//            os.write(buffer);
//            os.flush();
//            os.close();
//            return true;
//        } catch (Exception e) {
//        	log.error(e.getMessage());
//            return false;
//        }
//    }
//
//}
