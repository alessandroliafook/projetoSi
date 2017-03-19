package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
