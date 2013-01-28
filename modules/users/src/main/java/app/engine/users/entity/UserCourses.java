package app.engine.users.entity;

import app.engine.core.EntityDeclaration;

/**
 * <p>
 * </p>
 * User: MichaelLee
 * Date: 13-1-15
 * Time: 下午3:27
 */
public class UserCourses extends EntityDeclaration {
    private Long userId;
    private Long coursesId;
    /**
     * 类别名称
     */
    private String categoryName;
}
