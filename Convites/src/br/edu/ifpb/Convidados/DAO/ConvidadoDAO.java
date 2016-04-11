package br.edu.ifpb.Convidados.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.Convidados.entidade.Convidado;
import br.edu.ifpb.Convidados.hibernate.HibernateUtil;

public class ConvidadoDAO extends GenericDao<Integer, Convidado> {

	private static Logger logger = LogManager.getLogger(ConvidadoDAO.class);

	static ConvidadoDAO instance;

	public static ConvidadoDAO getInstance() {

		instance = new ConvidadoDAO();
		return instance;
	}

	@Override
	public List<Convidado> getAll() throws HibernateException {
		// TODO Auto-generated method stub
		return getAll("Monitor.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Convidado.class;
	}

	@Override
	public Convidado find(Convidado entity) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Convidado> getByName(String nome) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Convidado> convidados = new ArrayList<Convidado>();

		try {

			session.beginTransaction();

			Query query = session.createQuery("from Convidado as c where c.nome like :nome");
			query.setParameter("nome", "%" + nome + "%");

			convidados = query.list();
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			session.getTransaction().rollback();

		} finally {

			session.close();
		}

		return convidados;
	}
	
	public List<Convidado> getByCode(String code) {

		List <Convidado> convidados = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			session.beginTransaction();
			Query query = session.createQuery("from Convidado as c where c.qrcode like :code");
			query.setParameter("code", "%" + code + "%");

			convidados = query.list();
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			session.getTransaction().rollback();

		} finally {

			session.close();
		}

		return convidados;
	}
}
