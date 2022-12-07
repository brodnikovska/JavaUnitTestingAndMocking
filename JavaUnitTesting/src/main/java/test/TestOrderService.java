package test;

import com.epam.tamentoring.bo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestOrderService {
//    @InjectMocks
//    DiscountUtility discountUtilityMock = new DiscountUtility() {
//        @Override
//        public double calculateDiscount(UserAccount userAccount) {
//            return 3.00;
//        }
//    };
//
//    @Mock
//    OrderService orderServiceMock = new OrderService(discountUtilityMock);
    UserAccount johnSmith;
    DiscountUtility discountUtilityMock;


    @BeforeEach
    public void setup() {
        List<Product> shoppingList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(shoppingList);
        johnSmith = new UserAccount("John", "Smith", "1990/10/10", shoppingCart);
        discountUtilityMock = Mockito.mock(DiscountUtility.class);
    }

    @Test
    public void testDiscount() {
        Mockito.when(discountUtilityMock.calculateDiscount(johnSmith)).thenReturn(3.00);

        assertEquals(3, discountUtilityMock.calculateDiscount(johnSmith));
    }

    @Test
    public void testMockedObjectCalledOnce() {
        discountUtilityMock.calculateDiscount(johnSmith);
        verify(discountUtilityMock).calculateDiscount(johnSmith);

        verify(discountUtilityMock, times(1)).calculateDiscount(johnSmith);
    }

    @Test
    public void testNoOtherInteractionsWithMockedObject() {
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        Mockito.when(discountUtilityMock.calculateDiscount(captor.capture())).thenReturn(3.00);

        assertEquals(3.00, discountUtilityMock.calculateDiscount(johnSmith));
        assertEquals(3.00, discountUtilityMock.calculateDiscount(captor.capture()));
        verify(discountUtilityMock, times(2)).calculateDiscount(captor.capture());
    }
}
