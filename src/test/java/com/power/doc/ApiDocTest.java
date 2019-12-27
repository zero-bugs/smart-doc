package com.power.doc;

import com.power.common.util.DateTimeUtil;
import com.power.common.util.StringUtil;
import com.power.doc.enums.OrderEnum;
import com.power.doc.model.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.SourceCodePath;

import org.junit.Test;

/**
 * Description:
 * ApiDoc测试
 *
 * @author yu 2018/06/11.
 */
public class ApiDocTest {

    /**
     * 包括设置请求头，缺失注释的字段批量在文档生成期使用定义好的注释
     */
    @Test
    public void testBuilderControllersApi() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.contains("aa");
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:8080");
        //config.setStrict(true);

        config.setAllInOne(false);
        config.setOutPath("d:\\md2");
        config.setMd5EncryptedHtmlName(true);
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePaths(
                SourceCodePath.path().setDesc("本项目代码").setPath("src/test/java")
                //SourcePath.path().setPath("F:\\Personal\\project\\smart\\src\\main\\java")
                //SourcePath.path().setDesc("加载项目外代码").setPath("E:\\ApplicationPower\\ApplicationPower\\Common-util\\src\\main\\java")
        );
        config.setDataDictionaries(
                ApiDataDictionary.dict().setTitle("订单字典").setEnumClass(OrderEnum.class).setCodeField("code").setDescField("desc")
        );
        //设置请求头，如果没有请求头，可以不用设置
     /*   config.setRequestHeaders(
                ApiReqHeader.header().setName("access_token").setType("string").setDesc("Basic auth credentials"),
                ApiReqHeader.header().setName("user_uuid").setType("string").setDesc("User Uuid key")
        );*/
        //对于外部jar的类，api-doc目前无法自动获取注释，
        //如果有这种场景，则自己添加字段和注释，api-doc后期遇到同名字段则直接给相应字段加注释
  /*      config.setCustomResponseFields(
//                CustomRespField.field().setName("success").setDesc("成功返回true,失败返回false"),
//                CustomRespField.field().setName("message").setDesc("接口响应信息"),
//                CustomRespField.field().setName("data").setDesc("接口响应数据"),
                CustomRespField.field().setName("code").setValue("00000")
                //.setDesc("响应代码")
        );*/
        //非必须只有当setAllInOne设置为true时文档变更记录才生效，https://gitee.com/sunyurepository/ApplicationPower/issues/IPS4O
        config.setRevisionLogs(
                RevisionLog.getLog().setRevisionTime("2018/12/15").setAuthor("chen").setRemarks("测试").setStatus("创建").setVersion("V1.0"),
                RevisionLog.getLog().setRevisionTime("2018/12/16").setAuthor("chen2").setRemarks("测试2").setStatus("修改").setVersion("V2.0")
        );



        long start = System.currentTimeMillis();
//        ApiDocBuilder.builderControllersApi(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
    
    
    
    private static final String SERVER_URL = "";
    private static final String MAVEN_CODE_PATH = "";
    private static final String MAVEN_DOC_MARK_PATH = "";
    private static final String MAVEN_DOC_HTML_PATH = "";

    @Test
    public void testBuilderControllersApiMavenMarkDown() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl(SERVER_URL);
        config.setStrict(false);
        config.setOutPath(MAVEN_DOC_MARK_PATH);
        config.setPackageFilters("com.test");
        config.setSrcCodePath(MAVEN_CODE_PATH);
        //不指定SourceCodePaths默认加载代码为项目src/main/java下的,如果项目的某一些实体来自外部代码可以一起加载
        config.setSourceCodePaths(
            // SourceCodePath.path().setDesc("本项目代码").setPath("src/main/java")
            //  SourceCodePath.path().setPath("E:\\Test\\Mybatis-PageHelper-master\\src\\main\\java"),
            SourceCodePath.path().setDesc("加载项目外代码").setPath(config.getSrcCodePath())
        );

        long start = System.currentTimeMillis();
        ApiDocBuilder.builderControllersApi(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

    @Test
    public void testBuilderControllersApiMavenHtml() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl(SERVER_URL);
        config.setStrict(false);
        config.setOutPath(MAVEN_DOC_HTML_PATH);
        config.setPackageFilters("com.test");

        //设置用md5加密html文件名,不设置为true，html的文件名将直接为controller的名称
        config.setMd5EncryptedHtmlName(false);
        config.setSrcCodePath(MAVEN_CODE_PATH);

        //不指定SourcePaths默认加载代码为项目src/main/java下的,如果项目的某一些实体来自外部代码可以一起加载
        config.setSourceCodePaths(
            // SourceCodePath.path().setDesc("本项目代码").setPath("src/main/java")
            //  SourceCodePath.path().setPath("E:\\Test\\Mybatis-PageHelper-master\\src\\main\\java"),
            SourceCodePath.path().setDesc("加载项目外代码").setPath(config.getSrcCodePath())
        );

        long start = System.currentTimeMillis();
        HtmlApiDocBuilder.builderControllersApi(config);//此处使用HtmlApiDocBuilder，ApiDocBuilder提供markdown能力
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

}
