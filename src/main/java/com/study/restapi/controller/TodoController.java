package com.study.restapi.controller;

import com.study.restapi.dto.request.TodoCreateRequest;
import com.study.restapi.dto.request.TodoUpdateRequest;
import com.study.restapi.dto.response.ApiResponse;
import com.study.restapi.dto.response.TodoResponse;
import com.study.restapi.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 일관성있게 구현하기 위해서 ResponseEntity 객체로 반환하는 것
    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> create(
            @Valid @RequestBody TodoCreateRequest request
    ) {
        TodoResponse response = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> findAll() {
        List<TodoResponse> responses = todoService.findAll();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> findById(
            @PathVariable Long id
    ) {
        TodoResponse response = todoService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        todoService.delete(id);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request
    ) {
        TodoResponse response = todoService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
