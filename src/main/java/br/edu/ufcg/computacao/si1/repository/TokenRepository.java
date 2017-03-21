package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	Token findByKey(String key);
	Token findByUsuarioId(Long usuarioId);
}
