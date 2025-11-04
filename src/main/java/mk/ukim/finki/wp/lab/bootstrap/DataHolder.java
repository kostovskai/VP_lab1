package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void initializeData() {
        for (int i = 0; i < 5; i++) {
            Dish dish = new Dish(Integer.toString(i),
                    "DishName" + i,
                    "Cuisine" + i,
                    i);
            dishes.add(dish);

            Chef chef = new Chef((long) i,
                    "FirstName" + i,
                    "LastName" + i,
                    "Bio" + i,
                    new ArrayList<>());
            chefs.add(chef);
        }
    }
}