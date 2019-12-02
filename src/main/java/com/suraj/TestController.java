package com.suraj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired private BookService bookService;

  @GetMapping("/call")
  public String call() {
    return bookService.readingList();
  }
}
