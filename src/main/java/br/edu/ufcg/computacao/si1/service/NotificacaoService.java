package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Notificacao;
import br.edu.ufcg.computacao.si1.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alessandro Fook on 22/03/2017.
 */
@Service
public class NotificacaoService {

	@Autowired
	NotificacaoRepository repository;

	public Notificacao save(Notificacao notificacao){
		return repository.save(notificacao);
	}

	public boolean delete(Notificacao notificacao){

		if (repository.exists(notificacao.getId())){

			repository.delete(notificacao);
			return true;
		}

		return false;
	}

	public boolean atualizarVisualizacao(Notificacao notificacao){

		if (repository.exists(notificacao.getId())){

			notificacao.setVisto(true);
			repository.save(notificacao);

			return true;
		}

		return false;
	}

	public List<Notificacao> getNotificacoesUsuario(Long idUsuario) {
		return repository.findAllByIdUsuario(idUsuario);
	}

	public Notificacao getNotificacao(Long id) {
		return repository.getOne(id);
	}
}
