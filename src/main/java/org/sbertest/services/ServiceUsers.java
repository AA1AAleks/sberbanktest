package org.sbertest.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sbertest.entuty.Users;
import org.sbertest.exeptions.InvalidFullNameException;
import org.sbertest.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceUsers implements CrudService<Users> {
    private final UsersRepository usersRepository;

    @Override
    public Collection<Users> getAll() {
        log.info("Получаем все: ");
        return usersRepository.findAll();
    }

    @Override
    public void create(Users users) {
        if (!isValidFullName(users.getFullName())) {
            log.info("Ошибка имени");
            throw new InvalidFullNameException("Incorrect fullName, try again!");
        }
        log.info("Создаем: ");
        usersRepository.save(users);
    }

    @Override
    public void update(Long id, Users users) {
        log.info("Обновляем");

        if (!usersRepository.existsById(id)) {
            return;
        }
        users.setId(id);

        usersRepository.save(users);

    }

    @Override
    public void toggle(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
        users.setActive(!users.isActive());
        usersRepository.save(users);
    }

    // Данный метод сделан как пример и не отражает полной валидации ФИО
    public static boolean isValidFullName(String fullName) {
        return fullName.matches("^[a-zA-Z-а-яА-ЯёЁ\\s]+$");


    }
}
