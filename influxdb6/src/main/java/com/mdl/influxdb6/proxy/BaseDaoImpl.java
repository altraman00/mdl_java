package com.mdl.influxdb6.proxy;

import com.mdl.influxdb6.config.ApplicationContextHelper;
import com.mdl.influxdb6.config.TicInfluxdbMapper;
import com.mdl.influxdb6.measurements.H2oFeetMeasurement;
import com.mdl.influxdb6.measurements.HealthSessionMeasurement;
import com.mdl.influxdb6.measurements.TicBaseMeasurement;
import java.util.List;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:22
 * ----------------- ----------------- -----------------
 */
@Component
public class BaseDaoImpl<T extends TicBaseMeasurement> implements IBaseDao {

  private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

  @Override
  public void save(TicBaseMeasurement ticBaseMeasurement) {
    System.out.println("=========DB save()执行==========");
  }

  @Override
  public void get(Long id) {
    System.out.println("=========DB get()执行==========");
  }

  @Override
  public void delete(Long id) {
    System.out.println("=========DB delete()执行===============");
  }

  /**
   * 自定义注解的通用操作方法
   */
  public List<?> dealHqlFunc(BaseDaoObj baseDaoObj)
      throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    System.out.println("=========DB doQuery()执行统一的deal方法==========");

    String hql = baseDaoObj.getHql();
    String database = baseDaoObj.getDatabase();
    String measurement = baseDaoObj.getMeasurement();
    String className = baseDaoObj.getClassName();
    Class measurementClass = baseDaoObj.getMeasurementClass();
    String measurementClassName = baseDaoObj.getMeasurementClassName();
    String classPath = baseDaoObj.getClassPath();
    T bean = (T) Class.forName(classPath).newInstance();
    Query query = new Query(hql, database);
    TicInfluxdbMapper ticInfluxdbMapper = ApplicationContextHelper.popBean(TicInfluxdbMapper.class);
    logger.info("[BaseDaoImpl]ticInfluxdbMapper:{}", ticInfluxdbMapper);
    List<?> resList = ticInfluxdbMapper.query(query, bean.getClass());
    logger.info("[BaseDaoImpl]resList:{}", resList);
    return resList;
  }


}
