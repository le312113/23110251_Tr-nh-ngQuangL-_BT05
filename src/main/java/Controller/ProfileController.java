package Controller;

import Model.User;
import Service.UserServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private UserServiceImplement  userServiceImplement = new UserServiceImplement();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action){
            case "list":
                List_profile(req, resp);
                req.getRequestDispatcher("/WEB-INF/profile/list_profile.jsp").forward(req, resp);
                break;
                case "edit":
                    String id = req.getParameter("id");
                    System.out.println(id);
                    req.getRequestDispatcher("/WEB-INF/profile/edit_profile.jsp").forward(req, resp);
                    break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action){
            case "edit":
                Update_profile(request,resp);
                break;
            case "delete":
                Delete_profile(request,resp);
                break;
        }
    }
    private void Delete_profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        userServiceImplement.deleteUser(id);
        resp.sendRedirect(req.getContextPath() + "/profile?action=list");
    }
    private void Update_profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String fullName=req.getParameter("fullName");
        String phone=req.getParameter("phone");
        Part filePart = req.getPart("avatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String avatarPath = null;
        if (fileName != null && !fileName.isEmpty()) {
            String uploadDir = req.getServletContext().getRealPath("/uploads");
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) uploadDirFile.mkdirs();
            filePart.write(uploadDir + File.separator + fileName);
            avatarPath = fileName;
        } else {
            User oldUser = userServiceImplement.getUser(id);
            avatarPath = oldUser.getAvatar();
        }
        User user=new User();
        user.setId(id);
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setAvatar(avatarPath);
        userServiceImplement.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/profile?action=list");
    }
    private void List_profile(HttpServletRequest request, HttpServletResponse resp){
        List<User> list_user=userServiceImplement.getUsers();
        if (list_user.isEmpty()){
            System.out.println("null");
        }
        request.setAttribute("list_user", list_user);
    }
}
