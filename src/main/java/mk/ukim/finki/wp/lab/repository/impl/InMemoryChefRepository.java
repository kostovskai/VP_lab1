package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {
    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        for(Chef chef : DataHolder.chefs){
            if(chef.getId().equals(id)){
                return Optional.of(chef);
            }
        }
        return Optional.empty();
    }

    @Override
    public Chef save(Chef chef) {
        Optional<Chef> optionalChef = findById(chef.getId());

        if(optionalChef.isPresent()) {
            Chef entity = optionalChef.get();
            entity.setFirstName(chef.getFirstName());
            entity.setLastName(chef.getLastName());
            entity.setBio(chef.getBio());
            entity.setDishes(chef.getDishes());
            return entity;
        } else {
            DataHolder.chefs.add(chef);
            return chef;
        }
    }
}