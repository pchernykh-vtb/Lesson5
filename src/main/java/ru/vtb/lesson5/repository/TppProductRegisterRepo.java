package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.TppProductRegisterEntity;

import java.util.List;

@Repository
public interface TppProductRegisterRepo extends JpaRepository<TppProductRegisterEntity, Integer> {
    List<TppProductRegisterEntity> findByProductId(long productId);
}
