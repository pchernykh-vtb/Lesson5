package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.TppProductEntity;

@Repository
public interface TppProductRepo extends JpaRepository<TppProductEntity, Integer> {
    TppProductEntity findFirstByNumber(String number);
}
