package com.kafein.cms.service;

import com.kafein.cms.constant.ActionType;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.exceptions.KafeinException;
import com.kafein.cms.model.dto.WarehouseDto;
import com.kafein.cms.model.entity.History;
import com.kafein.cms.model.entity.Warehouse;
import com.kafein.cms.mapper.WarehouseMapper;
import com.kafein.cms.repository.HistoryRepository;
import com.kafein.cms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper=WarehouseMapper.INSTANCE;

    private final MessageSource messageSource;

    private final HistoryRepository historyRepository;

    public WarehouseDto updateAndCreate(WarehouseDto warehouseDto) {
        ActionType actionType = null;
        final Optional<List<Warehouse>> warehousesList = warehouseRepository.findByName(warehouseDto.getName());

        if(Objects.isNull(warehouseDto.getId()) && !warehousesList.isPresent()){
            throw new KafeinException(MessageKey.ERR04,messageSource.getMessage(MessageKey.ERR04,null, Locale.ENGLISH));
        }

        if(Objects.nonNull(warehouseDto.getId())){
            actionType = ActionType.UPDATE;
            logger.debug("Depo Guncelleniyor");
        }
        else{
            actionType = ActionType.INSERT;
            logger.debug("Yeni Depo Ekleniyor");
        }
        Warehouse warehouse = warehouseMapper.mapToEntityFromDto(warehouseDto);
        warehouse.setDeleted(Boolean.FALSE);
        warehouse = warehouseRepository.saveAndFlush(warehouse);

        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(actionType);
        history.setRelatedTable(warehouse.getClass().getName().toUpperCase());
        history.setEfectedId(warehouse.getId());
        history.setDescription(warehouse.getId()+" no lu urun icin "+(ActionType.INSERT.equals(actionType)? "ekleme":"guncelleme")+" islemi yapildi");

        historyRepository.saveAndFlush(history);
        return warehouseMapper.mapToDtoFromEntity(warehouse);
    }

    public Boolean delete(Long id) {
        warehouseRepository.deleteById(id);
        logger.debug(id+" no lu depo silindi.");
        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(ActionType.DELETE);
        history.setEfectedId(id);
        history.setRelatedTable("WAREHOUSE");
        history.setDescription(id+" no lu depo icin silme islemi yapildi");
        historyRepository.saveAndFlush(history);
        return true;
    }

    public List<WarehouseDto> getAllWarehouse() {
        return warehouseMapper.mapToDtoFromEntity(warehouseRepository.findAll());
    }
}
