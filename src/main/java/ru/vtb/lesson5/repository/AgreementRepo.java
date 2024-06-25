package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.AgreementEntity;

import java.util.List;

@Repository
public interface AgreementRepo extends JpaRepository<AgreementEntity, Integer> {
    AgreementEntity findFirstByNumberIsIn(List<String> numberList);
}
