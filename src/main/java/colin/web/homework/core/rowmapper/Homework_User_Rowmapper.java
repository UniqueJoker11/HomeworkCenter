package colin.web.homework.core.rowmapper;

import colin.web.homework.core.pojo.Homework_User_Entity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ASUS on 2015/7/11.
 */
@Component
public class Homework_User_Rowmapper implements RowMapper<Homework_User_Entity> {
    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws java.sql.SQLException if a SQLException is encountered getting
     *                               column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Homework_User_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Homework_User_Entity entity=new Homework_User_Entity();
        entity.setUser_id(rs.getString("user_id"));
        entity.setUser_password(rs.getString("user_password"));
        entity.setUser_name(rs.getString("user_name"));
        entity.setUser_callname(rs.getString("user_callname"));
        entity.setUser_createtime(rs.getString("user_createtime"));
        entity.setUser_email(rs.getString("user_email"));
        entity.setUser_head(rs.getString("user_head"));
        entity.setUser_logintime(rs.getString("user_logintime"));
        entity.setUser_phone(rs.getString("user_phone"));
        return entity;
    }
}
