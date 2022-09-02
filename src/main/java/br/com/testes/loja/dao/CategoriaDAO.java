package br.com.testes.loja.dao;

import java.util.List;

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
	categoria = em.merge(categoria);
	this.em.remove(categoria);
    }

    public Categoria buscaPorId(long id) {
	return em.find(Categoria.class, id);
    }

    public List<Categoria> buscarProdutos() {
	String jpql = "select c from Categoria c";
	return em.createQuery(jpql, Categoria.class).getResultList();
    }
}
