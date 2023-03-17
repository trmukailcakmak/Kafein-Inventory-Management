package com.kafein.cms.controllers;

import com.kafein.cms.constant.ConstantEndPoint;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.mapper.SearchMapper;
import com.kafein.cms.model.dto.ProductDto;
import com.kafein.cms.mapper.ProductMapper;
import com.kafein.cms.model.request.ProductRequest;
import com.kafein.cms.model.request.SearchRequest;
import com.kafein.cms.model.request.StockRequest;
import com.kafein.cms.model.response.SearchResponse;
import com.kafein.cms.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(ConstantEndPoint.PRODUCT_CONTROLLER)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    private final SearchMapper searchMapper = SearchMapper.INSTANCE;

    @PostMapping(ConstantEndPoint.UPDATE_AND_CREATE_PRODUCT)
    @ApiOperation("ürün kayıt ve günceleme servisi")
    public ResponseEntity<Object> save(@RequestBody @Valid ProductRequest productRequest) {
        ProductDto productDto = productService.updateAndCreate(productMapper.mapToDtoFromRequest(productRequest));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.DELETE_BY_ID)
    @ApiOperation("ürün silme servisi")
    public ResponseEntity<Object> delete(@PathVariable @NotNull(message = MessageKey.ERR11) Long id) {
        Boolean result = productService.delete(id);
        if (result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    @GetMapping(ConstantEndPoint.GET_ALL)
    @ApiOperation("bütün ürünleri dönen servisi")
    public ResponseEntity<Object> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.ADD_PRODUCT_STOCK)
    @ApiOperation("ürün stok artirma servisi")
    public ResponseEntity<Object> addStock(@PathVariable @NotNull(message = MessageKey.ERR11) Long id) {
        return new ResponseEntity<>(productService.incrementStock(id), HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.REMOVE_PRODUCT_STOCK)
    @ApiOperation("ürün stok azaltma servisi")
    public ResponseEntity<Object> removeFromStock(@PathVariable @NotNull(message = MessageKey.ERR11) Long id) {
        return new ResponseEntity<>(productService.decrementStock(id), HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.SEARCH)
    @ApiOperation("Depo Katalog ve Product arasinda urun arama islemi")
    public ResponseEntity<List<SearchResponse>> search(@RequestBody @Valid SearchRequest searchRequest) {
        List<ProductDto> productDtoList = productService.search(searchMapper.mapToDtoFromRequest(searchRequest));
        return new ResponseEntity<>(searchMapper.mapToResponseFromDto(productDtoList), HttpStatus.OK);
    }

}
