package com.study.restapi.service;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.response.TodoResponse;

public interface TodoService {
    TodoResponse create(TodoCreateRequest request);
}
