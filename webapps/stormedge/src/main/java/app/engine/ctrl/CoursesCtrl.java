package app.engine.ctrl;

import app.engine.core.ctrl.MultiActionCtrl;
import app.engine.courses.entity.Courses;
import app.engine.courses.service.CoursesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p></p>
 * User: MichaelLee
 * Date: 13-1-16
 * Time: 上午11:52
 */
@Controller
public class CoursesCtrl extends MultiActionCtrl {

    @Resource
    private CoursesService coursesService;

    public ModelAndView readCourses(HttpServletRequest request,
                              HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        List<Courses> coursesList = coursesService.readAll();
        return modelAndView;

    }
}
