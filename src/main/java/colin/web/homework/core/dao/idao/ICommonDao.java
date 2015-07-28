package colin.web.homework.core.dao.idao;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
public interface ICommonDao<T> {
    /**
     * 单一增加数据
     */
    public boolean addObjInfo(T t);

    /**
     * 单一数据删除
     */
    public boolean deleteObjInfo(T t);

    /**
     * 单一数据更新,该方法仅支持主键更新
     */
    public boolean updateObjInfo(T t);

    /**
     * 根据 主键 查询某个对象
     *
     * @param <E>
     * @param c
     * @param id
     * @return
     */
    public T selectObjectById(final Class c, final String id, RowMapper<T> rowMapper);

    /**
     * 带条件的查询.返回list集合
     *
     * @param <E>
     * @param c
     * @param map 根据map里面放置的参数
     * @return 返回一个list对象集合
     */
    public List<T> seletcObjectByMap(Class c, Map<String, Object> map, RowMapper<T> rowMapper);

    /**
     * 排序+分页功能+条件查询
     *
     * @param <E>
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    public List<T> getOrderObjects(final Class cl, final Map<String, Object> map,
                                   final String orderstr, final Integer beginpos, final Integer count, RowMapper<T> rowMapper, boolean isAsc);
}
