package colin.web.homework.core.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ASUS on 2015/7/26.
 */
public class DefaultRowmapper<T> implements RowMapper<T> {

    private String className = "";

    public DefaultRowmapper(String className) {
        this.className = className;
    }

    /**
     * Implementations must implement this method to map each row of data in the
     * ResultSet. This method should not call {@code next()} on the ResultSet;
     * it is only supposed to map values of the current row.
     *
     * @param rs
     *            the ResultSet to map (pre-initialized for the current row)
     * @param rowNum
     *            the number of the current row
     * @return the result object for the current row
     * @throws java.sql.SQLException
     *             if a SQLException is encountered getting column values (that
     *             is, there's no need to catch SQLException)
     */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T t = null;
        try {
            t = (T) Class.forName(this.className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);// 对于似有属性予以访问
            //过滤掉static final等字段
            if(!Modifier.isFinal(field.getModifiers())&&!Modifier.isStatic(field.getModifiers())){
                String columnName = "";
                if (field.getAnnotation(Column.class) != null) {
                    columnName = field.getAnnotation(Column.class).name();
                } else {
                    columnName = field.getName();
                }
                // 获取列的名称
                try {
                    if(field.getType().toString().equals("class java.math.BigInteger")){
                        if(rs.getObject(columnName)!=null){
                            field.set(t, BigInteger.valueOf(Double.valueOf(rs.getObject(columnName).toString()).longValue()));
                        }
                    }else{
                        Object columnObj=null;
                        try{
                            columnObj=rs.getObject(columnName);
                        }catch(SQLException e){
                            columnObj=null;
                        }
                        if(columnObj!=null){
                            field.set(t, rs.getObject(columnName));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

}
