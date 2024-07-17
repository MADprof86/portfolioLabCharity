package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return  categoryRepository.findAll();
    }

    public Category save(Category category){
        return  categoryRepository.save(category);
    }

    public void deleteById(Long id){
        if(categoryRepository.findById(id).isPresent())
        {
            categoryRepository.deleteById(id);
        }
        else return;
    }
    public Category findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        else return new Category();
    }



}
