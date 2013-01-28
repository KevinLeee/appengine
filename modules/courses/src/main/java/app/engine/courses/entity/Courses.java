package app.engine.courses.entity;

import app.engine.core.EntityDeclaration;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.File;
import java.util.Set;

/**
 * content of courses 教学内容，选修课程
 * User: MichaelLee
 * Date: 13-1-14
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Courses")
public class Courses extends EntityDeclaration {
    private String title;
    private String subTitle;
    private Set keyWords;
    private Category category;
    private Integer categoryId;
    /**
     * Course Summary
     */
    private String summary;
    /**
     * Course Goal and Objectives
     * 课程目的，或者目标objectives
     */
    private String goal;
    /**
     * Intended Audience 目标受众
     */
    private String audience;
    /**
     * Course Requirements:
     */
    private String requirements;
    /**
     * Instructional Level:
     * Introductory
     * Intermediate
     * Advanced
     * All Levels (Comprehensive)
     */
    private Integer level;
    /**
     * Set the level of privacy for your course.
     * A “public” course is available for anyone to take on site. A “private” course is only accessible by a direct URL.
     * It will not show up on site's search. This is great if you have a course you'd like to offer but only to a select group.
     */
    private Integer privacy;
    /**
     * Set the price of your course and promote it with a discounted coupon code.
     */
    private Double price;
    /**
     * need to become a premium instructor to create coupon codes!
     */
    private Long couponCodes;

    private File images;

    private String video;

    /**
     * 是否发布 0为是，1为否
     */
    private Integer unPublish;

    /**
     * 是否免费  0为是，1为否
     */
    private Integer isFree;


}
