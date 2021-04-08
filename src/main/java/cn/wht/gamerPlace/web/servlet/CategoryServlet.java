package cn.wht.gamerPlace.web.servlet;

import cn.wht.gamerPlace.domain.Category;
import cn.wht.gamerPlace.service.CategoryService;
import cn.wht.gamerPlace.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @ClassName ${NAME}
 * @Author qgq
 * @Date 2020/12/24 22:31
 * @Version V1.0
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    //申明categoryService
    private CategoryService service =  new CategoryServiceImpl();
    /**
     * 查询所有种类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Category> cs = service.findAll();
        //2.以json返回
        writeValue(cs,response);
    }


}
