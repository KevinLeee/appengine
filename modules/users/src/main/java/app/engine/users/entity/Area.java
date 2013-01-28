package app.engine.users.entity;

import app.engine.core.EntityDeclaration;
import app.engine.core.OrderBy;

@OrderBy("id asc")
public class Area extends EntityDeclaration {

    // 上级ID
    private Integer parentId;
    // 名称
    private String areaName;
    // 类型
    private Integer areaType;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

}
