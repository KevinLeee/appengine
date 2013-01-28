package app.engine.users.entity;

import app.engine.core.EntityDeclaration;
import app.engine.courses.entity.Category;

/**
 * <p></p>
 * User: MichaelLee
 * Date: 13-1-17
 * Time: 下午12:49
 */
public class UserCategory extends EntityDeclaration {

    private Long userId;
    private Users users;
    private Long categoryId;
    private Category category;
}
