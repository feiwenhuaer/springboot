package ocean.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author xieyi
 */
@Slf4j
@Configuration
public class MyBatisConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Value("${spring.datasource.maxActive}")
    private int active;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.initialSize}")
    private int initSize;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${mybatis.mapperLocations}")
    private String mapperLocation;

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(jdbcDriverClassName);
        datasource.setUrl(jdbcUrl);
        datasource.setUsername(jdbcUsername);
        datasource.setPassword(jdbcPassword);
        datasource.setMaxActive(active);
        datasource.setMinIdle(minIdle);
        datasource.setInitialSize(initSize);
        datasource.setMaxWait(maxWait);
        datasource.setValidationQuery(validationQuery);
        return datasource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory( DataSource dataSource) {
       log.debug(dataSource.getClass().toString());
        //解决myBatis下 不能嵌套jar文件的问题
        VFS.addImplClass(SpringBootVfs.class);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try {
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
            Resource mybatisConfigXml = resolver.getResource("classpath:mybatis-config.xml");
            sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);

            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}