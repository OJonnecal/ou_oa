//package com.jjou.demo;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import org.junit.Test;
//
///**
// * 代码生成器
// *
// * @Author
// * @Date
// */
//public class CodeGenerator {
//    public static void main(String[] args) {
//
//    }
//
//    @Test
//    public void run(){
//
//        //代码生成器
//        AutoGenerator autoGenerator = new AutoGenerator();
//
//        //1.数据源配置
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDbType(DbType.MYSQL);//数据库枚举类型
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("oujiajun123");
//        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/oa?serverTimezone=GMT%2B8");
//        autoGenerator.setDataSource(dataSourceConfig); //数据源配置进自动生成器里
//
//        //2.全局配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setOpen(false); //代码生成后是否自动打开 (代码生成完成后以文件夹形式弹出来)
//        globalConfig.setOutputDir("D:\\workplace\\JavaProject\\oa" + "/src/main/java");//设置代码输出路径（当前系统项目路径，源代码Java里面）
//        globalConfig.setAuthor("jjou"); //作者名
//        globalConfig.setServiceName("%sService"); //%s代表表名前缀，后面补上Service
//        autoGenerator.setGlobalConfig(globalConfig);
//
//        //3.包配置
//        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setParent("com.jjou.oa");//父包名
//        packageConfig.setEntity("entity");//实体类包名
//        packageConfig.setMapper("mapper");//mapper包名
//        packageConfig.setController("controller");//控制层包名
//        packageConfig.setService("service");//业务层接口包名
//        packageConfig.setServiceImpl("service.impl");//业务层实现类包名
//        autoGenerator.setPackageInfo(packageConfig);
//
//        //策略配置
//        StrategyConfig strategyConfig = new StrategyConfig();
////        strategyConfig.setInclude("user");
//        strategyConfig.setEntityLombokModel(true);//是否给实体类添加 Lombok 的注解
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名映射类名，下划线转驼峰  如：表名 user_address 下划线转驼峰实体类为 userAddress
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//表字段映射类属性，下划线转驼峰 如：表字段 user_id 下划线转驼峰实体类属性 userId
//
//        //4.表自动填充 （mp自动填充，在添加数据的时候，创建时间和修改时间，是在灭）  ===此处不需要可省略===
////        List<TableFill> list  = new ArrayList<>();
////        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
////        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
////        list.add(create_time);
////        list.add(update_time);
////        strategyConfig.setTableFillList(list);//设置表自动填充
//        autoGenerator.setStrategy(strategyConfig);
//
//        // 5. 配置模板引擎
//        // 默认采用velocity模板
//        //autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
//
//        //6.执行生成代码
//        autoGenerator.execute();
//        System.err.println("代码生成完成");
//    }
//}