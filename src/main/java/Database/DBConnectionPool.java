package Database;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnectionPool {

    private static DBConnectionPool dbConnectionPool = null;
    public  DataSource dataSource;

    /*singleton object*/
    private DBConnectionPool(){

        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/EduClickDB");
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("root");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(1000);
        p.setInitialSize(100);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);


    }

    public static DBConnectionPool getInstance(){

        if( dbConnectionPool == null ){
            dbConnectionPool = new DBConnectionPool();
        }
        return dbConnectionPool;
    }

}
