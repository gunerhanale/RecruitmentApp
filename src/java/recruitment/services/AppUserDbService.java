/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import recruitment.entitites.AppUser;

/**
 *
 * @author ebubekir.gunerhanal
 */
public class AppUserDbService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public AppUserDbService() {
        this.emf = Persistence.createEntityManagerFactory("RecruitmentPU");
        this.em = emf.createEntityManager();
    }

    public AppUser fetchUSer(BigInteger id) {
        return em.find(AppUser.class, id);
    }

    public AppUser fetchUSer(String userName) {
        AppUser record = null;
        try {
            record = em.createNamedQuery("AppUser.findByUserName", AppUser.class).
                    setParameter("userName", userName).
                    getSingleResult();

        } catch (Exception e) {
            
        }
        return record;
    }

    public <T> void edit(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public List findAll(Class c) {
        return em.createNamedQuery(c.getSimpleName() + ".findAll", c).getResultList();
    }

    public <T> T find(Class<T> c, Object id) {
        try {
            return em.find(c, id);
        } catch (Throwable e) {
            return null;
        }
    }

    public <T> void remove(T entity) {
        em.getTransaction().begin();
        em.remove(em.merge(entity));
        em.getTransaction().commit();
        em.close();
    }

    public BigDecimal getSequenceValue(String sequenceName) {
        String sql = "select " + sequenceName + ".nextval from dual";
        BigDecimal id = (BigDecimal) em.createNativeQuery(sql).getSingleResult();
        return id;
    }

    public BigInteger getSequenceBigIntValue(String sequenceName) {
        BigDecimal id = getSequenceValue(sequenceName);
        return id.toBigInteger();
    }

}
