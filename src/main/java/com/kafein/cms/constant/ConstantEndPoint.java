package com.kafein.cms.constant;

public class ConstantEndPoint {

    public static final String API_PREFIX = "/api";
    public static final String PRODUCT_PREFIX = "/product";
    public static final String WAREHOUSE_PREFIX = "/warehouse";
    public static final String CATALOG_PREFIX = "/catalog";
    public static final String PRODUCT_CONTROLLER = API_PREFIX + PRODUCT_PREFIX;
    public static final String WAREHOUSE_CONTROLLER = API_PREFIX + WAREHOUSE_PREFIX;
    public static final String CATALOG_CONTROLLER = API_PREFIX + CATALOG_PREFIX;

    // Material
    public static final String UPDATE_AND_CREATE_PRODUCT = "/update-or-add";
    public static final String DELETE_BY_ID = "/delete/{id}";
    public static final String GET_ALL = "/all";
    public static final String ADD_PRODUCT_STOCK = "/add-stock";
    public static final String REMOVE_PRODUCT_STOCK = "/remove-stock";
    public static final String SEARCH = "/search";

    // Warehouse
    public static final String UPDATE_AND_CREATE_WAREHOUSE = "/update-or-add";

    // Categori
    public static final String UPDATE_AND_CREATE_CATALOG = "/update-or-add";
}
