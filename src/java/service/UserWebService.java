package service;

import entity.*;
import java.util.*;
import DAO.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author khanh
 */
@WebService(serviceName = "UserWebService")
public class UserWebService {

    private AccountDAO dao = new AccountDAO();
    
    @WebMethod(operationName = "login")
    public Taikhoan login(@WebParam(name = "username") String username,@WebParam(name = "password") String password){
        return dao.login(username, password);
    }
    
    @WebMethod(operationName = "findall")
    public List<Account> findAll() {
        return dao.findAll();
    }
    
    @WebMethod(operationName = "delete")
    public boolean delete(@WebParam(name = "id") String id){
        return dao.delete(id);
    }
    
    @WebMethod(operationName = "insert")
    public boolean insert(@WebParam(name = "account") Account account){
        return dao.insert(account);
    }
    
    @WebMethod(operationName = "update")
    public boolean update(@WebParam(name = "id") String id,@WebParam(name = "accountnumber") String accountnumber,@WebParam(name = "firstname") String firstname,@WebParam(name = "lastname") String lastname,@WebParam(name = "balance") double balance ){
        return dao.update(id, accountnumber, firstname, lastname, balance);
    }
}
