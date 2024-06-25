package ru.vtb.lesson5.service;

import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vtb.lesson5.exception.NotFoundException;
import ru.vtb.lesson5.repository.TppRefProductRegisterTypeRepo;
import ru.vtb.lesson5.repository.entity.TppRefProductRegisterTypeEntity;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class TppRefProductRegisterTypeService {
    private final TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;

    public TppRefProductRegisterTypeEntity getByRegistryTypeCode(String registryTypeCode) {
        TppRefProductRegisterTypeEntity tppRefProductRegisterType =
                tppRefProductRegisterTypeRepo.findByValue(registryTypeCode);
        if (tppRefProductRegisterType == null) {
            // Не знаю как ещё дотянуться до аннотаций класса без объекта.
            TppRefProductRegisterTypeEntity fake = new TppRefProductRegisterTypeEntity();

            throw new NotFoundException(MessageFormat.format(
                    "Код продукта {0} не найден в Каталоге продуктов {1}.{2} для данного типа Регистра",
                    registryTypeCode,
                    fake.getClass().getAnnotation(Table.class).schema(),
                    fake.getClass().getAnnotation(Table.class).name()
            ));
        }

        return tppRefProductRegisterType;
    }
}
