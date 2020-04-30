package ro.siit.recipes.Model;

public enum RecipeCategory {
    SOUP("Soup"),
    MAIN_DISH ("Main Dish"),
    SALAD ("Salad"),
    DESSERT("Dessert"),
    MISCELLANEOUS ("Miscellaneous");

    private final String displayValue;

    RecipeCategory(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
