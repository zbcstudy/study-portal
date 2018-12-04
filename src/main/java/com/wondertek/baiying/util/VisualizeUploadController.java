//package com.wondertek.baiying.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.wondertek.baiying.admin.config.AppConstants;
//import com.wondertek.baiying.admin.config.ApplicationProperties;
//import com.wondertek.baiying.admin.config.VisualizeConstants;
//import com.wondertek.baiying.admin.service.VisualizeService;
//@RestController
//public class VisualizeUploadController {
//
//    private static final long serialVersionUID = 1L;
//    public static final String DIRROOT = "temp";
//    public static final String FILE_PATTERN = "%s%s" ;
//    static final AtomicInteger AI = new AtomicInteger();
//    public static final String SDFTD = "yyyyMMdd";
//    public static final String SDF = "yyyyMMddHHmmss";//new SimpleDateFormat(SDF);
//    public final static Log log = LogFactory.getLog(VisualizeUploadController.class);
//    public Map<String, Object> resultMap = new HashMap<String, Object> ();
//    @Autowired
//    private ApplicationProperties applicationProperties;
//
//    @Autowired
//    private VisualizeService VisualizeService;
//
//    /**
//     * 上传
//     *
//     * @param files
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/visualizeUpload")
//    public Map<String, Object> upload(@RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request)  {
//        List fileList = new ArrayList ();
//        resultMap.put("success", false);
//        try {
//            for (MultipartFile file : files) {
//                String fName = file.getOriginalFilename();
//                System.out.println(fName);
//                StringBuffer tempStr = new StringBuffer();
//                tempStr.append("name:"+ fName);
//                tempStr.append(",fileType:" + fName.substring(fName.lastIndexOf(".")+1));
//                //TODO
//                //String webPath = request.getServletContext().getRealPath("depository");
//                String webPath = VisualizeService.getVisualizePath();
//                StringBuilder basicFilePath = new StringBuilder(webPath);
//                if (basicFilePath.lastIndexOf(File.separator) != basicFilePath.length() - 1) {
//                    basicFilePath.append(File.separator);
//                }
//                String td = new SimpleDateFormat(SDFTD).format(new Date());
//                String tdh = new SimpleDateFormat(SDF).format(new Date());
//                String fileName = String.format(FILE_PATTERN, tdh,AI.getAndIncrement()+""+fName);
//                //TODO
//                String rpath = "img"+ File.separator+"temp" + File.separator + td + File.separator + fileName;
//
//                basicFilePath.append(rpath);
//
//                File basicDir = new File(basicFilePath.toString());
//
//                if (!basicDir.getParentFile().exists()) {
//                    basicDir.getParentFile().mkdirs();
//                }
//
//                String filePath = basicFilePath.toString();
//                log.info("Upload file path: "+filePath.replaceAll("\\\\", "/"));
//
//                File dest = new File(filePath);
////                file.transferTo(dest);
//                FileCopyUtils.copy(file.getBytes(),dest);
//
//                tempStr.append(",tempPath:"+filePath.replaceAll("\\\\", "/"));
//                fileList.add(tempStr.toString());
//            }
//        resultMap.put("success", true);
//        resultMap.put("files", fileList);
//        return resultMap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            resultMap.put("success", false);
//			resultMap.put("message", e.getMessage());
//			log.error(e.getMessage());
//            return resultMap;
//        }
//    }
//
//}
