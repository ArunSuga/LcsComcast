package com.comcast.interview.exception;
import java.util.Date;

public class ExceptionResponse {
  private String message;
  private String details;

  public ExceptionResponse(String message, String details) {
    super();
    this.message = message;
    this.details = details;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

}