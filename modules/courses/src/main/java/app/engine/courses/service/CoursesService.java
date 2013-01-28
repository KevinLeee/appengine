package app.engine.courses.service;

import app.engine.core.mapper.Condition;
import app.engine.core.mapper.Pagination;
import app.engine.core.persistence.PersistenceService;
import app.engine.courses.dao.CoursesDao;
import app.engine.courses.entity.Courses;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 * User: MichaelLee
 * Date: 13-1-16
 * Time: 下午3:24
 */
@Service
public class CoursesService extends PersistenceService<Courses, CoursesDao> {


    /**
     * 读取课程
     * @param pagination
     * @param conditions
     * @return
     */
    public List<Courses> readCourses(Pagination pagination, List<Condition> conditions) {

        return dataAccess.read(pagination, conditions);

    }
}
