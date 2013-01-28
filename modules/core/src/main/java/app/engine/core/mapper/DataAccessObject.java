package app.engine.core.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * </p>
 * User: MichaelLee
 * Date: 13-1-15
 * Time: 下午5:27
 */
@SqlMapper
public interface DataAccessObject<T> {

    public List<T> read(Pagination page, @Param(value = "conditions") List<Condition> conditions);

    public T readById(Object id);

    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public void deleteById(Object id);

    /**
     * 根据id的集合删除一批记录
     *
     * @param ids
     */
    public void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 把ids对应的实体中的属性值更新成entity中所有非null的属性值
     *
     * @param entity
     * @param ids
     */
    public void updateBatch(@Param("entity") T entity, @Param("ids") List<Long> ids);
}
