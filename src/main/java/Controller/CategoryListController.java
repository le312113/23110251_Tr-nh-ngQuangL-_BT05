package Controller;

import Model.Category;
import Model.User;
import Service.CategoryServiceImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/list"})
public class CategoryListController extends HttpServlet {
    private CategoryServiceImplement categoryServiceImplement = new CategoryServiceImplement();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userid =Integer.parseInt(req.getParameter("id"));
        List<Category> categoryList;
        if (userid==1 ||userid==3) {
            categoryList=categoryServiceImplement.get_all();
        }else {
            categoryList=categoryServiceImplement.get(userid);
        }
        req.setAttribute("cateList", categoryList);
        System.out.println("đã chuyển đến trang list");
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/category/list_category.jsp");
        dispatcher.forward(req, resp);
    }
}
