package ru.vtb.lesson5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vtb.lesson5.controller.dto.*;
import ru.vtb.lesson5.exception.DuplicateException;
import ru.vtb.lesson5.exception.NotFoundException;
import ru.vtb.lesson5.repository.AgreementRepo;
import ru.vtb.lesson5.repository.TppProductRepo;
import ru.vtb.lesson5.repository.TppRefProductClassRepo;
import ru.vtb.lesson5.repository.TppRefProductRegisterTypeRepo;
import ru.vtb.lesson5.repository.entity.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstanceService implements Instanceable{
    private final TppProductRepo tppProductRepo;
    private final AgreementRepo agreementRepo;
    private final TppRefProductClassRepo tppRefProductClassRepo;
    private final TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;
    private final Accountable accountable;
    @Override
    @Transactional
    public InstanceResponse process(InstanceRequest instanceRequest){
        InstanceResponse instanceResponse;
        if (instanceRequest.getInstanceId() == null) {
            instanceResponse = create(instanceRequest);
        } else {
            instanceResponse = edit(instanceRequest);
        }
        return instanceResponse;
    }

    private InstanceResponse create(InstanceRequest instanceRequest) {
        // Шаги 1.1, 1.2.
        checkBeforeCreate(instanceRequest);

        // Шаг 1.3.
        List<TppRefProductClassEntity> tppRefProductClassEntities= tppRefProductClassRepo.findByValue(instanceRequest.getProductCode());
        List<TppRefProductRegisterTypeEntity> tppRefProductRegisterTypeEntities =
                tppRefProductRegisterTypeRepo.findByProductClassCodeIsInAndAccountType(
                        tppRefProductClassEntities.stream().map(TppRefProductClassEntity::getValue).toList(),
                        "Клиентский"
                );
        if (tppRefProductRegisterTypeEntities.isEmpty()) {
            throw new NotFoundException(MessageFormat.format(
                    "КодПродукта {0} не найден в Каталоге продуктов tpp_ref_product_class",
                    instanceRequest.getProductCode()
            ));
        }

        // Шаг 1.4.
        // На деле надо было сделать внутренний DTO, совпадающий с Request по полям, тогда бы в контроллере проводилось
        // перекладывание, а здесь бы конструктор вызывался с DTO на вход. Было бы здесь короче. Но времени не хватает уже.
        // А делать в Entity конструктор с Request на входе считаю не правильным - Request это внешняя структура,
        // которая не должна ходить внутри сервиса и в случае изменения интерфейса (JSON, Request) вызывать переделку всего.
        // Хотя в этом классе так и получилось - внутри него таскаю Request.
        // Поэтому конструктор с кучей полей и длинный код здесь.
        TppProductEntity tppProductEntity = new TppProductEntity(
                null,
                Long.parseLong(instanceRequest.getMdmCode()),
                instanceRequest.getProductType(),
                instanceRequest.getContractNumber(),
                instanceRequest.getPriority().longValue(),
                instanceRequest.getContractDate().atStartOfDay(),
                null,
                null,
                null,
                instanceRequest.getInterestRatePenalty(),
                instanceRequest.getMinimalBalance(),
                instanceRequest.getThresholdAmount(),
                null,
                instanceRequest.getRateType(),
                instanceRequest.getTaxPercentageRate(),
                null,
                "OPEN"
        );

        tppProductRepo.save(tppProductEntity);

        // Шаг 1.5.
        List<String> registerIdList = new ArrayList<>();
        for (TppRefProductRegisterTypeEntity registerType : tppRefProductRegisterTypeEntities) {
            // Обратимся к сервису 1 для создания объектов.
            AccountResponse accountResponse = accountable.create(new AccountRequest(
                    tppProductEntity.getId().longValue(),
                    registerType.getValue(),
                    registerType.getAccountType(),
                    instanceRequest.getIsoCurrencyCode(),
                    instanceRequest.getBranchCode(),
                    "00",
                    instanceRequest.getMdmCode(),
                    null,
                    null,
                    null,
                    null
            ));
            registerIdList.add(accountResponse.getData().getAccountId());
        }

        return new InstanceResponse(tppProductEntity.getId().toString(), registerIdList, null);
    }

    private InstanceResponse edit(InstanceRequest instanceRequest) {
        // Шаги 2.1, 2.2.
        checkBeforeEdit(instanceRequest);

        // Шаг 2.3, он же, видимо, 8. Хорошее ТЗ.
        List<String> agreementIdList = new ArrayList<>();
        for (InstanceArrangementDto instanceArrangement: instanceRequest.getInstanceArrangements()) {
            AgreementEntity agreementEntity = new AgreementEntity(
                    instanceRequest.getInstanceId(),
                    instanceArrangement.getGeneralAgreementId(),
                    instanceArrangement.getSupplementaryAgreementId(),
                    instanceArrangement.getArrangementType(),
                    instanceArrangement.getShedulerJobId().longValue(),
                    instanceArrangement.getNumber(),
                    instanceArrangement.getOpeningDate().atStartOfDay(),
                    instanceArrangement.getClosingDate().atStartOfDay(),
                    instanceArrangement.getCancelDate().atStartOfDay(),
                    instanceArrangement.getValidityDuration().longValue(),
                    instanceArrangement.getCancellationReason(),
                    instanceArrangement.getStatus(),
                    instanceArrangement.getInterestCalculationDate().atStartOfDay(),
                    instanceArrangement.getInterestRate(),
                    instanceArrangement.getCoefficient(),
                    instanceArrangement.getCoefficientAction(),
                    instanceArrangement.getMinimumInterestRate(),
                    instanceArrangement.getMinimumInterestRateCoefficient(),
                    instanceArrangement.getMinimumInterestRateCoefficientAction(),
                    instanceArrangement.getMaximalnterestRate(),
                    instanceArrangement.getMaximalnterestRateCoefficient(),
                    instanceArrangement.getMaximalnterestRateCoefficientAction()
            );

            agreementRepo.save(agreementEntity);

            agreementIdList.add(agreementEntity.getId().toString());
        }

        return new InstanceResponse(instanceRequest.getInstanceId().toString(), null, agreementIdList);
    }

    private void checkBeforeCreate(InstanceRequest instanceRequest) {
        String messageTemplate = "Параметр {0} {1} уже существует для ЭП с ИД {2}.";
        // Шаг 1.1.
        TppProductEntity tppProductEntity = tppProductRepo.findFirstByNumber(instanceRequest.getContractNumber());
        if (tppProductEntity != null) {
            throw new DuplicateException(MessageFormat.format(
                    messageTemplate,
                    "ContractNumber № договора",
                    instanceRequest.getContractNumber(),
                    tppProductEntity.getId()
            ));
        }

        // Шаг 1.2.
        checkAgreementExists(instanceRequest);
    }

    private void checkAgreementExists(InstanceRequest instanceRequest) {
        String messageTemplate = "Параметр {0} {1} уже существует для ЭП с ИД {2}.";
        AgreementEntity agreementEntity = agreementRepo.findFirstByNumberIsIn(
                instanceRequest.getInstanceArrangements().stream()
                        .map(InstanceArrangementDto::getNumber)
                        .toList()
        );

        if (agreementEntity != null) {
            throw new DuplicateException(MessageFormat.format(
                    messageTemplate,
                    "№ Дополнительного соглашения (сделки) Number",
                    agreementEntity.getNumber(),
                    agreementEntity.getId())
            );
        }
    }

    private void checkBeforeEdit(InstanceRequest instanceRequest) {
        // Шаг 2.1.
        if (!tppProductRepo.existsById(instanceRequest.getInstanceId())) {
            throw new NotFoundException(MessageFormat.format(
                    "Экземпляр продукта с параметром instanceId {0} не найден.",
                    instanceRequest.getInstanceId()
            ));
        }

        // Шаг 2.2.
        checkAgreementExists(instanceRequest);
    }
}
