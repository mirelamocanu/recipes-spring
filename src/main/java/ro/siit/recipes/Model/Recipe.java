package ro.siit.recipes.Model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = 6, message = "minimum 6 characters")
    private String name;

    @NotEmpty
    @Size(min = 20, message = "minimum 20 characters")
    @Column(length = 5000)
    private String description;

    @NotEmpty
    @Size(min = 20, message = "minimum 20 characters")
    @Column(length = 5000)
    private String ingredients;

    @NotEmpty
    @Size(min = 20, message = "minimum 20 characters")
    @Column(length = 5000)
    private String directions;

    @Enumerated(EnumType.STRING)
    private RecipeCategory category;

    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateModified;

    @NotEmpty
    @Lob
    private byte[] image;

    public Recipe() {
    }

    public Recipe(Long id, String name, String description, String ingredients, String directions, RecipeCategory category, LocalDate created, LocalDate modified, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

//    public void setDateCreated(LocalDate dateCreated) {
//        this.dateCreated = dateCreated;
//    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String getImage() {
        if (image != null) {
            try {
                return new String(Base64.encodeBase64(image), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setImage(MultipartFile image) {
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", category=" + category +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", image=" + Arrays.toString(image) +
                '}';
    }


}
