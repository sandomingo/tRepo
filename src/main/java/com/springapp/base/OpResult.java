package com.springapp.base;

/**
 * Service包中类的操作返回值类
 * User: SanDomingo
 * Date: 3/13/14
 * Time: 3:16 PM
 */
public class OpResult {
    String exception; // 操作失败的原因
    Object output; // 操作输出内容
    Boolean status; // 操作结果状态，成功／失败

    private OpResult(String exception, Object output, Boolean status) {
        this.exception = exception;
        this.output = output;
        this.status = status;
    }

    public OpResult() {
        this.status = Boolean.TRUE;
    }

    public static OpResult genSuccessOpResult(String output) {
        OpResult result = new OpResult(null, output, true);
        return result;
    }

    public static OpResult genFailedOpResult(String exception) {
        OpResult result = new OpResult(exception, null, false);
        return result;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
        this.setStatus(Boolean.FALSE);
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void showResult() {
        if (status) {
            System.out.println("Success!");
        } else {
            System.err.println(exception);
        }
    }
}
