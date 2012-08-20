package mobion.point.dao;

import java.io.Serializable;
import java.util.List;

public interface MobionDAO<KeyType extends Serializable, T extends Serializable> {

	public Object save(T t);

	public void save(List<T> list);

	public void delete(T t);

	public T update(T t);

	public void update(List<T> list);

	public T findById(KeyType id);
	
	public List<T> findAll();
	
	public List<T> findByExample(T instance);

	public List<T> findByProperty(String propertyName, Object value);

}