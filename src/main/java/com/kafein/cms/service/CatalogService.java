package com.kafein.cms.service;

import com.kafein.cms.constant.ActionType;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.exceptions.KafeinException;
import com.kafein.cms.model.dto.CatalogDto;
import com.kafein.cms.model.entity.Catalog;
import com.kafein.cms.mapper.CatalogMapper;
import com.kafein.cms.model.entity.History;
import com.kafein.cms.repository.CatalogRepository;
import com.kafein.cms.repository.HistoryRepository;
import com.kafein.cms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
    private final CatalogRepository catalogRepository;

    private final CatalogMapper catalogMapper=CatalogMapper.INSTANCE;

    private final MessageSource messageSource;
    private final ProductRepository productRepository;

    private final HistoryRepository historyRepository;

    @Transactional
    public CatalogDto updateAndCreate(CatalogDto catalogDto) {
        ActionType actionType = null;
        final Optional<List<Catalog>> catalogList = catalogRepository.findByName(catalogDto.getName());

        if(Objects.isNull(catalogDto.getId()) && !catalogList.isPresent()){
            throw new KafeinException(MessageKey.ERR03,messageSource.getMessage(MessageKey.ERR03,null, Locale.ENGLISH));
        }

        if(Objects.nonNull(catalogDto.getId())){
            actionType = ActionType.UPDATE;
            logger.debug("Kategori Guncelleniyor");
        }
        else{
            actionType = ActionType.INSERT;
            logger.debug("Yeni Kategori Ekleniyor");
        }


        Catalog tmpCatalog = catalogMapper.mapToEntityFromDto(catalogDto);
        tmpCatalog.setDeleted(Boolean.FALSE);
        Catalog catalog = catalogRepository.save(tmpCatalog);

        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(actionType);
        history.setEfectedId(catalog.getId());
        history.setRelatedTable(catalog.getClass().getName().toUpperCase());
        history.setDescription(catalog.getId()+" no lu catalog icin "+(ActionType.INSERT.equals(actionType)? "ekleme":"guncelleme")+" islemi yapildi");

        historyRepository.save(history);

        return this.catalogMapper.mapToDtoFromEntity(catalog);
    }

    public Boolean delete(Long catalogId){
        catalogRepository.deleteById(catalogId);
        logger.debug(catalogId+" no lu catalog silindi.");
        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(ActionType.DELETE);
        history.setEfectedId(catalogId);
        history.setRelatedTable("CATALOG");
        history.setDescription(catalogId+" no lu catalog icin silme islemi yapildi");
        historyRepository.save(history);
        return true;
    }


    public List<CatalogDto> getAllCatalog() {
        return catalogMapper.mapToDtoFromEntity(catalogRepository.findAll());
    }

}
