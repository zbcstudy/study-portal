package com.wondertek.baiying.freemarker;

import com.itextpdf.text.pdf.codec.Base64;
import com.wondertek.baiying.vo.User;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据模板生成对应的xml
 * Created by wd on 2018/5/17.
 */
public class TemplateParseUtils {

    /**
     * 解析模板生成Excel
     * @param templateDir 模板路径
     * @param templateName  模板名称
     * @param excelPath     存储路径
     * @param data          数据
     */
    public static void parseToXml(String templateDir, String templateName, String excelPath, Map<String,Object> data) throws IOException {
        //初始化
        Configuration configuration = new Configuration();
        //设置默认编码格式
        configuration.setDefaultEncoding("UTF-8");

        configuration.setNumberFormat("0.00");

        //设置模板存储路径
        configuration.setDirectoryForTemplateLoading(new File(templateDir));

        configuration.setObjectWrapper(new DefaultObjectWrapper());

        //根据设置的模板路径和模板名称加载模板
        Template template = configuration.getTemplate(templateName);

        OutputStreamWriter writer = null;

        try {
            //填充数据
            writer = new OutputStreamWriter(new FileOutputStream(excelPath));
            template.process(data,writer);
            writer.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    @Test
    public void xmlTest() {
        List<User> userList = new ArrayList<>();
        //生成数据
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setUserName("富贵--" + i);
            user.setRealName("张无忌--" + i);
            user.setPassWord("123456");
            user.setAddr("明教光明顶");
            user.setAge("26");
            userList.add(user);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("userList", userList);
        try {
            parseToXml("D:\\test","students.ftl","D:\\test\\test.xml",data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
