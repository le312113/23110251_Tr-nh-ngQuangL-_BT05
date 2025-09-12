package Dao;

import Model.Category;

import java.util.List;

public interface CategoryDao {
    void insert(Category category);
    void update(Category category);
    void delete(int id);
    List<Category> get(int userid);
    Category get_cateid(int cate_id);
    List<Category> get_all();
}
