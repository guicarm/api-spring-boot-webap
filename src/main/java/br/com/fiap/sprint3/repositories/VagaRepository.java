package br.com.fiap.sprint3.repositories;
import br.com.fiap.sprint3.models.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long>{
    public Page<Vaga> findAllByAndarEquals(String andar, Pageable pageable);
}
