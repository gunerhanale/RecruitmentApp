/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.services;

import common.constant.Globals;
import common.util.CommonUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import recruitment.entitites.AppUser;
import recruitment.entitites.Appointment;

/**
 *
 * @author ebubekir.gunerhanal
 */
public class AppointmentDbService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public AppointmentDbService() {
        this.emf = Persistence.createEntityManagerFactory("RecruitmentPU");
        this.em = emf.createEntityManager();
    }
    
    public List<Appointment> appStatusModelFetchFilter(Appointment filter) {
        String sql = "SELECT "
                + "t.* "
                + "FROM APPOINTMENT t ";
        
        sql += "WHERE  1=1 ";

        if (!CommonUtil.isEmpty(filter.getAppUserId())) {
            sql += " AND APP_USER_ID = #userId";
        }
        
        if (!CommonUtil.isEmpty(filter.getPurpose())) {
            sql += " AND UPPER(PURPOSE) like #purpose";
        }
        if (!CommonUtil.isEmpty(filter.getStartDate())) {
            sql += " AND to_Char(START_DATE,'" + Globals.DATE_FORMAT_DB + "') >= #startDate";
        }
        if (!CommonUtil.isEmpty(filter.getFinishDate())) {
            sql += " AND to_Char(FINISH_DATE,'" + Globals.DATE_FORMAT_DB + "') <= #finishDate";
        }
        sql += " ORDER BY START_DATE";
        return em.createNativeQuery(sql, Appointment.class)
                .setParameter("userId", filter.getAppUserId())
                .setParameter("purpose", CommonUtil.toDbContainsStr(filter.getPurpose()))
                .setParameter("startDate", CommonUtil.formatDate(filter.getStartDate(), Globals.DATE_FORMAT_DB))
                .setParameter("finishDate", CommonUtil.formatDate(filter.getFinishDate(), Globals.DATE_FORMAT_DB))
                .getResultList();
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
