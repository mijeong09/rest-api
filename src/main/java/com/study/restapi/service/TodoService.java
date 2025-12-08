package com.study.restapi.service;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse create(TodoCreateRequest request);
    List<TodoResponse> findAll();
    TodoResponse findById(Long id);
}
