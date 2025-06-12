package com.reljicd.controller;

import com.reljicd.service.ProductService;
import com.reljicd.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {

    ShoppingCartService shoppingCartService;
    ProductService productService; // 💥 Needed for constructor
    ShoppingCartController controller;

    @BeforeEach
    public void setup() {
        shoppingCartService = Mockito.mock(ShoppingCartService.class);
        productService = Mockito.mock(ProductService.class); // ✅ Mock ProductService too
        controller = new ShoppingCartController(shoppingCartService, productService);
    }

    @Test
    public void testAddCartItem() {
        when(shoppingCartService.getProductsInCart()).thenReturn(Collections.emptyMap());
        when(shoppingCartService.getTotal()).thenReturn(BigDecimal.valueOf(0.0));

        ModelAndView mav = controller.shoppingCart();
        assertEquals("shoppingCart", mav.getViewName());

        verify(shoppingCartService).getProductsInCart();
        verify(shoppingCartService).getTotal();
    }
}
