package com.springBoot.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse <T>{
    private boolean ok;
    private String mensaje;
    private T data;
}
