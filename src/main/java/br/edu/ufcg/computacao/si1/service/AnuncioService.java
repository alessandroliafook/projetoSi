package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.anuncio.TipoAnuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository repository;

    public Anuncio create(Anuncio anuncio) {
        /*aqui salvamos o anuncio recem criado no repositorio jpa*/
        return repository.save(anuncio);
    }


    public Anuncio getById(Long id) {
        /*aqui recuperamos o anuncio pelo seu id*/
        return repository.findOne(id);
    }

    public List<Anuncio> get(String tipo) {

        /*pegamos aqui todos os anuncios, mas retornamos os anuncios por tipo
        * filtrando o tipo, pelo equals, retornando um arrayLista*/
        return repository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(TipoAnuncio.valueOf(tipo.toUpperCase())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Anuncio> getAll() {
        /*aqui retornamos todos os anuncios, sem distincao*/

        return repository.findAll();
    }

    public boolean update(Anuncio anuncio) {
        /*a atualizacao do anuncio eh feita apenas se o anuncio ja existir*/
        if (repository.exists(anuncio.getId())) {
            repository.save(anuncio);
            return true;
        }
        return false;
    }


    public boolean delete(Long id) {
        /*aqui se apaga o anuncio se ele existir*/


        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        return false;
    }

    public boolean exists(Anuncio anuncio) {
        return repository.exists(anuncio.getId());
    }

    public AnuncioRepository getRepository(){
        return this.repository;
    }
}
