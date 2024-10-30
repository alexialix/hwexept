package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        repo.add(product1);
        repo.add(product2);

        repo.remove(1);

        Product[] expected = {product2};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistentProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        repo.add(product1);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(2); // Удаление несуществующего продукта
        });

        Assertions.assertEquals("Element with id: 2 not found.", exception.getMessage());
    }
}
