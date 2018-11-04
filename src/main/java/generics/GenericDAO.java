package generics;

import jpautil.JPAUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<T> {
	protected static Criteria filtro;
	protected static EntityManager em;
	protected Class<T> clazz;

	/**
	 * @throws IOException
	 * @property Abre conexão
	 */
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		try {
			em = JPAUtil.getEntityManager();
			this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		} catch (PersistenceException e) {
			e.printStackTrace();

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @property Salvar registros
	 */
	/**
	 * @property Salvar registros
	 */
	public void save(T t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		}
	}

	/**
	 * @property Atualizar registros
	 */
	public T update(T t) {
		try {
			em.getTransaction().begin();
			t = em.merge(t);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		}
		return t;
	}

	public void saveOrUpdate(T t) {
		Session session = (Session) em.getDelegate();
		session.saveOrUpdate(t);
	}

	/**
	 * @property Procurar registros pelo ID
	 */
	public T findById(Long id) throws NullPointerException {
		return em.find(clazz, id);
	}

	/**
	 * @property Lista registros
	 */
	@SuppressWarnings("unchecked")
	public List<T> list() {
		Query query = em.createQuery("FROM " + clazz.getSimpleName());
		return query.getResultList();
	}

	/**
	 * @property Remove registros por ID
	 */
	public void remove(Long id) throws NullPointerException, Exception {
		T t = findById(id);
		try {
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public void refresh(T t) {
		em.refresh(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> listaFiltroNome(String pesquisa) throws NullPointerException, Exception {
		String hql = "from " + clazz.getSimpleName() + " c where upper(nom_nome) LIKE :nome ";
		Query query = em.createQuery(hql);
		query.setParameter("nome", "%" + pesquisa.toUpperCase() + "%");

		return query.setMaxResults(100).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listaFiltroDescricao(String pesquisa) throws NullPointerException, Exception {
		String hql = "from " + clazz.getSimpleName() + " c where upper(nom_descricao) LIKE :descricao ";
		Query query = em.createQuery(hql);
		query.setParameter("descricao", "%" + pesquisa.toUpperCase() + "%");

		return query.setMaxResults(100).getResultList();
	}
	
	public String getVersao() {
		String hql = "SELECT description FROM versao_base ORDER BY installed_rank DESC LIMIT 1;";
		Query query = em.createNativeQuery(hql);
		return (String) query.getSingleResult();
	}
	
	public void evict(Object o) {
		Session session = (Session) em.getDelegate();
		session.evict(o);
	}

}
