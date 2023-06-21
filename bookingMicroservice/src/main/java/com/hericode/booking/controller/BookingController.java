package com.hericode.booking.controller;

import com.hericode.booking.client.StockClient;
import com.hericode.booking.dto.OrderDto;
import com.hericode.booking.entity.Order;
import com.hericode.booking.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDto orderDto) {

        boolean inStock = orderDto.getOrderItem().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if (inStock) {

            Order order = new Order();

            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderItems(orderDto.getOrderItem());

            orderRepository.save(order);

            return "Order saved";
        }
        return "Order cannot be saved";
    }

    private String fallbackToStockService(){
        return "something went wrong, please try after sometime";
    }
}
