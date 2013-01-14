package oop.kevin.clients.datasync.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-6
 * Time: 下午2:10
 * <p/>
 */
public class AsyncCustomer {
    private int cusId;
    private String cusPwd;
    private String email;
    private String mobile;
    private String sex;
    private Date regTime;
    private int cusType;  //类型　0代表注册学员，1内部学员，2临时学员，3内部体验，4内部体验(三十天)
    private int subjectId;
    private String cusFromUrl;
    //注册标识来源页面 1项目页面注册 2 订单页面注册
    private String fromType;

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusPwd() {
        return cusPwd;
    }

    public void setCusPwd(String cusPwd) {
        this.cusPwd = cusPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getCusType() {
        return cusType;
    }

    public void setCusType(int cusType) {
        this.cusType = cusType;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getCusFromUrl() {
        return cusFromUrl;
    }

    public void setCusFromUrl(String cusFromUrl) {
        this.cusFromUrl = cusFromUrl;
    }
}
