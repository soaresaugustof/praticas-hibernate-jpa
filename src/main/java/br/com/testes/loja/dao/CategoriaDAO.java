package br.com.testes.loja.dao;

import javax.persistence.EntityManager;

import br.com.testes.loja.vo.Categoria;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Categoria categoria) {
	this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
	this.em.merge(categoria);
    }

    public void remover(Categoria categoria) {
	this.em.remove(categoria);
    }
}
