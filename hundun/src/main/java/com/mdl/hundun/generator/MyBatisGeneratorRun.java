package com.mdl.hundun.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project : zhaopin-cloud
 * @Package Name : com.sunlands.zhaopin.console.fn.generator
 * @Description : 运行此方法生成mybatis代码，生成代码自动放入对应目录
 * @Author : xiekun
 * @Create Date : 2020年01月04日 20:33
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class MyBatisGeneratorRun {

    /**
     * 数据库类型
     **/
    public final static DbType dbType = DbType.MYSQL;

    /**
     * 指定生成的表名
     **/
    public final static String[] tableNames = new String[]{"sys_user"};


    /**
     * 数据库连结信息
     **/
//    public final static String dbUrl = "jdbc:mysql://111.230.70.125:3389/db_feo_zhaopin_20190525?useUnicode=true&useSSL=false&characterEncoding=utf8";
    public final static String dbUrl = "jdbc:mysql://127.0.0.1:3306/alkb-order?useUnicode=true&useSSL=false&characterEncoding=utf8";
    public final static String driver = "com.mysql.jdbc.Driver";
    public final static String userName = "root";
    public final static String password = "123456";

    /**
     * 项目(模块)名
     **/
    public final static String projectName = "";
    /**
     * 指定包名
     **/
    public final static String packageName = "com.mdl.hundun";

    /**
     * xml 文件生成路径
     **/
    public final static String xmlPath = "/src/main/resources/mapper";
    /**
     * java 文件生成路径
     **/
    public final static String javaFilePath = "/src/main/java";


//    //controller基础类
//    public final static String superControllerClass = packageName + ".common.BaseController";
//    //entity基础类
//    public final static String superEntityClass = packageName + ".common.BaseEntity";

    /**
     * 作者名
     **/
    public final static String author = "xiekun";


    public static void main(String[] args) {
        //serviceNameStartWithI：user -> UserService, 设置成true: user -> IUserService
        generateByTables(false, packageName, tableNames);
    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param packageName           包名
     * @param tableNames            表名
     * @author Terry
     */
    public static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自定义配置xml的生成路径
        InjectionConfig injectionConfig = XmlInjectionConfig(projectName, xmlPath);
        //自定义模板(设置xml为null，就不会在src/main/java/mapper下面生成xml)
        TemplateConfig templateConfig = getTemplateConfig();

        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig, injectionConfig,templateConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     * @author Terry
     */
    public static void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig, InjectionConfig injectionConfig,TemplateConfig templateConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setTemplate(templateConfig)
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @param packageName 模块名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    public static PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setXml("mapper.xml")
                .setMapper("mapper")
                .setController("controller")
                .setEntity("entity");
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Terry
     */
    public static GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                // XML columList
                .setBaseColumnList(true)
                // XML ResultMap
                .setBaseResultMap(true)
                // 是否覆盖同名文件，默认是false
                .setFileOverride(true)
                // XML 二级缓存
                .setEnableCache(false)
                //不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                .setOpen(false)
                .setSwagger2(true)
                .setEntityName("%sEntity")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                //作者
                .setAuthor(author)
                //设置输出路径
                .setOutputDir(getOutputDir(projectName, javaFilePath));

        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     * @param projectName 项目名
     * @return 项目路径
     * @author Terry
     */
    public static String getOutputDir(String projectName, String filePath) {
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        int index = path.indexOf(projectName);
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + projectName + filePath;
        System.out.println("path--->"+path);
        return path;
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    public static StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                //从数据库表转java时，下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                //数据库字段生成java字段时，取消驼峰的映射关系，在字段上指定java字段对应的表字段名
                .setColumnNaming(NamingStrategy.no_change)
//              .setColumnNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames)
//                 //此处可以修改为您的表前缀
//                .setTablePrefix(new String[] { "tlog_", "tsys_" })
//                //公共父类
//                .setSuperControllerClass(superControllerClass)
//                .setSuperEntityClass(superEntityClass)
//                // 写于父类中的公共字段
//                .setSuperEntityColumns("id")
                //使用lombok
                .setEntityLombokModel(true)
                //驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                //rest风格
                .setRestControllerStyle(true);
    }

    /**
     * 自定义模板
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        //设置xml为null，就不会在src/main/java/mapper下面生成xml
        templateConfig.setXml(null);
        return templateConfig;
    }


    /**
     * 自定义配置xml的生成路径
     *
     * @return
     */
    public static InjectionConfig XmlInjectionConfig(String projectNameStr, String xmlPathStr) {

        String mapperXmlPath = getOutputDir(projectNameStr, xmlPathStr);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String path = mapperXmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                System.out.println("path===>"+path);
                return path;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        return cfg;
    }


    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    public static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName(driver);
    }

    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     * @author Terry
     */
    @SuppressWarnings("unused")
    public void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
