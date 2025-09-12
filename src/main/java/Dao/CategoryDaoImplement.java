package Dao;
import Configs.JpaConfig;
import Model.Category;
import Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImplement implements CategoryDao{
    private EntityManager em;
    @Override
    public void insert(Category category) {
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);             // insert
            tx.commit();                      // sau commit, category đã có ID (nếu @GeneratedValue)
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;                          // hoặc return null tuỳ bạn
        } finally {
            em.close();
        }
    }
    @Override
    public void update(Category category){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "UPDATE Category c SET c.cate_name = :name, c.icons = :icons WHERE c.id = :id";
            int rows = em.createQuery(jpql)
                    .setParameter("name", category.getCate_name())
                    .setParameter("icons", category.getIcons())
                    .setParameter("id", category.getCate_id())
                    .executeUpdate();
            tx.commit();
            System.out.println("Updated rows = " + rows);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Override
    public void delete(int id){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "DELETE FROM Category c WHERE c.id = :id";
            int rows=em.createQuery(jpql)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
        }catch(Exception e){
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Override
    public List<Category> get(int userId){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT c FROM Category c WHERE c.user.id = :userId";
            List<Category> result = em.createQuery(jpql, Category.class).setParameter("userId", userId).getResultList();
            if(result.isEmpty()){
                return null;
            }
            return result;
        }finally{
            em.close();
        }
    }
    @Override
    public Category get_cateid(int cate_id){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT c FROM Category c";
            List<Category> result = em.createQuery(jpql, Category.class).getResultList();
            if(result.isEmpty()){
                return null;
            }
            return result.get(0);
        }finally{
            em.close();
        }
    }
    @Override
    public List<Category> get_all(){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT c FROM Category c";
            List<Category> result = em.createQuery(jpql, Category.class).getResultList();
            if(result.isEmpty()){
                return null;
            }
            return result;
        }finally{
            em.close();
        }
    }
}
