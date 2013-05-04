package org.meta.gwtworld.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.meta.gwtworld.client.DataService;
import org.meta.gwtworld.client.model.ListAndDefault;
import org.meta.gwtworld.client.model.TbIdPerson;
import org.meta.gwtworld.client.model.TbWkWork;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements
		DataService {

	public <T> List<T> getAll(Class<T> cls) throws IllegalArgumentException {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("gwtworld");
		EntityManager em=factory.createEntityManager();
		CriteriaBuilder qb=em.getCriteriaBuilder();
		CriteriaQuery<T> c=qb.createQuery(cls);
		//Root<TbIdPerson> p=c.from(TbIdPerson.class);
		//Predicate condition=qb.equal(p.get("firstname"), "Mark");
		//c.where(condition);
		TypedQuery<T> tq=em.createQuery(c);
		return tq.getResultList();
	}
	public <T> T getDefault(Class<T> cls) {
		if(cls==TbIdPerson.class) {
			EntityManagerFactory factory=Persistence.createEntityManagerFactory("gwtworld");
			EntityManager em=factory.createEntityManager();
			CriteriaBuilder qb=em.getCriteriaBuilder();
			CriteriaQuery<T> c=qb.createQuery(cls);
			Root<T> p=c.from(cls);
			Predicate condition1=qb.equal(p.get("firstname"), "Mark");
			Predicate condition2=qb.equal(p.get("surname"), "Veltzer");
			c.where(condition1,condition2);
			TypedQuery<T> tq=em.createQuery(c);
			List<T> l=tq.getResultList();
			assert(l.size()==1);
			return l.get(0);
		} else {
			return null;
		}
	}
	@Override
	public ListAndDefault<TbIdPerson> getTbIdPerson() {
		ListAndDefault<TbIdPerson> res=new ListAndDefault<TbIdPerson>(getAll(TbIdPerson.class),getDefault(TbIdPerson.class));
		return res;
	}
	@Override
	public ListAndDefault<TbWkWork> getTbWkWork() {
		ListAndDefault<TbWkWork> res=new ListAndDefault<TbWkWork>(getAll(TbWkWork.class),getDefault(TbWkWork.class));
		return res;
	}
	/* fake getAll to not connect to the database
	@Override
	public List<TbIdPerson> getAll() throws IllegalArgumentException {
		List<TbIdPerson> ret=new ArrayList<TbIdPerson>();
		TbIdPerson newp=new TbIdPerson();
		newp.setFirstname("Mark");
		newp.setSurname("veltzer");
		ret.add(newp);
		return ret;
	}
	*/
	/*
	public List<TbIdPerson> getAllMarks() throws IllegalArgumentException {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("gwtworld");
		EntityManager em=factory.createEntityManager();
		CriteriaBuilder qb=em.getCriteriaBuilder();
		CriteriaQuery<TbIdPerson> c=qb.createQuery(TbIdPerson.class);
		Root<TbIdPerson> p=c.from(TbIdPerson.class);
		Predicate condition=qb.equal(p.get("firstname"), "Mark");
		c.where(condition);
		TypedQuery<TbIdPerson> tq=em.createQuery(c);
		return tq.getResultList();
	}
	*/
	/*
	@SuppressWarnings("unchecked")
	public List<TbIdPerson> getAllPersonsQuery() throws IllegalArgumentException {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("gwtworld");
		EntityManager em=factory.createEntityManager();
		Query q=em.createQuery("select t from TbIdPerson t");
		return q.getResultList();
	}
	*/
	public static void main(String[] args) {
		DataServiceImpl dsi=new DataServiceImpl();
		List<TbIdPerson> all=dsi.getAll(TbIdPerson.class);
		for(TbIdPerson i:all) {
			System.out.println(i);
		}
		System.out.println("Size: "+all.size());
		System.out.print(dsi.getDefault(TbIdPerson.class));
	}
}
