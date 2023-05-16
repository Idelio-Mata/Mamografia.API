package Project.Mamografia.Api.Controller;

import Project.Mamografia.Api.Entity.Regiao;
import Project.Mamografia.Api.Repository.RegiaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * Controller > Responsavel por colecionar todos nossos métodos.
 * Metodos de requesições feitos apartir de anotações e passagem de url.
*/


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
public class RegiaoController {

    /** final recebe a classeRepository e instancia nomeclasse+repository*/
    private final RegiaoRepository regiaoRepository;


    /**Construtor padrao da classe.   Recebe a instancia nomeClasse+repository da classe*/
    public RegiaoController(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }


    /**
     * Obtem lista de todas regioes, quando acessa a url designada no metodo.
     * Return regions. findAll.
     */
    @GetMapping("/regiao")
    public ResponseEntity<?> findAllRegioes(){
        try{
            List<Regiao> getAllRegioes = regiaoRepository.findAll();
            System.out.println("Encontrado");
            if (getAllRegioes.isEmpty())
                System.out.println("Vazio");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtem as regioes atravez de {id}.
     * if (verifica se o id da regiao escolhida existe) e retorna Ok, se sim e Not_Found senao..
     * */
    @GetMapping("/regiao/{id}")
    public ResponseEntity<?> getRegiaoById(@PathVariable  long id) {
        Optional regiaoEscolhidaOptional = regiaoRepository.findById(id);
        if (regiaoEscolhidaOptional.isPresent()) {
            Object regiaoEscolhida = regiaoEscolhidaOptional.get();
            return new ResponseEntity<>(regiaoEscolhida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    /**
     * Post metodo para salvar.
     * Ele recebe o objeto atraves de um Json dentro do @RequestBody
     * @return
     */
    @PostMapping("/regiao/novo")
    public Regiao postRegiao(@RequestBody Regiao newRegiao){
        return regiaoRepository.save(newRegiao);
    }

    /**
     * Delete metodo. Neste metodo pretendo deletar atraves do Id.
     * @PathVariable é a variavel que sserá passada para o metodo > {id} neste caso que sera de eletado.
     * */
    @DeleteMapping("regiao/delete/{id}")
    public void deleteRegiao(@PathVariable long id){
        regiaoRepository.deleteById(id);
    }


}
