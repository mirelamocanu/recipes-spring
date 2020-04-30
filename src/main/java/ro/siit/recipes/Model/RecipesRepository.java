package ro.siit.recipes.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Long> {
    // List<Recipe> findAllByOrderByDateCreated();


    List<Recipe> findAllByOrderByDateModifiedDesc();

    List<Recipe> findByCategoryOrderByDateModifiedDesc(RecipeCategory category);

}
