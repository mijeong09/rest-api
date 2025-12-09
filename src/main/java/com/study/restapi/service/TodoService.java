package com.study.restapi.service;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.request.TodoUpdateRequest;
import com.study.restapi.dto.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse create(TodoCreateRequest request, String username);
    List<TodoResponse> findAll();
    TodoResponse findById(Long id);
    void delete(Long id);
    TodoResponse update(Long id, TodoUpdateRequest request);
}
