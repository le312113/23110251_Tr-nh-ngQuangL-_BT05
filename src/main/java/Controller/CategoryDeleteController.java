package Controller;

import Service.CategoryServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/delete"})
public class CategoryDeleteController extends HttpServlet {
    private CategoryServiceImplement categoryService=new CategoryServiceImplement();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        int cate_id = Integer.parseInt(req.getParameter("id"));
        categoryService.delete(cate_id);
        req.getRequestDispatcher("/waiting").forward(req, resp);
    }
}
