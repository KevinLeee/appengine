package app.engine.courses.dao;

import app.engine.core.mapper.Condition;
import app.engine.core.mapper.DataAccessObject;
import app.engine.core.mapper.Pagination;
import app.engine.courses.entity.Courses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 * User: MichaelLee
 * Date: 13-1-16
 * Time: 下午1:23
 */
public interface CoursesDao extends DataAccessObject<Courses> {

    List<Courses> readCourses(Pagination page, @Param(value = "conditions") List<Condition> conditionList);


}
