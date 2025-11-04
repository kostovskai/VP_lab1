package mk.ukim.finki.wp.lab.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        Optional<Chef> chefOptional = chefRepository.findById(id);

        if(chefOptional.isPresent()) {
            return chefOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Dish dish = dishRepository.findByDishId(dishId);
        Chef chef = findById(chefId);
        chef.getDishes().add(dish);
        return chefRepository.save(chef);
    }
}