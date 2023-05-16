package Project.Mamografia.Api.Repository;


import Project.Mamografia.Api.Entity.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repositors sao interfaces.
 * Estendem o JpaRepository e Recebem < classe, tipo de dado>
 * */
public interface RegiaoRepository  extends JpaRepository <Regiao, Long>{
}
