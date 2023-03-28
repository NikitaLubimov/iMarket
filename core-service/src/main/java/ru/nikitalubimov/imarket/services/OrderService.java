package ru.nikitalubimov.iMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitalubimov.iMarket.api.CartDto;
import ru.nikitalubimov.iMarket.api.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.data.Order;
import ru.nikitalubimov.iMarket.data.OrderItem;
import ru.nikitalubimov.iMarket.integration.CartServiceIntegration;
import ru.nikitalubimov.iMarket.repositories.OrderItemRepository;
import ru.nikitalubimov.iMarket.repositories.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void createdOrder(String userName) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUserName(userName);
        orderRepository.save(order);

        List<OrderItem> orderItems = cartDto.getCartItemList().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product not found (method: createdOrder)")),
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        )).collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        order.setItems(orderItems);
        order.setTotalPrice(cartDto.getTotalPrice());
        orderRepository.save(order);
    }
}
