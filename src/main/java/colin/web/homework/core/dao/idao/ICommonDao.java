package colin.web.homework.core.dao.idao;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
public interface ICommonDao {
    /**
     * 单一增加数据
     */
    public <T> boolean addObjInfo(T t);

    /**
     * 单一数据删除
     */
    public <T> boolean deleteObjInfo(T t);

    /**
     * 单一数据更新,该方法仅支持主键更新
     */
    public <T> boolean updateObjInfo(T t);

    /**
     * 根据 主键 查询某个对象
     * @param c
     * @param id
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> T selectObjectById(final Class c, final String id, RowMapper<T> rowMapper);

    /**
     * 根据主键ID删除对象
     * @param c
     * @param idVal
     * @return
     */
    public boolean deleteObjectById(final Class c, final String idVal);

    /**
     * 带条件的查询.返回list集合
     * @param c
     * @param map
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> seletcObjectByMap(Class c, Map<String, Object> map, RowMapper<T> rowMapper);

    /**
     * 排序+分页功能+条件查询
     * @param cl
     * @param map
     * @param orderstr
     * @param beginpos
     * @param count
     * @param rowMapper
     * @param isAsc
     * @param <T>
     * @return
     */
    public <T> List<T> getOrderObjects(final Class cl, final Map<String, Object> map,
                                       final String orderstr, final Integer beginpos, final Integer count, RowMapper<T> rowMapper, boolean isAsc);

    /**
     * 排序+区间查询
     *
     * @param cl
     * @param map
     * @param orderstr
     * @param searchField
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> getAmongObjectWithOrder(final Class cl, final Map<String, Object> map,
                                               final String orderstr, final String searchField, RowMapper<T> rowMapper, boolean isAsc);
}
