package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
@RequiredArgsConstructor
public class ChefDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        Long chefId = Long.valueOf(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");

        Chef chef = chefService.addDishToChef(chefId, dishId);
        context.setVariable("chef", chef);

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}