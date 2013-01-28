package app.engine.courses.entity;

import app.engine.core.EntityDeclaration;

/**
 * <p>
 * dsfasda
 * </p>
 * User: MichaelLee
 * Date: 13-1-14
 * Time: 下午4:31
 */
public class Category extends EntityDeclaration {
    private String categoryName;// 类别名称
    private Integer sequence;// 序号，用于调整排序
    private Category parent;// 父节点引用
    private Long parentId;// 父节点Id
    private String parentDescript;// 层级结构描述
    private Integer deleted;// 删除状态 0正常 1删除
}
