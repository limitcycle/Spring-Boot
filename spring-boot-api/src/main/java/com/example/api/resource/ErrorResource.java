package com.example.api.resource;

public class ErrorResource {

  private String message;

  public ErrorResource(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ErrorResource{" +
        "message='" + message + '\'' +
        '}';
  }
}
