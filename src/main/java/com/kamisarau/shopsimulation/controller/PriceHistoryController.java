package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.model.WrappedProduct;
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
public class PriceHistoryController {
    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{name}")
    public ResponseEntity<List<WrappedProduct>> getHistory(@PathVariable String name,
                                                           @RequestParam(required = false) Integer page,
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
