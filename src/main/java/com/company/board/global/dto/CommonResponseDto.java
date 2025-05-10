package com.company.board.global.dto;

import com.company.board.global.exception.BaseErrorCode;
import com.company.board.global.exception.CommonErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponseDto<T> {

    private String code;
    private String message;
    private T data;

    public static <T> CommonResponseDto<T> success(T data) {
        return CommonResponseDto.<T>builder()
                .code(CommonErrorCode.SUCCESS.getCode())
                .message(CommonErrorCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> CommonResponseDto<T> fail(BaseErrorCode errorCode) {
        return CommonResponseDto.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}