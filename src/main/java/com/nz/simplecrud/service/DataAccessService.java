package com.nz.simplecrud.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementation of the generic Data Access Service
 * All CRUD (create, read, update, delete) basic data access operations for any 
 * persistent object are performed in this class.
 * Please see this link : 
 * http://www.adam-bien.com/roller/abien/entry/generic_crud_service_aka_dao
 * @author emre.simtay@gmail.com
 */
@Stateless
public class DataAccessService {

    @PersistenceContext
    EntityManager em;

    public DataAccessService() {
    }
    
    /**
     * Stores an instance of the entity class in the database
     * @param <T>
     * @return 
     */
    public <T> T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    /**
     * Retrieves an entity instance that was previously persisted to the database 
     * @param <T>
     * @param id
     * @return 
     */
    public <T> T find(Class<T> type, Object id) {
        return (T) this.em.find(type, id);
    }

    /**
     * Removes the record that is associated with the entity instance
     * @param type
     * @param id 
     */
    public void delete(Class type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    /**
     * Removes the number of entries from a table
     * @param <T>
     * @param items
     * @return 
     */
    public <T> boolean deleteItems(T[] items) {
        for (T item : items) {
            em.remove(em.merge(item));
        }
        return true;
    }

    /**
     * Updates the entity instance
     * @param <T>
     * @param t
     * @return the object that is updated
     */
    public <T> T update(T t) {
        return (T) this.em.merge(t);
    }

    /**
     * Returns the number of records that meet the criteria
     * @param namedQueryName
     * @return List
     */
    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Returns the number of records that meet the criteria
     * @param namedQueryName
     * @param parameters
     * @return List
     */
    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Returns the number of records with result limit
     * @param queryName
     * @param resultLimit
     * @return List
     */
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Returns the number of records that meet the criteria
     * @param <T>
     * @param sql
     * @param type
     * @return List
     */
    public <T> List<T> findByNativeQuery(String sql, Class<T> type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    /**
     * Returns the number of total records
     * @param namedQueryName
     * @return int
     */
    public int countTotalRecord(String namedQueryName) {
        Query query = em.createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    /**
     * Returns the number of records that meet the criteria with parameter map and
     * result limit
     * @param namedQueryName
     * @param parameters
     * @param resultLimit
     * @return List
     */
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
    /**
     * Returns the number of records that will be used with lazy loading / pagination 
     * @param namedQueryName
     * @param start
     * @param end
     * @return List
     */
    public List findWithNamedQuery(String namedQueryName, int start, int end) {
        //Logger.getLogger(DataAccessService.class.getName()).log(Level.INFO, DateUtility.getCurrentDateTime() + " findWithNamedQuery:", namedQueryName);
        Query query = this.em.createNamedQuery(namedQueryName);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }
}