package com.reljicd.controller;

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

    private ShoppingCartService shoppingCartService;
    private ShoppingCartController controller;

    @BeforeEach
    public void setup() {
        shoppingCartService = Mockito.mock(ShoppingCartService.class);
        controller = new ShoppingCartController(shoppingCartService);
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

