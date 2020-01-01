package club.vtuka.tuka.model;

public class RespResult {
    private String msg;
    private Integer status;
    private Object resultObj;

    public static String wrongParam = "传参错误";
    public static String success = "操作成功";
    public static String fail = "操作失败";

    public static int successInt = 1;
    public static int failInt = 0;

    public RespResult(Integer status, String msg, Object resultObj){
        this.status=status;
        this.msg=msg;
        this.resultObj=resultObj;
    }

    public static RespResult ok(String msg,Object resultObj){
        return new RespResult(200,msg,resultObj);
    }


    public static RespResult ok(String msg){
        return new RespResult(200,msg,null);
    }

    public static RespResult ok(){
        return new RespResult(200,success,null);
    }


    public static RespResult error(String msg,Object resultObj){
        return new RespResult(500,msg,resultObj);
    }


    public static RespResult error(String msg){
        return new RespResult(500,msg,null);
    }

    public static RespResult error(){
        return new RespResult(500,fail,null);
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

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
}
