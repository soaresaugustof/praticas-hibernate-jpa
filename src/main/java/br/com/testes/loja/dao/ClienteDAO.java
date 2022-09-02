package br.com.testes.loja.dao;

import javax.persistence.EntityManager;

import br.com.testes.loja.vo.Cliente;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Cliente cliente) {
	this.em.persist(cliente);
    }

    public Cliente buscaPorId(long id) {
	return em.find(Cliente.class, id);
    }

}
