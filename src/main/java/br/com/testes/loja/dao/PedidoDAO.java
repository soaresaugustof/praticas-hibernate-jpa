package br.com.testes.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.testes.loja.vo.Pedido;
import br.com.testes.loja.vo.RelatorioDeVendasVo;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Pedido pedido) {
	this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
	String jpql = "select sum(p.valorTotal) from Pedido p";
	return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
	String jpql = "select new br.com.testes.loja.vo.RelatorioDeVendasVo(" + "produto.nome, "
		+ "sum(item.quantidade), " + "max(pedido.data)) " + "from Pedido pedido " + "join pedido.itens item "
		+ "join item.produto produto " + "group by produto.nome " + "order by item.quantidade desc";
	return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id) {
	return em.createQuery("Select p from Pedido p join fetch p.cliente where p.id = :id", Pedido.class)
		.setParameter("id", id).getSingleResult();
    }
}
