package club.vtuka.tuka.model;

/**
 * Created by Vicky on 2018/8/19.
 */
public class Result {
    private String msg;
    private Integer status; // 1 / 0
    private Object result;

    private String errorMsg = "操作失败";
    private String successMsg = "操作成功";
    private String wrongParamStr = "传参错误";

    public static final int error = 0;
    public static final int success = 1;
    public static final int wrongParam = 2;


    public Result(Integer serviceResult, Object resultObject){
        if(serviceResult == Result.error){
            this.msg=errorMsg;
            this.status=Result.error;
            this.result = resultObject;
        }
        if(serviceResult == 1){
            this.msg=successMsg;
            this.status=Result.success;
            this.result = resultObject;
        }
        if(serviceResult == Result.wrongParam){
            this.msg=wrongParamStr;
            this.status=Result.wrongParam;
            this.result=resultObject;
        }
    }

    public Result(String msg, Integer status, Object result) {
        this.msg = msg;
        this.status = status;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
