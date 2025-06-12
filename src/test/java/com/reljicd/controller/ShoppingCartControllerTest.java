package com.reljicd.controller;

import com.reljicd.service.ShoppingCartService;
import com.reljicd.service.ProductService;
import com.reljicd.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {
    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private ShoppingCartController controller;

    @BeforeEach
    public void setUp() {
        shoppingCartService = mock(ShoppingCartService.class);
        productService = mock(ProductService.class);
        controller = new ShoppingCartController(shoppingCartService, productService);
    }

    @Test
    public void testShoppingCartReturnsViewName() {
        when(shoppingCartService.getProductsInCart()).thenReturn(Collections.emptyMap());
        when(shoppingCartService.getTotal()).thenReturn(0.0);

        ModelAndView mav = controller.shoppingCart();

        assertEquals("/shoppingCart", mav.getViewName());
        verify(shoppingCartService).getProductsInCart();
        verify(shoppingCartService).getTotal();
    }
}

