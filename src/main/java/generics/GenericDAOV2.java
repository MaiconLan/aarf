package generics;

import org.hibernate.Session;
import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class GenericDAOV2<T, ID extends Serializable> {

    @Inject
    private EntityManager entityManager;

    private Class<T> classeEntidade;

    public GenericDAOV2() {
        this.classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public T findById(ID id) {
        return entityManager.find(classeEntidade, id);
    }

    public void remove(T entidate) {
        entityManager.remove(entidate);
    }

    public void remove(ID id) {
        T entidade = findById(id);
        remove(entidade);
    }

    public List<T> list() {
        Query query = entityManager.createQuery("FROM " + classeEntidade.toGenericString());
        return query.getResultList();
    }

    public void save(T entidade) {
        entityManager.merge(entidade);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }


}
