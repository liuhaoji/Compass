package com.lenovo.compass.compass.bean.dao;

import android.content.Context;


import com.lenovo.compass.compass.CompassApplication;
import com.lenovo.compass.compass.utils.Constants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

public class OrmPersistManager {
    private static OrmPersistManager instance = new OrmPersistManager();
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static UserInfoDao userInfoDao;
    private static HashMap<Class, AbstractDao> classMap;

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    Constants.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private OrmPersistManager() {
        DaoSession daoSession = getDaoSession(CompassApplication.getInstance());
        initDao(daoSession);
        initClassMap();
    }

    private void initDao(DaoSession daoSession) {
        userInfoDao = daoSession.getUserInfoDao();
        // 添加新的Dao,要再这里添加
    }

    private void initClassMap() {
        classMap = new HashMap<>();
        classMap.put(UserInfo.class, userInfoDao);
        // 添加新的Dao,�?要再这里添加
    }

    public static OrmPersistManager getInstance() {
        return instance;
    }

    /**
     * 保存单条记录
     */
    @SuppressWarnings("unchecked")
    public void saveObject(Object object) {
        classMap.get(object.getClass()).insertOrReplace(object);
    }

    /**
     * 保存多条记录
     */
    @SuppressWarnings("unchecked")
    public void saveObjects(List objects) {
        Object object = null;
        if (objects == null || objects.size() == 0 || (object = objects.get(0)) == null) {
            return;
        }
        classMap.get(objects.get(0).getClass()).insertOrReplaceInTx(objects);
    }

    /**
     * 读取表中的所有数�?
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> readObjects(Class beanClass) {
        return (List<T>) classMap.get(beanClass).loadAll();
    }

    /**
     * 删除表中�?有数�?
     */
    @SuppressWarnings("unchecked")
    public void deleteAllObject(Class beanClass) {
        classMap.get(beanClass).deleteAll();
    }

    /**
     * 删除表中单个元素
     */
    @SuppressWarnings("unchecked")
    public void delete(Object object) {
        classMap.get(object.getClass()).delete(object);
    }

    /**
     * 删除表中单个元素
     */
    @SuppressWarnings("unchecked")
    public void delete(Class beanClass, long key) {
        classMap.get(beanClass).deleteByKey(key);
    }

    /**
     * 查询单个元素
     */
    @SuppressWarnings("unchecked")
    public Object query(Class beanClass, long key) {
        return classMap.get(beanClass).load(key);
    }

    /**
     * 根据单个或多个Where语句，查询对应元�?
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> queryItems(Class<T> beanClass, WhereCondition... conditions) {
        QueryBuilder queryBuilder = classMap.get(beanClass).queryBuilder();
        for (WhereCondition whereCondition : conditions) {
            queryBuilder.where(whereCondition);
        }
        return (List<T>) queryBuilder.list();
    }


    public void changeAccount() {
        Iterator iterator = classMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Class, AbstractDao> entry = (Map.Entry<Class, AbstractDao>) iterator.next();
            Class key = entry.getKey();
            AbstractDao val = entry.getValue();
            val.deleteAll();
        }
    }
}
