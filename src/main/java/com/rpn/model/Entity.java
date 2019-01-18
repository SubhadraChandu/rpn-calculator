package com.rpn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Entity {
    private BigDecimal number;
    private OperatorType operatorType;
}
