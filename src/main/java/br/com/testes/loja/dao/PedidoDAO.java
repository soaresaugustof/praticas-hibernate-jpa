package br.com.testes.loja.dao;

import javax.persistence.EntityManager;

import br.com.testes.loja.vo.Pedido;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Pedido pedido) {
	this.em.persist(pedido);
    }

}
