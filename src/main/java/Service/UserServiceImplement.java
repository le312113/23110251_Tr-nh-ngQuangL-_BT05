package Service;

import Dao.CategoryDaoImplement;
import Dao.UserDao;
import Dao.UserDaoImplement;
import Model.Category;
import Model.User;

import java.util.List;

public class UserServiceImplement implements UserService {
    private final UserDaoImplement userDaoImplement = new UserDaoImplement();
    private CategoryServiceImplement categoryServiceImplement = new CategoryServiceImplement();
    public User getUser(String username){
        User user = userDaoImplement.getUser(username);
        List<Category> categories=categoryServiceImplement.get(user.getId());
        user.setCategories(categories);
        return user;
    }
    public boolean checkExitsUsername(String username){
        return userDaoImplement.checkExitsUsername(username);
    }
    public List<User> getUsers(){
        return userDaoImplement.getUsers();
    }
    public void updateUser(User user){
        userDaoImplement.updateUser(user);
    }
    public User getUser(int id){
        return userDaoImplement.getUser(id);
    }
    public void deleteUser(int id){
        userDaoImplement.deleteUser(id);
    }
}
