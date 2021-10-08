package com.gt.trainee.controller;

import com.gt.trainee.models.TipoUnidade;
import com.gt.trainee.service.TipoUnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tipounidade")
public class TipoUnidadeController {

    @Autowired
    private TipoUnidadeService tipoUnidadeService;

    @GetMapping
    public List<TipoUnidade> findAll() {
        return tipoUnidadeService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TipoUnidade findById(@PathVariable Long id) {
        return tipoUnidadeService.findById(id);
    }

    @PostMapping(value = "/{id}")
    public TipoUnidade save(@Valid @RequestBody TipoUnidade tipoUnidade) {
        return tipoUnidadeService.save(tipoUnidade);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        tipoUnidadeService.deleteById(id);
    }
}
