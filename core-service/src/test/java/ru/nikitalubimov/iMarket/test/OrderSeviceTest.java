package ru.nikitalubimov.iMarket.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.nikitalubimov.iMarket.api.CartDto;
import ru.nikitalubimov.iMarket.api.CartItemDto;
import ru.nikitalubimov.iMarket.data.Order;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.integration.CartServiceIntegration;
import ru.nikitalubimov.iMarket.repositories.OrderItemRepository;
import ru.nikitalubimov.iMarket.repositories.OrderRepository;
import ru.nikitalubimov.iMarket.services.OrderService;
import ru.nikitalubimov.iMarket.services.ProductService;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderSeviceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private OrderItemRepository orderItemRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto1 = new CartItemDto(51L,"milk",2,100,200);
        cartDto.setCartItemList(List.of(cartItemDto1));
        cartDto.setTotalPrice(200);
        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();

        Product product = new Product(51L,"milk",100);
        Mockito.doReturn(Optional.of(product)).when(productService).findById(51L);

        Order order = orderService.createdOrder("bob");
        Assertions.assertEquals(order.getTotalPrice(),200);
    }
}
