package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseCrudController<S, T> {

        @PostMapping("/save")
        ResponseEntity<S> save(@RequestBody S s);

        @PutMapping("/update")
        ResponseEntity<S> update(@RequestBody S s);

        @GetMapping("/{id}")
        ResponseEntity<S> findById(@PathVariable T id);

        @GetMapping("/all")
        ResponseEntity<List<S>> findAll();
    }