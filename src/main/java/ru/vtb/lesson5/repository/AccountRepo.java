package ru.vtb.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.lesson5.repository.entity.AccountEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
    @Query(nativeQuery = true, value = "select ac.* from account_pool ap, account ac"
            + " where ac.account_pool_id =ap.id"
            + " and ap.branch_code = ?1 and ap.currency_code = ?2 and ap.mdm_code = ?3"
            + " and ap.priority_code = ?4 and ap.registry_type_code = ?5"
            + " limit 1")
    AccountEntity findFirstFromPool(String branchCode, String currencyCode,String mdmCode, String priorityCode, String registryTypeCode);
}
