package oop.kevin.clients.datasync.repository.mybatis;

import oop.kevin.clients.datasync.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-29
 * Time: 下午6:03
 * <p/>
 * 其中，@Repository表示这是一个被Spring管理的资源，资源名称为menuMapper；@Select表示operateReturnBeans方法为一个select方
 * 法；@Results表示返回结果，@Result将返回结果中的字段名与实体类关联；@Param表示String sql这个变量是用于Mybatis的一个变量
 * ，其名称为sql（value值），该变量在@Select中调用（通过${sql}调用）。
 */
@Repository(value = "CustomerDao")
public interface CustomerDao {
    @Select(value = "${sql}")
    @Results(value = {@Result(id = true, property="cusId", column="CUS_ID"),
            @Result(property="cusName", column="CUS_NAME"),
            @Result(property="cusPwd", column="CUS_PWD"),
            @Result(property="email", column="EMAIL"),
            @Result(property="mobile", column="MOBILE"),
            @Result(property="realName", column="REAL_NAME"),
            @Result(property="sex", column="SEX"),
            @Result(property="idNum", column="ID_NUM"),
            @Result(property="qq", column="QQ"),
            @Result(property="photo", column="PHOTO"),
            @Result(property="lastloginIP", column="LASTLOGIN_IP"),
            @Result(property="lastloginTime", column="LASTLOGIN_TIME"),
            @Result(property="regTime", column="REG_TIME"),
            @Result(property="birthday", column="BIRTHDAY"),
            @Result(property="loginTimes", column="LOGIN_TIMES"),
            @Result(property="isComplete", column="IS_COMPLETE"),
            @Result(property="completeTime", column="COMPLETE_TIME"),
            @Result(property="MSN", column="MSN"),
            @Result(property="address", column="ADDRESS"),
            @Result(property="cusType", column="CUS_TYPE"),
            @Result(property="studyType", column="STUDY_TYPE"),
            @Result(property="subjectId", column="SUBJECT_ID"),
            @Result(property="cusFromUrl", column="CUS_FROM_URL"),
            @Result(property="cusWebFrom", column="CUS_WEB_FROM"),
            @Result(property="cusWebAgent", column="CUS_WEB_AGENT"),
            @Result(property="fromType", column="FROMTYPE"),
            @Result(property="freezeStatus", column="FREEZE_STATUS"),
            @Result(property="freezeChangedDate", column="FREEZE_CHANGED_DATE")})
    List <Customer> operateReturnBeans(@Param(value = "sql") String sql);

}
