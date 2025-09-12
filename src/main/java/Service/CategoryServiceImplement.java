package Service;

import Dao.CategoryDaoImplement;
import Model.Category;

import java.util.List;

public class CategoryServiceImplement implements CategoryService {
    private final CategoryDaoImplement categoryDao = new CategoryDaoImplement();
    public void insert(Category category){
        categoryDao.insert(category);
    }
    public void update(Category category){
        categoryDao.update(category);
    }
    public void delete(int id){
        categoryDao.delete(id);
    }
    public List<Category> get(int userid){
        return categoryDao.get(userid);
    }
    public Category get_cateid(int cate_id){
        return categoryDao.get_cateid(cate_id);
    }
    public List<Category> get_all(){return categoryDao.get_all();}
}
