package app.engine.courses.entity;

import app.engine.core.EntityDeclaration;

/**
 * <p>课程</p>
 * User: MichaelLee
 * Date: 13-1-14
 * Time: 下午6:09
 */
public class Curriculum extends EntityDeclaration {
    /**
     * 章节
     */
    private String section;
    /**
     * 章节标题
     */
    private String sectionTitle;
    /**
     * 演讲、课件
     */
    private String lectureTitle;
    /**
     * 描述
     */
    private String description;
    /**
     * Course has a minimum of 5 complete lectures with title, description, and asset.
     */
    private int lecturesCompleted;
    /**
     * Course has a minimum of one (1) hour of content.
     */
    private int courseCompleted;

}
