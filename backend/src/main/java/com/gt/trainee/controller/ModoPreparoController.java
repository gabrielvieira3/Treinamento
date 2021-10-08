package com.gt.trainee.controller;

import com.gt.trainee.models.ModoPreparo;
import com.gt.trainee.service.ModoPreparoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/modopreparo")
public class ModoPreparoController {

    @Autowired
    private ModoPreparoService modoPreparoService;

    @GetMapping
    public List<ModoPreparo> findAll() {
        return modoPreparoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ModoPreparo findById(@PathVariable Long id) {
        return modoPreparoService.findById(id);
    }

    @PostMapping(value = "/{id}")
    public ModoPreparo insertModoPreparo (@Valid @RequestBody ModoPreparo modoPreparo) {
        return modoPreparoService.save(modoPreparo);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id){
        modoPreparoService.deleteById(id);
    }
}
