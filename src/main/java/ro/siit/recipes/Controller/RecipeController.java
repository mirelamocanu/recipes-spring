package ro.siit.recipes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.recipes.Model.Recipe;
import ro.siit.recipes.Model.RecipeCategory;
import ro.siit.recipes.Model.RecipesRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class RecipeController {
    @Autowired
    private RecipesRepository repo;

    @GetMapping(path = "home")
    public ModelAndView getRecipes() {
        List<Recipe> recipesList = repo.findAllByOrderByDateModifiedDesc();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("all_recipes", recipesList);
        return modelAndView;
    }

    @GetMapping(path = "home/page/{page}")
    public ModelAndView listRecipesPageByPage(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        PageRequest pageable = PageRequest.of(page - 1, 3);
        Page<Recipe> recipePage = repo.findAll(pageable);
        int totalPages = recipePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("all_recipes", recipePage.getContent());
        return modelAndView;
    }


    @GetMapping(path = "soup")
    public ModelAndView getSoupRecipes() {
        List<Recipe> recipesList = repo.findByCategoryOrderByDateModifiedDesc(RecipeCategory.SOUP);
        ModelAndView modelAndView = new ModelAndView("soup");
        modelAndView.addObject("soup_recipes", recipesList);
        return modelAndView;
    }

    @GetMapping(path = "maindish")
    public ModelAndView getMainDishRecipes() {
        List<Recipe> recipesList = repo.findByCategoryOrderByDateModifiedDesc(RecipeCategory.MAIN_DISH);
        ModelAndView modelAndView = new ModelAndView("maindish");
        modelAndView.addObject("maindish_recipes", recipesList);
        return modelAndView;
    }
    @GetMapping(path = "salad")
    public ModelAndView getSaladRecipes() {
        List<Recipe> recipesList = repo.findByCategoryOrderByDateModifiedDesc(RecipeCategory.SALAD);
        ModelAndView modelAndView = new ModelAndView("salad");
        modelAndView.addObject("salad_recipes", recipesList);
        return modelAndView;
    }
    @GetMapping(path = "dessert")
    public ModelAndView getDessertRecipes() {
        List<Recipe> recipesList = repo.findByCategoryOrderByDateModifiedDesc(RecipeCategory.DESSERT);
        ModelAndView modelAndView = new ModelAndView("dessert");
        modelAndView.addObject("dessert_recipes", recipesList);
        return modelAndView;
    }
    @GetMapping(path = "miscellaneous")
    public ModelAndView getMiscellaneousRecipes() {
        List<Recipe> recipesList = repo.findByCategoryOrderByDateModifiedDesc(RecipeCategory.MISCELLANEOUS);
        ModelAndView modelAndView = new ModelAndView("miscellaneous");
        modelAndView.addObject("miscellaneous_recipes", recipesList);
        return modelAndView;
    }

    @GetMapping(path = "aboutme")
    public ModelAndView getAboutMe() {
        return new ModelAndView("aboutme");
    }


    @GetMapping(path = "add")
    public ModelAndView showAddRecipe() {
        ModelAndView modelAndView = new ModelAndView("add");
        modelAndView.addObject("addRecipe", new Recipe());
        return modelAndView;
    }

    @PostMapping(path = "add")
    public String recipeSubmit(@ModelAttribute(name = "addRecipe") @Valid Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        repo.save(recipe);
        return "redirect:/home";
    }


    @GetMapping("/details/{id}")
    public ModelAndView getRecipeDetails(@PathVariable("id") Long id) {
        Recipe recipe = repo.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("recipeDetails", recipe);
        return modelAndView;
    }

    @GetMapping(path = "/edit/{id}")
    public ModelAndView showEditRecipe(@PathVariable("id") Long id) {
        Recipe recipe = repo.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("editRecipe", recipe);
        return modelAndView;
    }

    @PostMapping(path = "/edit/{id}")
    public String editRecipe(@ModelAttribute(name = "editRecipe") @Valid Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            recipe.setId(recipe.getId());
            return "/edit/{id}";
        }
        repo.save(recipe);
        return "redirect:/home";
    }

    @PostMapping("/details/{id}")
    public String deleteRecipeById(@RequestParam("id") Long id) {
        repo.deleteById(id);
        return "redirect:/home";
    }
}
