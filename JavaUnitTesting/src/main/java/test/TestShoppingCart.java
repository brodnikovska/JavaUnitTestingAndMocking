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
    public void testAddProductToCartProductNotFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.addProductToCart(meat);

        Assertions.assertEquals(2, shoppingCart.getProducts().size());
        Assertions.assertEquals(1.5, shoppingCart.getProductById(meat.getId()).getQuantity());
    }

    @Test
    public void testAddProductToCartTwoExistingProductIsFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(meat);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.addProductToCart(meat);

        Assertions.assertEquals(1, shoppingCart.getProducts().size());
        Assertions.assertEquals(3, shoppingCart.getProductById(meat.getId()).getQuantity());
    }

    @Test
    public void testRemoveProductFromCartProductNotFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        try {
            shoppingCart.removeProductFromCart(meat);
        } catch (ProductNotFoundException exception) {
            Assertions.assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(meat.getId()));
        }
        Assertions.assertEquals(2, shoppingCart.getProducts().size());

    }

    @Test
    public void testRemoveProductFromCartProductIsFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(new Product());
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        shoppingCart.removeProductFromCart(carrot);
        Assertions.assertEquals(1, shoppingCart.getProducts().size());
        Assertions.assertEquals(0, shoppingCart.getCartTotalPrice());
    }

    @Test
    public void testGetProductByIdProductNotFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(meat);
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        Assertions.assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(potato.getId()));
    }

    @Test
    public void testGetProductByIdProductIsFound() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(meat);
        shoppingList.add(carrot);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        Assertions.assertEquals(meat, shoppingCart.getProductById(3));
    }

    @Test
    public void testGetCartTotalPrice() {
        List<Product> shoppingList = new ArrayList<>();
        shoppingList.add(potato);
        shoppingList.add(carrot);
        shoppingList.add(new Product());
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);

        Assertions.assertEquals(5.06, shoppingCart.getCartTotalPrice(), 0.0001);
    }
}
