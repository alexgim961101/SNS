package com.example.sns.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> {
    private int code;
    private String message;
    private T data;
}
