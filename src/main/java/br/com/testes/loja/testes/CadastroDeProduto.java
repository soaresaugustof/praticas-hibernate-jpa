package br.com.testes.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.testes.loja.vo.Produto;

public class CadastroDeProduto {
    public static void main(String[] args) {
	Produto celular = new Produto();
	celular.setNome("Xiaomi Redmi");
	celular.setDescricao("Topo de linha");
	celular.setPreco(new BigDecimal("800"));

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja-curso-alura");
	EntityManager em = factory.createEntityManager();

	em.getTransaction().begin();
	em.persist(celular);
	em.getTransaction().commit();
	em.close();

    }
}
