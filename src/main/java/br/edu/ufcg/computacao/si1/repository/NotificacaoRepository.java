package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alessandro Fook on 22/03/2017.
 */
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
	List<Notificacao> findAllByIdUsuario(Long idUsuario);
}
