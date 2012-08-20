package mobion.point.dao.impl;

import java.io.Serializable;
import java.util.List;

import mobion.point.dao.MobionDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class MobionAbstractDAO<KeyType extends Serializable, T extends Serializable>
		extends HibernateDaoSupport implements MobionDAO<KeyType, T> {

	private static final Log LOG = LogFactory.getLog(MobionAbstractDAO.class);

	Class<T> instanceClazz;
	Class<KeyType> keyClass;

	public MobionAbstractDAO(Class<KeyType> keyClass, Class<T> instanceClazz) {
		this.instanceClazz = instanceClazz;
		this.keyClass = keyClass;
	}

	public Object save(T t) {
		LOG.debug("save T instance" + t);
		
		return getHibernateTemplate().save(t);

	}

	public void save(List<T> list) {
		LOG.debug("save List<T> instance" + list);
		
		for (T t : list) {
			save(t);
		}
	}

	public void delete(T t) {
		LOG.debug("deleting T instance");
		getHibernateTemplate().delete(t);
	}

	public T update(T t) {
		LOG.debug("update T instance" + t);
		return getHibernateTemplate().merge(t);
	}

	public void update(List<T> list) {
		LOG.debug("update T instance" + list);
		for (T t : list) {
			getHibernateTemplate().merge(t);
		}
	}

	@SuppressWarnings("unchecked")
	public T findById(KeyType id) {
		LOG.debug("getting T instance with id: " + id);
		T instance = (T) getHibernateTemplate()
				.get(instanceClazz.getName(), id);

		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(instanceClazz));

	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T instance) {
		LOG.debug("finding T instance by example" + instance);

		List<T> results = getHibernateTemplate().findByExample(instance);
		LOG.debug("find by example successful, result size: " + results.size());

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		LOG.debug("finding T instance with property: " + propertyName
				+ ", value: " + value);

		String queryString = "from T as model where model." + propertyName
				+ "= ?";
		return getHibernateTemplate().find(queryString, value);
	}

}
