package com.study.restapi.service;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.response.TodoResponse;
import com.study.restapi.entity.Todo;
import com.study.restapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public TodoResponse create(TodoCreateRequest request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Todo saved = todoRepository.save(todo);
        return TodoResponse.from(saved);
    }
}
