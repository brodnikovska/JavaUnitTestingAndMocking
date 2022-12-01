package test;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestShoppingCart {
    private Product potato;
    private Product carrot;
    private Product meat;

    @BeforeEach
    public void setup() {
       potato = new Product(1, "potato", 1.02, 3);
       carrot = new Product(2, "carrot", 2.00, 1);
       meat = new Product(3, "meat", 5.17, 1.5);
    }

    @Test
    public void testAddProductToCartSmokeHappyPath() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.addProductToCart(meat);

        Assertions.assertEquals(shoppingCart.getProducts().size(), 3);
        Assertions.assertTrue(shoppingCart.getProductById(meat.getId()).equals(meat));
    }

    @Test
    public void testAddProductToCartOneProductAndOneEmptyProduct() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(new Product());
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.addProductToCart(potato);

        Assertions.assertEquals(shoppingCart.getProducts().size(), 2);
        Assertions.assertTrue(shoppingCart.getProductById(potato.getId()).equals(potato));
    }

    @Test
    public void testRemoveProductToCartSmokeHappyPath() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        shoppingList.add(carrot);
        shoppingList.add(meat);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.removeProductFromCart(potato);
        Assertions.assertEquals(shoppingCart.getProducts().size(), 2);
        Assertions.assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(potato.getId()));
    }

    @Test
    public void testRemoveProductToCartOneProductAndOneEmptyProduct() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(new Product());
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.removeProductFromCart(carrot);
        Assertions.assertEquals(shoppingCart.getProducts().size(), 1);
        Assertions.assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(carrot.getId()));
    }

    @Test
    public void testGetCartTotalPriceSmokeHappyPath() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        shoppingList.add(carrot);
        shoppingList.add(meat);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        Assertions.assertEquals(shoppingCart.getCartTotalPrice(), 12.815, 0.0001);
    }

    @Test
    public void testGetCartTotalPriceOneProductAndOneEmptyProduct() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(new Product());
        shoppingList.add(meat);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        Assertions.assertEquals(shoppingCart.getCartTotalPrice(), 7.755, 0.0001);
    }
}
