package colin.web.homework.core.pojo;

/**
 * Created by ASUS on 2015/8/29.
 */
public class Homework_Role_Authority_Entity {
    private String role_authority_id;
    private String role_id;
    private String authority_id;
    private String authority_type;
    private int is_access;
    private int is_authorization;

    public String getRole_authority_id() {
        return role_authority_id;
    }

    public void setRole_authority_id(String role_authority_id) {
        this.role_authority_id = role_authority_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getAuthority_type() {
        return authority_type;
    }

    public void setAuthority_type(String authority_type) {
        this.authority_type = authority_type;
    }

    public int getIs_access() {
        return is_access;
    }

    public void setIs_access(int is_access) {
        this.is_access = is_access;
    }

    public int getIs_authorization() {
        return is_authorization;
    }

    public void setIs_authorization(int is_authorization) {
        this.is_authorization = is_authorization;
    }
}
