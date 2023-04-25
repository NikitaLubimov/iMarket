package ru.nikitalubimov.iMarket.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.api.CartDto;
import ru.nikitalubimov.iMarket.api.StringResponse;
import ru.nikitalubimov.iMarket.carts.converters.CartConverter;
import ru.nikitalubimov.iMarket.carts.services.CartService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String userName,
                                  @PathVariable String uuid) {
        String targetUuid = getCartUuid(userName,uuid);
        return cartConverter.toDto(cartService.getCurrentCart(targetUuid));
    }

    @PostMapping("/{uuid}/add/{id}")
    public void addProductToCart(@RequestHeader(name = "username", required = false) String userName,
                                 @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(userName,uuid);
        cartService.add(targetUuid, id);
    }

    @GetMapping("/{uuid}/changePlus/{id}")
    public void changeQuantityPlus(@RequestHeader(name = "username", required = false) String userName,
                                   @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(userName,uuid);
        cartService.changeQuantityPlus(targetUuid, id);
    }

    @GetMapping("/{uuid}/changeMinus/{id}")
    public void changeQuantityMinus(@RequestHeader(name = "username", required = false) String userName,
                                    @PathVariable String uuid,
                                    @PathVariable Long id) {
        String targetUuid = getCartUuid(userName,uuid);
        cartService.changeQuantityMinus(targetUuid, id);
    }

    @DeleteMapping("/{uuid}/{id}")
    public void delProductCart(@RequestHeader(name = "username", required = false) String userName,
                               @PathVariable String uuid,
                               @PathVariable Long id) {
        String targetUuid = getCartUuid(userName,uuid);
        cartService.deleteItemCart(targetUuid, id);
    }

    @DeleteMapping("/{uuid}/clearCart")
    public void clearCart(@RequestHeader(name = "username", required = false) String userName,
                          @PathVariable String uuid) {
        String targetUuid = getCartUuid(userName,uuid);
        cartService.clearCart(targetUuid);
    }

    private String getCartUuid(String userName, String uuid) {
        if (userName != null) {
            return userName;
        }
        return uuid;
    }
}
