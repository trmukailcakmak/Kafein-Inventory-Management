package com.kafein.cms.controllers;

import com.kafein.cms.constant.ConstantEndPoint;
import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.model.dto.WarehouseDto;
import com.kafein.cms.model.request.WarehouseRequest;
import com.kafein.cms.mapper.WarehouseMapper;
import com.kafein.cms.model.response.WarehouseResponse;
import com.kafein.cms.service.MessageService;
import com.kafein.cms.service.WarehouseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(ConstantEndPoint.WAREHOUSE_CONTROLLER)
@RequiredArgsConstructor
public class WarehouseController{
    private final WarehouseService warehouseService;

    private final MessageService messageService;

    private final WarehouseMapper warehouseMapper = WarehouseMapper.INSTANCE;

    @PostMapping(ConstantEndPoint.UPDATE_AND_CREATE_WAREHOUSE)
    @ApiOperation("Depo kayıt ekleme ve günceleme servisi")
    @ResponseBody
    public ResponseEntity<Object> saveWarehouse(@RequestBody @Valid WarehouseRequest request) {
        WarehouseDto warehouseDto = warehouseService.updateAndCreate(warehouseMapper.mapToDtoFromRequest(request));
        return new ResponseEntity<>(warehouseDto, HttpStatus.OK);
    }

    @PostMapping(ConstantEndPoint.DELETE_BY_ID)
    @ApiOperation("Depo silme servisi")
    public ResponseEntity<String> delete(@PathVariable @NotNull(message = MessageKey.ERR11) Long id) {
        warehouseService.delete(id);
        return new ResponseEntity<>(this.messageService.getMessage(MessageKey.INFO01), HttpStatus.OK);
    }

    @GetMapping(ConstantEndPoint.GET_ALL)
    @ApiOperation("butun depo listesini dönen servis")
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouse() {
        return new ResponseEntity<>(warehouseMapper.mapToResponseFromDto(this.warehouseService.getAllWarehouse()), HttpStatus.OK);
    }

}
