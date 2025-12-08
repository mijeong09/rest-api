package com.study.restapi.service;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.request.TodoUpdateRequest;
import com.study.restapi.dto.response.TodoResponse;
import com.study.restapi.entity.Todo;
import com.study.restapi.exception.CustomException;
import com.study.restapi.exception.ErrorCode;
import com.study.restapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResponse::from)
                .toList();
    }

    @Override
    public TodoResponse findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));
        return TodoResponse.from(todo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new CustomException(ErrorCode.TODO_NOT_FOUND);
        }
        todoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TodoResponse update(Long id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todo.update(request.getTitle(), request.getContent());
        return TodoResponse.from(todo);
    }
}
