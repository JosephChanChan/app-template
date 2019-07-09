package com.byb.mybatisplus;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.byb.mybatisplus.config.MybaitsPackageConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author: chenzeting
 * Date:     2018/12/18
 * Description:
 */
public class MysqlGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = MysqlGenerator.class.getClassLoader().getResourceAsStream("config/config.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/mybatis-plus-generator/src/main/java");
        gc.setAuthor(properties.getProperty("author"));
        gc.setDateType(DateType.SQL_PACK);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setBaseResultMap(true);
        String moduleName = properties.getProperty("moduleName");
        String tableName = properties.getProperty("tableName");
        int i = tableName.indexOf("_");
        if (i > 0) {
            String[] split = tableName.split("_");
            String temp = "";
            for (int k = 0; k < split.length; k++) {
                temp += (split[k].substring(0, 1).toUpperCase() + split[k].substring(1));
            }
            gc.setServiceName(temp + "Service");
        } else {
            gc.setServiceName(tableName.substring(0, 1).toUpperCase() + tableName.substring(1) + "Service");
        }
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(properties.getProperty("db.url"));
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(properties.getProperty("db.username"));
        dsc.setPassword(properties.getProperty("db.password"));
        mpg.setDataSource(dsc);


        // 包配置
        MybaitsPackageConfig pc = new MybaitsPackageConfig();
//        pc.setModuleName(moduleName);
        pc.setParent("com.byb");
        pc.setService("service." + moduleName);
        pc.setServiceImpl("service.impl." + moduleName);
        pc.setController("sznews." + moduleName);
        pc.setMapper("dao." + moduleName);
        pc.setEntity("model.entity." + moduleName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("FormPackage", pc.getParent()+".model.form."+moduleName);
                map.put("VoPackage",pc.getParent()+".model.vo."+moduleName);
                map.put("PagePackage",pc.getParent()+".model.page."+moduleName);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/mapper/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-dao/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig("/mapper/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-service-interface/src/main/java/com/byb/service/" + moduleName
                        + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-service-impl/src/main/java/com/byb/service/impl/" + moduleName
                        + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/page.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-model/src/main/java/com/byb/model/page/" + moduleName
                        + "/" + tableInfo.getEntityName() + "PageForm" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-controller/src/main/java/com/byb/sznews/api/" + moduleName
                        + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/form.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-model/src/main/java/com/byb/model/form/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Form" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-dao/src/main/java/com/byb/dao/" + moduleName
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/mapper/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-model/src/main/java/com/byb/model/entity/" + moduleName
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig("/mapper/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/sznews-model/src/main/java/com/byb/model/vo/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        mpg.setTemplate(new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                .setController(null)
                .setEntity(null)
                .setMapper(null)
                .setXml(null)
                .setService(null)
                .setServiceImpl(null)
        );
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.byb.mybatisplus.BaseEntity");
        strategy.setEntityLombokModel(true);
//        strategy.setSuperControllerClass("com.byb.mybatisplus.BaseController");
        strategy.setInclude(tableName);
        strategy.setSuperServiceClass("com.byb.service.BaseService");
        strategy.setSuperServiceImplClass("com.byb.service.impl.BaseServiceImpl");
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        //关流
        in.close();
    }

}