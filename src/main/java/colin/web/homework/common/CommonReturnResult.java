package colin.web.homework.common;

/**
 * Created by DELL on 2015/12/25.
 */
public class CommonReturnResult {
    private Boolean success;
    private Object data;
    private String msg;

    public CommonReturnResult(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public CommonReturnResult(Boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public CommonReturnResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CommonReturnResult(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
