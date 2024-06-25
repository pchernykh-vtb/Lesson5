package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.TppRefProductRegisterTypeEntity;

import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepo extends JpaRepository<TppRefProductRegisterTypeEntity, Integer> {
    TppRefProductRegisterTypeEntity findByValue(String value);
    List<TppRefProductRegisterTypeEntity> findByProductClassCodeIsInAndAccountType(List<String> numberList, String accountType);
}
