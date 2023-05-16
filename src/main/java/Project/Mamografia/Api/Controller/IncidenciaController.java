package Project.Mamografia.Api.Controller;

import Project.Mamografia.Api.Entity.Incidencia;
import Project.Mamografia.Api.Repository.IncidenciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @RestController >  Indica que a classe é um controlador que lida com solicitacoes HTTP de entrada e esposta HTTP.
 * Significa que os metodos serão chamados quando um cliente fizer uma solicitacao HTTP para API
 *
 * @RequestMapping("/api") > Define um mapeamento de URL para a classe.
 * Significa que todas as solicitações HTTP que começam com /api serão manipuladas por essa classe.
 *
 * */

@RestController
@RequestMapping("/api")
public class IncidenciaController {


    /** final recebe a classeRepository e instancia nomeclasse+repository*/
    private final IncidenciaRepository incidenciaRepository;

    /**Construtor padrao da classe.   Recebe a instancia nomeClasse+repository da classe*/
    public IncidenciaController(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }


    /**
     * A resposta deste é a lista de todas ocorrencias, quando acessa a url designada no metodo.
     * Se estiver vasio, mostrar codigo NOT_FOUND.
     * Se não tiver vasio, mostrar incidencias e o codigo OK.
     **/
    @GetMapping("/incidencia")
    public ResponseEntity<List<Incidencia>> findIncidencia(){
            List<Incidencia> listaIncidencias = incidenciaRepository.findAll();
            if (listaIncidencias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(listaIncidencias, HttpStatus.OK);
    }


    /**
     * Obtem  as ocorrencias pelo {id}, quando acessa a url designada no metodo.
     * Optional representaa um valor que pode ser nulo ou não.
     * Se o optinonl id selecionado tiver informacao, pegar no objeto Incidencia o Unid. e retonar codigo OK.
     * Se for nulo,mostrar  codigo de NOT_FOUND
     **/
    @GetMapping("/incidencia/{id}")
    public ResponseEntity<Incidencia> findIncidenciaById(@PathVariable long id){
        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(id);
            if (incidenciaOptional.isPresent()){
                Incidencia incidenciaUnid = incidenciaOptional.get();
                return new ResponseEntity<>(incidenciaUnid, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Post metodo para salvar.
     * Ele recebe o objeto atraves de um Json dentro do @RequestBody
     * @return
     */@PostMapping("/incidencia/novo")
    public Incidencia postIncidencia(@RequestBody Incidencia newIncidencia){
        return incidenciaRepository.save(newIncidencia);
    }



}
