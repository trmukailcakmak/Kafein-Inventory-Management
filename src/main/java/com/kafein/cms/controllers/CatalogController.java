package com.kafein.cms.controllers;

import com.kafein.cms.constant.ConstantEndPoint;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.model.dto.CatalogDto;
import com.kafein.cms.model.request.CatalogRequest;
import com.kafein.cms.mapper.CatalogMapper;
import com.kafein.cms.model.response.CatalogResponse;
import com.kafein.cms.model.response.WarehouseResponse;
import com.kafein.cms.service.CatalogService;
import com.kafein.cms.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(ConstantEndPoint.CATALOG_CONTROLLER)
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    private final CatalogMapper catalogMapper;

    private final MessageService messageService;

    @PostMapping(ConstantEndPoint.UPDATE_AND_CREATE_CATALOG)
    @ApiOperation("Kategori kayıt ve günceleme servisi")
    public ResponseEntity<Object> save(@RequestBody @Valid CatalogRequest request) {
        CatalogDto catalogDto = catalogService.updateAndCreate(catalogMapper.mapToDtoFromRequest(request));
        return new ResponseEntity<>(catalogDto, HttpStatus.OK);
    }

    @GetMapping(ConstantEndPoint.GET_ALL)
    @ApiOperation("butun depo listesini dönen servis")
    public ResponseEntity<List<CatalogResponse>> getAllWarehouse() {
        return new ResponseEntity<>(catalogMapper.mapToResponseFromDto(this.catalogService.getAllCatalog()), HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.DELETE_BY_ID)
    @ApiOperation("Katalog silme servisi")
    public ResponseEntity<String> delete(@PathVariable @NotNull(message = MessageKey.ERR11) Long id) {
        catalogService.delete(id);
        return new ResponseEntity<>(this.messageService.getMessage(MessageKey.INFO01), HttpStatus.OK);
    }

}
