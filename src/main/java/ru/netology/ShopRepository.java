package ru.netology;

import java.util.Objects;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        // Проверяем, существует ли продукт с таким ID
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Product with id: " + product.getId() + " already exists.");
        }
        products = addToArray(products, product);
    }

    /**
     * Метод поиска товара по ID
     *
     * @param id — ID искомого товара
     * @return — найденный товар или null, если товар не найден
     */
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product[] findAll() {
        return products;
    }

    /**
     * Метод удаления товара по ID
     *
     * @param id — ID товара, который нужно удалить
     */
    public void remove(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Element with id: " + id + " not found.");
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product currentProduct : products) {
            if (currentProduct.getId() != id) {
                tmp[copyToIndex] = currentProduct;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}
