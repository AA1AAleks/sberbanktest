package org.sbertest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sbertest.entuty.Users;
import org.sbertest.services.ServiceUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "User API version 1")
public class UsersController {
    private ServiceUsers serviceUsers;

    public UsersController(ServiceUsers serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    @Operation(summary = "Get all user",
            description = "This method checks whether we have added a user or not " +
                    "(этот метод проверяет пользователей в базе данных)")
    @GetMapping
    public Collection<Users> getAll() {
        return serviceUsers.getAll();
    }

    @Operation(summary = "Create user",
            description = "Creates a user if his name and email are verified " +
                    "(создаем если проходит валидацию)",
            tags = {"users", "id"})
    @ApiResponse(responseCode = "201", description = "If the check went well we get code 201 create" +
            "(если проверка прошла создаем и выводим код 201)")
    @ApiResponse(responseCode = "400", description = "If the check fails we get code 400" +
            "(если не прошли проверку видим ошибку)")
    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users users) {
        serviceUsers.create(users);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Operation(summary = "Update user",
            description = "We update the user if his id is found in database name and email are verified " +
                    "(обновляем если id найден)",
            tags = {"users", "id"}
    )
    @ApiResponse(responseCode = "200", description = "If Id is found and name, " +
            "email verification is passed, output code 200" +
            " (если проверка прошла обновляем и выводим код 200)")
    @ApiResponse(responseCode = "400", description = "If the check fails we get code 400" +
            " (если не прошли проверку видим ошибку)")
    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users users) {
        serviceUsers.update(id, users);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Change flag (false -> true)",
            description = "Check the user activity in the database, change it with false -> true" +
                    "(проверяем в базе пользователя и если значение активности 0 = false," +
                    " данным методом переключаем на 1 = true)")
    @PatchMapping("/{id}/toggle")
    @ResponseStatus(HttpStatus.OK)
    public void toggleUser(@PathVariable Long id) {
        serviceUsers.toggle(id);

    }

}
