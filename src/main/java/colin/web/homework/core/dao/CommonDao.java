package colin.web.homework.core.dao;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.dao.idao.ICommonDao;
import colin.web.homework.tools.HomeworkLogOperate;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ASUS on 2015/7/11.
 */
@Repository
public class CommonDao extends NamedParameterJdbcDaoSupport implements ICommonDao {

    /**
     * 单一增加数据
     *
     * @param t
     */
    @Override
    public <T> boolean addObjInfo(T t) {
        //声明增加SQL语句
        StringBuilder insertSql = new StringBuilder("insert into ");
        StringBuilder insertSqlVal = new StringBuilder(" values(");
        //获取实体类的表明
        insertSql.append(this.getEntityTableName(t)).append(" (");
        //获取每个字段的对应表字段
        Map<String, Object> addParamsMap = getEntityParamsGroup(t, 0);

        insertSql.append(addParamsMap.get("insertSql").toString()).replace(insertSql.length() - 1, insertSql.length(), ")");
        insertSqlVal.append(addParamsMap.get("insertSqlCondition").toString()).replace(insertSqlVal.length() - 1, insertSqlVal.length(), ")");
        insertSql.append(insertSqlVal);
        try {
            int result = this.getNamedParameterJdbcTemplate().update(insertSql.toString(), (Map<String, Object>) addParamsMap.get("params"));
            if (result != 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 单一数据删除，根据主键删除
     *
     * @param t
     */
    @Override
    public <T> boolean deleteObjInfo(T t) {
        StringBuilder delSql = new StringBuilder("delete from ");
        delSql.append(this.getEntityTableName(t)).append(" where ");
        Map<String, Object> delFragmentMap = this.getEntityParamsGroup(t, 1);
        delSql.append(delFragmentMap.get("delSql").toString());
        Map<String, Object> params = (Map<String, Object>) delFragmentMap.get("params");
        try {
            this.getNamedParameterJdbcTemplate().update(delSql.toString(), params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 单一数据更新,该方法仅支持主键更新
     *
     * @param t
     */
    @Override
    public <T> boolean updateObjInfo(T t) {
        StringBuilder updateSql = new StringBuilder("update ");
        updateSql.append(this.getEntityTableName(t)).append(" set ");
        Map<String, Object> updateMap = this.getEntityParamsGroup(t, 2);
        if (!updateMap.get("updateSql").toString().equals("")) {
            updateSql.append(updateMap.get("updateSql").toString()).append(" where ").append(updateMap.get("updateSqlCondition").toString());
            int result = this.getNamedParameterJdbcTemplate().update(updateSql.toString(), (Map<String, Object>) updateMap.get("params"));
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 根据 主键 查询某个对象
     *
     * @param c
     * @param id
     * @return
     */
    @Override
    public <T> T selectObjectById(Class c, String id, RowMapper<T> rowMapper) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        searchSql.append(this.getEntityTableNameByClazz(c)).append(" where id=:id");
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            return this.getNamedParameterJdbcTemplate().queryForObject(searchSql.toString(), params, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据主键ID删除对象
     *
     * @param c
     * @param idVal
     * @return
     */
    @Override
    public boolean deleteObjectById(Class c, String idVal) {
        StringBuilder deleteSql = new StringBuilder("delete from ");
        String keyName = this.getPrimaryKeyNameByClazz(c);
        if (keyName.equals("")) {
            deleteSql.append(this.getEntityTableNameByClazz(c)).append(" where ").append(keyName).append("=:").append(keyName);
            Map<String, Object> params = new HashMap<>();
            params.put(keyName, idVal);
            int result = this.getNamedParameterJdbcTemplate().update(deleteSql.toString(), params);
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * 带条件的查询.返回list集合
     *
     * @param c
     * @param map 根据map里面放置的参数
     * @return 返回一个list对象集合
     */
    @Override
    public <T> List<T> seletcObjectByMap(Class c, Map<String, Object> map, RowMapper<T> rowMapper) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        searchSql.append(this.getEntityTableNameByClazz(c));
        if (map != null && !map.isEmpty()) {
            searchSql.append(" where ");
            //拼接查詢參數
            searchSql.append(this.fetchSearchSqlFragment(map));
        }
        //开始查询
        return this.getNamedParameterJdbcTemplate().query(searchSql.toString(), map, rowMapper);
    }

    /**
     * 排序+分页功能+条件查询
     * 语句默认按照升序对记录进行排序
     *
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    @Override
    public <T> List<T> getOrderObjects(Class cl, Map<String, Object> map, String orderstr, Integer beginpos, Integer count, RowMapper<T> rowMapper, boolean isAsc) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        //获取表名
        searchSql.append(this.getEntityTableNameByClazz(cl));
        //添加查询列表
        if (map != null && !map.isEmpty()) {
            searchSql.append(" where ").append(this.fetchSearchSqlFragment(map));
        }
        //假如需要分页
        if (beginpos != null && beginpos >= 1) {
            if (count == null || count < 1) {
                count = HomeworkConstants.PAGE_SIZE;
            }
            searchSql.append(" limit ").append((beginpos - 1) * count).append(" ").append(count);
        }
        //假如需要排序
        if (!orderstr.equals("")) {
            searchSql.append(" order by ").append(orderstr);
            if (isAsc) {
                searchSql.append(" asc");
            }
        }
        return this.getNamedParameterJdbcTemplate().query(searchSql.toString(), map, rowMapper);
    }

    /**
     * 排序+区间查询
     *
     * @param cl
     * @param map
     * @param orderstr
     * @param searchField
     * @param rowMapper
     * @param isAsc
     * @return
     */
    @Override
    public <T> List<T> getAmongObjectWithOrder(Class cl, Map<String, Object> map, String orderstr, String searchField, RowMapper<T> rowMapper, boolean isAsc) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        //获取表名
        searchSql.append(this.getEntityTableNameByClazz(cl));
        if (map != null && !map.isEmpty()) {
            //获取查询参数
            Object[] paramsArray = map.keySet().toArray();
            searchSql.append(" where ").append(searchField).append(" between :").append(paramsArray[0].toString()).append(" and :").append(paramsArray[1].toString());
        }
        if (orderstr != null) {
            searchSql.append(" order by ").append(orderstr);
        }
        if (isAsc) {
            searchSql.append(" asc");
        }
        return this.getNamedParameterJdbcTemplate().query(searchSql.toString(), map, rowMapper);
    }


    /**
     * 根絕類來獲取表名
     *
     * @param clazz
     * @return
     */
    private final String getEntityTableNameByClazz(Class clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return table.name();
    }

    /**
     * 根絕類來獲取主键名
     *
     * @param clazz
     * @return
     */
    private final String getPrimaryKeyNameByClazz(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String keyName = "";
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                keyName = field.getAnnotation(Column.class).name();
            }
        }
        return keyName;
    }

    /**
     * 获取实体表名
     *
     * @param t
     * @return
     */
    private final <T> String getEntityTableName(T t) {
        Table table = t.getClass().getAnnotation(Table.class);
        return table.name();
    }

    /**
     * 返回查询语句和其参数Map
     *
     * @param t
     * @param choose 0:增加一个对象，1:删除一个对象，2：修改一个对象,3:查询
     * @return
     */
    private final <T> Map<String, Object> getEntityParamsGroup(T t, int choose) {
        //获取实体类的Fields
        Field[] fields = t.getClass().getDeclaredFields();
        //存放结果对象
        Map<String, Object> resultMap = new HashMap<>();
        //参数
        Map<String, Object> params = new HashMap<String, Object>();
        //根据条件返回相应的Sql和变量
        switch (choose) {
            //插入
            case 0:
                //声明存放插入语句的sql
                StringBuilder insertSql = new StringBuilder(""), insertSqlCondition = new StringBuilder("");
                //循环遍历fields,获取变量的名和值
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            insertSql.append(columnName + ",");
                            insertSqlCondition.append(":").append(columnName).append(",");
                            //存放参数
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                resultMap.put("insertSql", insertSql.toString());
                resultMap.put("insertSqlCondition", insertSqlCondition.toString());
                resultMap.put("params", params);
                break;
            //删除
            case 1:
                StringBuilder delSql = new StringBuilder("");
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            delSql.append(columnName).append("=:").append(columnName).append(" and ");
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        HomeworkLogOperate.getCurrentLogger(CommonDao.class).error("拼接删除语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("delSql", delSql.toString());
                resultMap.put("params", params);
                break;
            //修改一个对象,根据主键更新
            case 2:
                StringBuilder updateSql = new StringBuilder(""), updateSqlCondition = new StringBuilder();
                for (Field field : fields) {
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            if (field.getAnnotation(Id.class) != null) {
                                updateSqlCondition.append(columnName).append("=:").append(columnName);
                            } else {
                                updateSql.append(columnName).append("=:").append(columnName).append(",");
                            }
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        HomeworkLogOperate.getCurrentLogger(CommonDao.class).error("拼接修改语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("updateSql", updateSql.toString());
                resultMap.put("updateSqlCondition", updateSqlCondition.toString());
                resultMap.put("params", params);
                break;
            //根据条件查询一个对像，精确查询，非like查询
            case 3:
                StringBuilder querySql = new StringBuilder("");
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            querySql.append(columnName).append("=:").append(columnName).append(" and ");
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        HomeworkLogOperate.getCurrentLogger(CommonDao.class).error("拼接查詢语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("querySql", querySql.toString());
                resultMap.put("params", params);
                break;
            default:

                break;

        }
        return resultMap;
    }

    /**
     * 根據參數map來拼接查詢語句
     *
     * @param params
     * @return
     */
    public String fetchSearchSqlFragment(Map<String, Object> params) {
        Set<String> keySet = params.keySet();
        StringBuilder searchFragment = new StringBuilder("");
        for (String key : keySet) {
            searchFragment.append(key).append("=:").append(key).append(" and ");
        }
        return searchFragment.substring(0, searchFragment.lastIndexOf(" and "));
    }
}
