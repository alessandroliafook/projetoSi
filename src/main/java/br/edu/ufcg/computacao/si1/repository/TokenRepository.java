package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
	Token findByKey(String key);
}
