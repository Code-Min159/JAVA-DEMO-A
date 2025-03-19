package com.lesson.demo.dto;

import lombok.Data;
@Data


public class ApiResponse<T> {//<T>把決定權交給實作的部分
        private String HttpStatusCode;
        private org.springframework.http.HttpStatusCode status;
        private String message;
        private T data;
}
