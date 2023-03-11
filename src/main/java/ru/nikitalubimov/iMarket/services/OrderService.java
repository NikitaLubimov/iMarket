package ru.nikitalubimov.iMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitalubimov.iMarket.data.Order;
import ru.nikitalubimov.iMarket.data.OrderItem;
import ru.nikitalubimov.iMarket.data.User;
import ru.nikitalubimov.iMarket.dto.Cart;
import ru.nikitalubimov.iMarket.repositories.OrderItemRepository;
import ru.nikitalubimov.iMarket.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Transactional
    public void createdOrder(User user) {
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);

        List<OrderItem> orderItems = cart.getCartItemList().stream().map(cartItem -> new OrderItem(
                null,
                productService.findById(cartItem.getProductId()).get(),
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now()
        )).collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        order.setItems(orderItems);
        order.setTotalPrice(cart.getTotalPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }
}
