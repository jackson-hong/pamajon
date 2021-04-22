package com.pamajon.product.model.service;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
    List<HashMap> homeBoard();
    int wishInsert(HashMap map);
    int wishDuplicate(HashMap map);
}
