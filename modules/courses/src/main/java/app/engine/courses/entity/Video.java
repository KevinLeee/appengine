package app.engine.courses.entity;

import app.engine.core.EntityDeclaration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * </p>
 * User: MichaelLee
 * Date: 13-1-15
 * Time: 下午4:13
 */
public class Video extends EntityDeclaration {
    private Category category;// 类别
    private Long categoryId;// 类别ID
    private Long subjectId;// 科目ID
    private String calssType;// 学术班型
    private String videoName;// 视频名称
    private String videoNumber;// 视频编号
    private String videoParagraph;// 视频段落
    private Date expectUploadDate;// 预计上架时间
    private Integer videoDifficulty;// 视频难度 0 易 1 中 2 难
    private String keynoteTeacher;// 主讲教师
    private String teacherType;// 教师类型
    private String examYear;// 考试年份
    private String version;// 时间版本
    private String makeGroup;// 生产组别
    private String searchKey;// 视频关键字
    private String teacherPrompt;// 教师提示
    private String remark;// 视频备注
    private BigDecimal amount;// 基础价格
    private Integer videoState;// 视频状态 3已创建 5 待审核 6 已审核 7 未过审 2 已冻结
    private Integer deleted;// 逻辑删除标识 0 正常 1 已删除
    private Collection<MediaPath> mediaPath = new ArrayList<MediaPath>(); // 关联的媒体文件
}
