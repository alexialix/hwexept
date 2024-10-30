package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    private ShopRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ShopRepository();
    }

    @Test
    public void shouldAddProductSuccessfully() {
        Product product = new Product(1, "Product 1", 100);
        repository.add(product);

        Product[] expected = {product};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenAddingDuplicateId() {
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(1, "Product 2", 200);

        repository.add(product1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product2);
        });
    }

    @Test
    public void shouldRemoveProductSuccessfully() {
        Product product = new Product(1, "Product 1", 100);
        repository.add(product);
        repository.remove(1);

        Product[] expected = {};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistentProduct() {
        Product product = new Product(1, "Product 1", 100);
        repository.add(product);

        repository.remove(1); // First, remove the existing product

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(1); // Try to remove again
        });
    }

    @Test
    public void shouldFindProductByIdSuccessfully() {
        Product product = new Product(1, "Product 1", 100);
        repository.add(product);

        Product foundProduct = repository.findById(1);

        Assertions.assertEquals(product, foundProduct);
    }

    @Test
    public void shouldReturnNullWhenFindingNonExistentProduct() {
        Product foundProduct = repository.findById(1);

        Assertions.assertNull(foundProduct);
    }
}
