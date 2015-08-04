package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.dao.CommonDao;
import colin.web.homework.core.dao.idao.ICommonDao;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
@Repository
public class DecorateCommnDao implements ICommonDao {
    @Autowired
    private CommonDao commonDao;

    /**
     * 单一增加数据
     *
     * @param t
     */
    @Override
    public<T> boolean addObjInfo(T t) {
        return commonDao.addObjInfo(t);
    }

    /**
     * 单一数据删除
     *
     * @param t
     */
    @Override
    public<T> boolean deleteObjInfo(T t) {
        return commonDao.deleteObjInfo(t);
    }

    /**
     * 单一数据更新,该方法仅支持主键更新
     *
     * @param t
     */
    @Override
    public<T> boolean updateObjInfo(T t) {
        return commonDao.updateObjInfo(t);
    }

    /**
     * 根据 主键 查询某个对象
     *
     * @param c
     * @param id
     * @return
     */
    @Override
    public<T> T selectObjectById(Class c, String id, RowMapper<T> rowMapper) {
        return (T) commonDao.selectObjectById(c, id, rowMapper);
    }

    /**
     * 带条件的查询.返回list集合
     *
     * @param c
     * @param map 根据map里面放置的参数
     * @return 返回一个list对象集合
     */
    @Override
    public<T> List<T> seletcObjectByMap(Class c, Map<String, Object> map, RowMapper<T> rowMapper) {
        return commonDao.seletcObjectByMap(c, map, rowMapper);
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
    public<T> List<T> getOrderObjects(Class cl, Map<String, Object> map, String orderstr, Integer beginpos, Integer count, RowMapper<T> rowMapper, boolean isAsc) {
        return commonDao.getOrderObjects(cl, map, orderstr, beginpos, count, rowMapper, isAsc);
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
        return null;
    }

    /**
     * 综合分页查询牧歌对象的内容
     * @param cl
     * @param map
     * @param orderstr
     * @param beginpos
     * @param count
     * @param rowMapper
     * @param isAsc
     * @param <G>
     * @return
     */
    public <G> Map<String, Object> getOrderObjectsByPage(Class cl, Map<String, Object> map, String orderstr, Integer beginpos, Integer count, RowMapper<G> rowMapper, boolean isAsc) {
        //查询当前页的数据
        List<G> resultList = this.commonDao.getOrderObjects(cl, map, orderstr, beginpos, count, rowMapper, isAsc);
        //查询总数据
        List<G> allData = this.commonDao.seletcObjectByMap(cl, map, rowMapper);
        Map<String, Object> resultMap = new HashMap<>();
        //设置数据总量
        resultMap.put("totalCount", allData.size());
        //设置总页数
        resultMap.put("totalPage", allData.size() % count > 0 ? allData.size() / count + 1 : allData.size() / count);
        //设置当前页
        resultMap.put("currentPage", beginpos);
        //设置当前页的数据
        resultMap.put("currentData", resultList);
        return resultMap;
    }
}
