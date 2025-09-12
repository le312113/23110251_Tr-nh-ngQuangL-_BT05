package Dao;

import Configs.JpaConfig;
import Model.Category;
import Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class UserDaoImplement implements UserDao {
    private EntityManager em;
    @Override
    public User getUser(String username){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT u FROM User u WHERE u.userName = :userName";
            List<User> result = em.createQuery(jpql, User.class).setParameter("userName", username).getResultList();
            if(result.isEmpty()){
                return null;
            }
            return result.get(0);
        }finally{
            em.close();
        }
    }
    @Override
    public boolean checkExitsUsername(String username){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.userName = :userName";
            Long result = em.createQuery(jpql, Long.class).setParameter("userName", username).getSingleResult();
            if (result>0){
                return true;
            }
            return false;
        }finally{
            em.close();
        }
    }
    @Override
    public List<User> getUsers() {
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT u FROM User u ";
            List<User> result = em.createQuery(jpql, User.class).getResultList();
            if(result.isEmpty()){
                return null;
            }
            return result;
        } finally{
            em.close();
        }
    }
    @Override
    public void updateUser(User user){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql="UPDATE User u SET u.fullName = :fullName, u.phone = :phone, u.avatar = :avatar WHERE u.id = :id";
            int rows = em.createQuery(jpql)
                    .setParameter("id", user.getId())
                    .setParameter("fullName", user.getFullName())
                    .setParameter("phone", user.getPhone())
                    .setParameter("avatar", user.getAvatar())
                    .executeUpdate();
            tx.commit();
            System.out.println("Updated rows = " + rows);
        }finally{
            em.close();
        }
    }
    @Override
    public User getUser(int id){
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            String jpql = "SELECT u FROM User u WHERE u.id = :id";
            User result = em.createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
            return result;
        } finally{
            em.close();
        }
    }
    @Override
    public void deleteUser(int id) {
        EntityManager em = JpaConfig.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "DELETE FROM User u WHERE u.id = :id";
            int rows = em.createQuery(jpql)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit(); // ✅ phải commit mới xóa thật
            System.out.println("Deleted rows = " + rows);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
