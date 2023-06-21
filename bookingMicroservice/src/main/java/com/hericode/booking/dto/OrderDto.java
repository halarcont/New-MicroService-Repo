package com.hericode.booking.dto;

import com.hericode.booking.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private List<OrderItem> orderItem;
}
