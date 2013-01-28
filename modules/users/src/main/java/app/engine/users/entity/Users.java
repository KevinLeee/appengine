package app.engine.users.entity;

import app.engine.core.EntityDeclaration;

import java.util.Date;

/**
 * <p>
 * </p>
 * User: MichaelLee
 * Date: 13-1-15
 * Time: 下午2:37
 */
public class Users extends EntityDeclaration {

    /**
     * 名称
     */
    private String designation;
    private String firstName;
    private String lastName;
    private String headLine;
    /**
     * 个人简介
     */
    private String blography;
    /**
     * 多个网页连接应该如何存储
     */
    private String links;

    private String mobile;// 手机号码
    private String password;// 密码
    private String email;// 邮箱
    private String realName;// 真名
    private String nickName;// 昵称
    private Long headImage;// 头像
    private Long headImageIn;// 中等头像(50*50)
    private Long headImageMin;// 小头像(30*30)
    private Integer sex;// 性别 0:男；1：女；2：保密
    private Date birthday;// 生日
    private Long provinceId;// 省份
    private Long cityId;// 城市
    private Long townId;// 区/县Id
    private Integer education;// 教育
    private String school;// 毕业院校
    private Integer major;// 所学专业
    private Integer trade;// 从事行业
    private Integer position;// 职位
    private String telephone;// 联系电话
    private String qq;// qq
    private String msn;// msn
    private String sign;// 个性签名
    private String registerPlace;// 注册位置 1自然注册 2购物页面
    private Date registerDate;// 注册日期
    private Integer customerType;// 用户类型
    private Integer customerStatus;// 用户状态
    private Integer customerGroup;// 用户组别
    private Integer customerLevel;// 用户等级
    private String categroyIdString;// 分类ID,多选，用逗号隔开
    private Integer integral;// 积分
    private Integer registerStep;// 注册进度 1 完成基本信息 2 完成考试类别选择 3 完成关注用户选择
    private String address;// 联系地址
    private Integer vip; // 0 非 1 是
    private String headImageUrl;// 头像图片地址
//    private CustomerAttach customerAttach;

}
