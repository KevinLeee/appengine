package app.engine.core.persistence;

import app.engine.core.EntityDeclaration;
import app.engine.core.mapper.*;
import app.engine.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * service 层的基类，所有service类必须继承自此类，该类不能直接使用。
 * <p/>
 * 将service层一些通用的操作给抽离出来，封装到此类中，其他service类必须继承此类，子类可以直接使用此类中的方法。<br/>
 * 该类使用泛型实现了实体和dao层封装，子类继承此方法时必须指明对应的Model和Dao具体实现类，在{@link #setDataAccess(K dataAccess)}方法中使用spring注解方式实现。<br/>
 * 子类在需要使用dao对象的地方，直接调用dataAccess.<i>method()</i>，该类当前只支持自动装配一个dao实例，如果需要多个，在自己的service类中以spring注解方式自行配置。
 *
 * @param <T> 主要操作的实体类型
 * @param <K> 主要操作的Dao类型
 *            <p/>
 *            User: MichaelLee
 *            Date: 13-1-14
 *            Time: 下午2:51
 */
public abstract class PersistenceService<T extends EntityDeclaration, K extends DataAccessObject<T>> {
    /**
     * dao原型属性
     */
    protected K dataAccess;

    /**
     * 根据K泛型自动装载DataAccessObject
     *
     * @param dataAccess
     */
    @Autowired
    public final void setDataAccess(K dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void deleteById(Object id) {
        dataAccess.deleteById(id);
    }

    public void delete(T entity) {
        dataAccess.delete(entity);
    }

    public T readById(Object id) {
        return dataAccess.readById(id);
    }

    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     * 主要是为了方便一个条件的查询，不用在调用时自己封装成List
     *
     * @param pagination
     * @param condition
     * @return
     */
    public List<T> read(Pagination pagination, Condition condition) {
        List<Condition> conditions = null;
        if (condition != null) {
            conditions = new ArrayList<Condition>();
            conditions.add(condition);
        }
        return read(pagination, conditions);
    }

    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     *
     * @param pagination
     * @param conditions
     * @return
     */
    public List<T> read(Pagination pagination, ConditionMap conditions) {
        List<Condition> conditionList = null;
        if (conditions != null) {
            conditionList = conditions.getItems();
        }
        return dataAccess.read(pagination, conditionList);
    }

    public List<T> read(ListPageQuery query) {
        return dataAccess.read(query.getPagination(), query.getConditions().getItems());
    }

    /**
     * 查找所有的记录
     *
     * @return
     */
    public List<T> readAll() {
        return dataAccess.read(null, null);
    }

    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     *
     * @param pagination
     * @param conditions
     * @return
     */
    public List<T> read(Pagination pagination, List<Condition> conditions) {
        return dataAccess.read(pagination, conditions);

    }

    public List<T> read(Pagination pagination) {
        return dataAccess.read(pagination, null);
    }

    public void create(T entity) {
        if (entity.getId() == null) {
            dataAccess.create(entity);
        } else {
            dataAccess.update(entity);
        }
    }

    /**
     * 批量保存所有实体
     *
     * @param entities
     */
    public void createBatch(List<T> entities) {
        if (!CollectionUtil.isEmpty(entities)) {
            for (T entity : entities) {
                create(entity);
            }
        }
    }

    /**
     * 根据id的集合删除一批记录
     *
     * @param ids
     */
    public void deleteByIds(List<Long> ids) {
        if (!CollectionUtil.isEmpty(ids)) {
            dataAccess.deleteByIds(ids);
        }
    }

    /**
     * 把ids对应的实体中的属性值更新成entity中所有非null的属性值
     *
     * @param entity
     * @param ids
     */
    public void updateBatch(T entity, List<Long> ids) {
        if (!CollectionUtil.isEmpty(ids)) {
            dataAccess.updateBatch(entity, ids);
        }
    }

    /**
     * 更新list中所有的实体。
     *
     * @param entities
     */
    public void updateBatch(List<T> entities) {
        if (!CollectionUtil.isEmpty(entities)) {
            for (T entity : entities) {
                create(entity);
            }
        }
    }
}
