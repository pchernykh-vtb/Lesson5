package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.TppRefProductClassEntity;

import java.util.List;

@Repository
public interface TppRefProductClassRepo extends JpaRepository<TppRefProductClassEntity, Integer> {
    List<TppRefProductClassEntity> findByValue(String value);
}
