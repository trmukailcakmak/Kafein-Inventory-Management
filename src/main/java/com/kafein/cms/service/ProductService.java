package com.kafein.cms.service;

import com.kafein.cms.constant.ActionType;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.exceptions.KafeinException;
import com.kafein.cms.mapper.ProductMapper;
import com.kafein.cms.model.dto.ProductDto;
import com.kafein.cms.model.dto.SearchDto;
import com.kafein.cms.model.entity.Catalog;
import com.kafein.cms.model.entity.History;
import com.kafein.cms.model.entity.Product;
import com.kafein.cms.model.entity.Warehouse;
import com.kafein.cms.repository.CatalogRepository;
import com.kafein.cms.repository.HistoryRepository;
import com.kafein.cms.repository.ProductRepository;
import com.kafein.cms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final CatalogRepository catalogRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    private final MessageService messageService;

    private final HistoryRepository historyRepository;

    public ProductDto updateAndCreate(ProductDto productDto) {

        final Optional<List<Product>> catalogList = productRepository.findByName(productDto.getName());
        ActionType actionType = null;
        if(Objects.isNull(productDto.getId()) && !catalogList.isPresent()){
            throw new KafeinException(MessageKey.ERR02,this.messageService.getMessage(MessageKey.ERR03));
        }

        if(Objects.nonNull(productDto.getId())){
            actionType = ActionType.UPDATE;
            logger.debug("Urun Guncelleniyor");
        }
        else{
            actionType = ActionType.INSERT;
            logger.debug("Yeni Urun Ekleniyor");
        }
        Product product = productMapper.mapToEntityFromDto(productDto);
        Optional<Catalog> catalogOpt = catalogRepository.findById(productDto.getCatalogId());
        if(catalogOpt.isPresent()) {
            product.setCatalog(catalogOpt.get());
        }

        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(productDto.getWarehouseId());
        if(warehouseOpt.isPresent()) {
            product.setWarehouse(warehouseOpt.get());
        }
        product.setDeleted(Boolean.FALSE);
        product = productRepository.saveAndFlush(product);

        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(actionType);
        history.setRelatedTable(product.getClass().getName().toUpperCase());
        history.setEfectedId(product.getId());
        history.setDescription(product.getId()+" no lu urun icin "+(ActionType.INSERT.equals(actionType)? "ekleme":"guncelleme")+" islemi yapildi");

        historyRepository.saveAndFlush(history);
        return productMapper.maptoDtoFromEntity(product);
    }

    public Boolean delete(Long id) {
            productRepository.deleteById(id);
        History history = new History();
        history.setDeleted(Boolean.FALSE);
        history.setActionType(ActionType.DELETE);
        history.setEfectedId(id);
        history.setRelatedTable("PRODUCT");
        history.setDescription(id+" no lu urun icin silme islemi yapildi");
        historyRepository.saveAndFlush(history);
            return true;
    }

    public Boolean decrementStock(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setStockSize(product.getStockSize()-1);
            product = productRepository.saveAndFlush(product);

            if(product.getStockSize()<=product.getStockDownLimit()){
                //TODO : Burda mail gonderilebilir. su anlik log a yazdiriliyor.
                logger.error(product.getId()+" li urunun miktari kritik seviyenin altinda. lutfen uruntu tedarik ediniz.");
            }
            History history = new History();
            history.setDeleted(Boolean.FALSE);
            history.setActionType(ActionType.STOCK_DECREMENT);
            history.setEfectedId(product.getId());
            history.setRelatedTable("PRODUCT");
            history.setDescription(id+" no lu urun icin stock azaltilma islemi yapildi.");
            historyRepository.saveAndFlush(history);
        }

        return true;
    }

    public Boolean incrementStock(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setStockSize(product.getStockSize()+1);
            product = productRepository.save(product);

            if(product.getStockSize()<=product.getStockDownLimit()){
                //TODO : Burda mail gonderilebilir. su anlik log a yazdiriliyor.
                logger.error(product.getId()+" li urunun miktari kritik seviyenin altinda. lutfen Urunu tedarik ediniz.");
            }
            History history = new History();
            history.setDeleted(Boolean.FALSE);
            history.setActionType(ActionType.STOCK_INCREMENT);
            history.setEfectedId(product.getId());
            history.setRelatedTable("PRODUCT");
            history.setDescription(id+" no lu urun icin stock artirma islemi yapildi.");
            historyRepository.saveAndFlush(history);
        }

        return true;
    }

    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productMapper.maptoDtoFromEntity(productList);
    }

    public List<ProductDto> search(SearchDto searchDto){
        if(Objects.nonNull(searchDto.getProductId())){
            Optional<Product> productOpt = productRepository.findById(searchDto.getProductId());
            if(productOpt.isPresent()){
                return Arrays.asList(productMapper.maptoDtoFromEntity(productOpt.get()));
            }
            throw new KafeinException(MessageKey.ERR12,this.messageService.getMessage(MessageKey.ERR12));
        }
        else{

            ArrayList<ProductDto> productDtoList = new ArrayList<>();

            Optional<List<Warehouse>> warehouseListOpt = warehouseRepository.findByNameLikeOrAreaLikeOrCityLikeOrDistrictLike("%"+searchDto.getName(),searchDto.getArea(),searchDto.getCity(),searchDto.getDistrict());
            //Optional<List<Catalog>> catalogListOpt = catalogRepository.findByNameLike(searchDto.getName());
            if(warehouseListOpt.isPresent()){
                List<Warehouse> warehouseList = warehouseListOpt.get();

                warehouseList.forEach(item ->{
                    Optional<List<Product>> productListOpt = productRepository.findByWarehouseId(item.getId());
                    if(productListOpt.isPresent()){
                        /*
                        List<Product> productList = productListOpt.get();
                        if(catalogListOpt.isPresent()) {
                            List<Long> catalogList = catalogListOpt.get().stream().map(catalogItem->catalogItem.getId()).collect(Collectors.toList());
                            productDtoList.addAll(productMapper.maptoDtoFromEntity(productList.stream().filter(productItem -> catalogList.contains(productItem.getId())).collect(Collectors.toList())));
                        }else {

                         */
                            productDtoList.addAll(productMapper.maptoDtoFromEntity(productListOpt.get()));
                        //}
                    }
                });
            }
            return productDtoList;
        }
    }

}
