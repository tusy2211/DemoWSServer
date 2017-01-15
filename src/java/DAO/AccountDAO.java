/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.*;
import entity.*;
import org.hibernate.*;
import util.NewHibernateUtil;
/**
 *
 * @author khanh
 */
public class AccountDAO {
    private final SessionFactory session = NewHibernateUtil.getSessionFactory();
    
    public Taikhoan login(String username,String password){
        Session s = NewHibernateUtil.getSessionAndBeginTransaction();
        String hql = "SELECT t from Taikhoan t where t.username = :username  AND t.password = :password";
        Query query = s.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Taikhoan> ls = query.list();
        if (ls.size() == 1) {
            return ls.get(0);
        }
        return null;
    }
    
    public List<Account> findAll(){
        try {
            
            session.getCurrentSession().beginTransaction();
            
            return session.getCurrentSession().createCriteria(Account.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                session.getCurrentSession().close();
            } catch (Exception e) {
            }
        }
    }
    
    public Account find(String id){
        SessionFactory session = NewHibernateUtil.getSessionFactory();
        try {
//            session.getCurrentSession().beginTransaction();
//            session.getCurrentSession().close();
            return (Account) session.getCurrentSession().get(Account.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean delete(String id){

        try {
            session.getCurrentSession().beginTransaction();
            session.getCurrentSession().delete(this.find(id));
            session.getCurrentSession().getTransaction().commit();
            session.getCurrentSession().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }
    
    public boolean insert(Account account){
        try {
            session.getCurrentSession().beginTransaction();
            session.getCurrentSession().save(account);
            session.getCurrentSession().getTransaction().commit();
            session.getCurrentSession().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }
    
    public boolean update(String id,String accountnumber,String firstname,String lastname,double balance){
        try {
            session.getCurrentSession().beginTransaction();
            Account account = this.find(id);
            account.setAccountnumber(accountnumber);
            account.setFirstname(firstname);
            account.setLastname(lastname);
            account.setBalance(balance);
            session.getCurrentSession().update(account);
            session.getCurrentSession().getTransaction().commit();
            session.getCurrentSession().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }
}
