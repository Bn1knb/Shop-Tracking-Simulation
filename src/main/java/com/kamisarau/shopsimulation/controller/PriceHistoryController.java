package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.model.WrappedProduct;
import io.swagger.annotations.*;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/history")
@Api(value = "Get price history here")
public class PriceHistoryController {
    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{name}")
    @ApiOperation(value = "Get price history of choosen product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "History has been shown"),
            @ApiResponse(code = 404, message = "No History found")
    })
    public ResponseEntity<List<WrappedProduct>> getHistory(
            @ApiParam(name = "name", value = "name of product (eg. orange, cheese, apple")
            @PathVariable String name,
            @ApiParam(name = "page", value = "page number")
            @RequestParam(required = false) Integer page,
            @ApiParam(name = "size", value = "number of results per page")
            @RequestParam(required = false) Integer size) {

        int limit = (size == null) ? 10 : size;
        int first = (page == null) ? 0 : page * limit + 1;

        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(WrappedProduct.class, true, true)
                .add(AuditEntity.property("name").eq(name.toUpperCase()))
                .setFirstResult(first)
                .setMaxResults(limit);

        List<WrappedProduct> productHistory = query.getResultList();

        return ResponseEntity.ok(productHistory);
    }
}
