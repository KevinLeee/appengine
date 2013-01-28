package app.engine.core.ctrl;

import app.engine.core.mapper.ListPageQuery;
import app.engine.core.utils.DateUtils;
import app.engine.core.utils.StringUtils;
import app.engine.core.view.ExtendedModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MultiActionCtrl extends MultiActionController {


    public MultiActionCtrl() {
        super();
//		ParameterMethodNameResolver resolver = (ParameterMethodNameResolver) ApplicationContextAccessor.getBean("parameterMethodNameResolver");
//		super.setMethodNameResolver(resolver);
    }

    public MultiActionCtrl(Object delegate) {
        super(delegate);

    }


    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
                /*数据转换成Date类型*/
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 设置查询参数及查询结果
     */
    protected ModelAndView putToModelAndView(List<?> list, ListPageQuery params) {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("list", list);
        content.put("pagination", params.getPagination());
        content.put("conditions", params.getConditions());

        ModelAndView modelAndView = new ExtendedModelAndView(content);
        return modelAndView;

    }

    /**
     * 绑定查询参数
     */
    protected ListPageQuery bindToListPageQuery(HttpServletRequest request) {

        ListPageQuery parameters = new ListPageQuery();
        new ServletRequestDataBinder(parameters).bind(request);
        return parameters;

    }


    /**
     * 获得整形参数
     */
    public int getIntValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        return Integer.parseInt(str);

    }

    /**
     * 获得Long类型参数
     */
    public long getLongValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        return Long.parseLong(str);

    }

    /**
     * 获得Float类型参数
     */
    public float getFloatValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        return Float.parseFloat(str);

    }

    /**
     * 获得日期类型参数
     */
    public Date getDateValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        try {
            return DateUtils.parse(str, DateUtils.PATTERN_YYYY_MM_DD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获得字符串类型参数
     */
    public String getStringValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        return str == null ? "" : str.trim();

    }

    /**
     * 获得数组类型参数
     */
    public String[] getArrayValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        return str.split(",");

    }

    /**
     * 获得集合类型参数
     */
    public List<Long> getListValue(HttpServletRequest request, String name) {

        String str = request.getParameter(name);
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(name + "参数异常！");
        }
        String[] array = str.split(",");
        List<Long> list = new ArrayList<Long>();

        for (String s : array) {
            list.add(Long.parseLong(s));
        }
        return list;

    }

    /**
     * 获得批量更新的Map类型
     */
    public Map<String, Object> getMap(int status, List<String> list) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("list", list);
        return map;

    }

    /**
     * 将对象以json的格式输出
     *
     * @param response
     * @param jsonObject
     */
    protected final void outJSON(HttpServletResponse response, Object jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setContentType("text/plain;charset=UTF-8");
            mapper.writeValue(response.getOutputStream(), jsonObject);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
