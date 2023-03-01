package ru.nikitalubimov.iMarket.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppError {

    private int statusCode;
    private String message;

}
