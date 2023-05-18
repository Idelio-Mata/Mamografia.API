package Project.Mamografia.Api.Controller;

import Project.Mamografia.Api.Entity.FaixaEtaria;
import Project.Mamografia.Api.Repository.FaixaEtariaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class FaixaEtariaController {

    /** final recebe a classeRepository e instancia nomeclasse+repository*/
    private final FaixaEtariaRepository faixaEtariaRepository;

    /**Construtor padrao da classe.   Recebe a instancia nomeClasse+repository da classe*/
    public FaixaEtariaController(FaixaEtariaRepository faixaEtariaRepository) {
        this.faixaEtariaRepository = faixaEtariaRepository;
    }



    /**
     * A resposta deste é a lista de todas faixa etarias, quando acessa a url designada no metodo.
     * Se mostrar lista de faixas etarias, mostrar codigo OK.
     * Se não tiver a lista, mostrar o caminho e o codigo de NOT_FOUND
     **/
    @GetMapping("/faixaetaria")
    public ResponseEntity<?> findAllFaixaEtaria(){
        try{
            List<FaixaEtaria> listaFaixasEtarias = faixaEtariaRepository.findAll();
            return new ResponseEntity<>(listaFaixasEtarias, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Obtem  faixa etarias pelo {id}, quando acessa a url designada no metodo.
     * Se o unico id selecionado tiver informacao, pegar o objeto FaixaEtaria mostrar a unidade e codigo OK.
     * Se não tiver, mostrar o caminho e o codigo de NOT_FOUND
     **/
    @GetMapping("/faixaetaria/{id}")
    public ResponseEntity<FaixaEtaria> findByIdFaixaEtaria(@PathVariable long id){
        try{
            Optional<FaixaEtaria> unidOptional = faixaEtariaRepository.findById(id);
            if (unidOptional.isPresent()){
                FaixaEtaria faixaEtariaUnid = unidOptional.get();
                return new ResponseEntity<>(faixaEtariaUnid, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Post metodo para salvar.
     * Ele recebe o objeto atraves de um Json dentro do @RequestBody
     * @return
     */@PostMapping("/faixaetaria/novo")
    public FaixaEtaria postFaixaEtaria(@RequestBody FaixaEtaria newFaixaEtaria){
         return faixaEtariaRepository.save(newFaixaEtaria);
    }

    /**
     * Delete metodo. Neste metodo pretendo deletar atraves do Id.
     * @PathVariable é a variavel que sserá passada para o metodo > {id} neste caso que sera de eletado.
     * */
    @DeleteMapping("faixaetaria/delete/{id}")
    public void deleteFaixaEtaria(@PathVariable long id){
        faixaEtariaRepository.deleteById(id);
    }

}
