package br.com.fiap.sprint3.controlllers.api;

import br.com.fiap.sprint3.models.Vaga;
import br.com.fiap.sprint3.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
public class ApiVagaController {
    @Autowired
    private VagaRepository repository;

    @GetMapping
    @Cacheable("vagas")
    public Page<Vaga> listar(@RequestParam(required = false) String andar, @PageableDefault Pageable pageable){
        if (andar == null){
            return repository.findAll(pageable);
        }
        return repository.findAllByAndarEquals(andar, pageable);
    }

    @PostMapping
    @CacheEvict(value = "vagas", allEntries = true)
    public ResponseEntity<Vaga> create(@RequestBody @Valid Vaga vaga, UriComponentsBuilder uriBuilder) {
        repository.save(vaga);
        URI uri = uriBuilder
                    .path("/api/task/{id}")
                    .buildAndExpand(vaga.getId())
                    .toUri();

        return ResponseEntity.created(uri).body(vaga);

    }


    @PostMapping("/{id}")
    public ResponseEntity<Vaga> atualizarStatus(@PathVariable("id") Long id){
        System.out.println(id);
        Optional<Vaga> optional = repository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Vaga vaga = optional.get();
        vaga.setDisponivel(vaga.getDisponivel() == true ? false : true);
        repository.save(vaga);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    @CacheEvict(value = "tasks", allEntries = true)
    public ResponseEntity<Vaga> atualizaVaga(
            @PathVariable Long id,
            @RequestBody Vaga vagaAtualizada
    ){

        Optional<Vaga> optional = repository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        Vaga vaga = optional.get();
        vaga.setAndar(vagaAtualizada.getAndar());
        vaga.setDisponivel(vagaAtualizada.getDisponivel());
        vaga.setLocalizacao(vagaAtualizada.getLocalizacao());

        repository.save(vaga);


        return ResponseEntity.ok(vaga);

    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "tasks", allEntries = true)
    public ResponseEntity<Vaga> deletar(@PathVariable Long id){
        Optional<Vaga> vaga = repository.findById(id);

        if (vaga.isEmpty())
            return ResponseEntity.notFound().build();

        repository.deleteById(id);
        return ResponseEntity.ok().build();

    }


}
