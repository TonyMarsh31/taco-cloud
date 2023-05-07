package tacos.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tacos.Ingredient;
import tacos.Ingredient.Type;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IngredientRepositoryTests {

    @Autowired
    IngredientRepository ingredientRepo;

    @Test
    public void findById() {
        Optional<Ingredient> flto = ingredientRepo.findById("FLTO");
        assertThat(flto.isPresent()).isTrue();
        assertThat(flto.get()).isEqualTo(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));

        Optional<Ingredient> xxxx = ingredientRepo.findById("XXXX");
        assertThat(xxxx.isEmpty()).isTrue();

    }

}
