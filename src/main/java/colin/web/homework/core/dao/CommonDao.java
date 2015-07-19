package colin.web.homework.core.dao;

import colin.web.homework.core.dao.idao.ICommonDao;
import colin.web.homework.tools.HomeworkLogOperate;
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
public class CommonDao<T> extends NamedParameterJdbcDaoSupport implements ICommonDao<T> {

    /**
     * 单一增加数据
     *
     * @param t
     */
    @Override
    public boolean addObjInfo(T t) {
        //声明增加SQL语句
        StringBuilder insertSql = new StringBuilder("insert into ");
        StringBuilder insertSqlVal = new StringBuilder(" values(");
        //获取实体类的表明
        insertSql.append(this.getEntityTableName(t)).append(" (");
        //获取每个字段的对应表字段
        Map<String, Object> addParamsMap = getEntityParamsGroup(t, 0);

        insertSql.append(addParamsMap.get("insertSql").toString()).replace(insertSql.length() - 1, insertSql.length(), ")");
        insertSqlVal.append(addParamsMap.get(insertSqlVal).toString()).replace(insertSqlVal.length() - 1, insertSqlVal.length(), ")");
        insertSql.append(insertSqlVal);
        try{
            int result = this.getNamedParameterJdbcTemplate().update(insertSql.toString(), (Map<String, Object>) addParamsMap.get("params"));
            if (result != 1) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
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
    public boolean deleteObjInfo(T t) {
        StringBuilder delSql=new StringBuilder("delete from ");
        delSql.append(this.getEntityTableName(t)).append(" where ");
        Map<String,Object> delFragmentMap=this.getEntityParamsGroup(t,1);
        delSql.append(delFragmentMap.get("delSql").toString());
        Map<String,Object> params= (Map<String, Object>) delFragmentMap.get("params");
        try{
            this.getNamedParameterJdbcTemplate().update(delSql.toString(),params);
            return true;
        }catch (Exception e){
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
    public boolean updateObjInfo(T t) {
        StringBuilder updateSql=new StringBuilder("update ");
        updateSql.append(this.getEntityTableName(t)).append(" set ");

        return false;
    }

    /**
     * 根据 主键 查询某个对象
     *
     * @param c
     * @param id
     * @return
     */
    @Override
    public T selectObjectById(Class c, String id, RowMapper<T> rowMapper) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        searchSql.append(this.getEntityTableNameByClazz(c)).append(" where id=:id");
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try{
            return this.getNamedParameterJdbcTemplate().queryForObject(searchSql.toString(), params, rowMapper);
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
    public List<T> seletcObjectByMap(Class c, Map<String, Object> map, RowMapper<T> rowMapper) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        searchSql.append(this.getEntityTableNameByClazz(c)).append(" where ");
        //拼接查詢參數
        searchSql.append(this.fetchSearchSqlFragment(map));
        //开始查询
        System.out.println(this.getNamedParameterJdbcTemplate()==null);
        return this.getNamedParameterJdbcTemplate().query(searchSql.toString(),map,rowMapper);
    }

    /**
     * 排序+分页功能+条件查询
     *
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    @Override
    public List<T> getOrderObjects(Class cl, Map<String, Object> map, String orderstr, Integer beginpos, Integer count, RowMapper<T> rowMapper) {
        return null;
    }

    /**
     * 排序(升序)+分页功能+条件查询
     *
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    @Override
    public List<T> getOrderAscObjects(Class cl, Map<String, Object> map, String orderstr, Integer beginpos, Integer count, RowMapper<T> rowMapper) {
        return null;
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
     * 获取实体表名
     *
     * @param t
     * @return
     */
    private final String getEntityTableName(T t) {
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
    private final Map<String, Object> getEntityParamsGroup(T t, int choose) {
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
                            updateSql.append(columnName).append("=:").append(columnName).append(",");
                            if (field.getAnnotation(Id.class) != null) {
                                updateSqlCondition.append(columnName).append("=:").append(columnName);
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
