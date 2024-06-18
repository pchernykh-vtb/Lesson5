package ru.vtb.lesson5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vtb.lesson5.controller.dto.AccountRequest;
import ru.vtb.lesson5.controller.dto.AccountResponse;
import ru.vtb.lesson5.exception.DuplicateException;
import ru.vtb.lesson5.model.TppProductRegistryStateEnum;
import ru.vtb.lesson5.repository.AccountRepo;
import ru.vtb.lesson5.repository.TppProductRegisterRepo;
import ru.vtb.lesson5.repository.entity.AccountEntity;
import ru.vtb.lesson5.repository.entity.TppProductRegisterEntity;
import ru.vtb.lesson5.repository.entity.TppRefProductRegisterTypeEntity;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements Accountable{
    private final TppProductRegisterRepo tppProductRegisterRepo;
    private final TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    private final AccountRepo accountRepo;

    @Override
    @Transactional
    public AccountResponse create(AccountRequest accountRequest) {
        // Шаг 2.
        checkBeforeCreate(accountRequest.getInstanceId(), accountRequest.getAccountType());

        // Шаг 3.
        TppRefProductRegisterTypeEntity tppRefProductRegisterTypeEntity = tppRefProductRegisterTypeService.getByRegistryTypeCode(accountRequest.getRegistryTypeCode());

        // Шаг 4.
        AccountEntity accountEntity = accountRepo.findFirstFromPool(
                accountRequest.getBranchCode(),
                accountRequest.getCurrencyCode(),
                accountRequest.getMdmCode(),
                accountRequest.getPriorityCode(),
                accountRequest.getRegistryTypeCode()
        );

        TppProductRegisterEntity tppProductRegisterEntity = new TppProductRegisterEntity(
                accountRequest.getInstanceId(),
                tppRefProductRegisterTypeEntity.getValue(),
                (long)accountEntity.getId(),
                accountRequest.getCurrencyCode(),
                TppProductRegistryStateEnum.OPEN,
                accountEntity.getAccountNumber()
        );

        tppProductRegisterRepo.save(tppProductRegisterEntity);

        return new AccountResponse(tppProductRegisterEntity.getId().toString());
    }

    private void checkBeforeCreate(long productId, String type) {
        // Шаг 2.
        List<TppProductRegisterEntity> productRegisters = tppProductRegisterRepo.findByProductId(productId);
        if (productRegisters.stream().anyMatch(x -> x.getType().equals(type))) {
            throw new DuplicateException(MessageFormat.format(
                    "Параметр registryTypeCode тип регистра {0} уже существует для ЭП с ИД {1}.",
                    type,
                    productId
            ));
        }
    }

}
