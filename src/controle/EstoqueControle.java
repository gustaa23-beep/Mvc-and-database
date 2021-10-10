package controle;

import java.util.List;

import dao.EstoqueDao;
import beans.Estoque;

public class EstoqueControle {
	private EstoqueDao ed = new EstoqueDao();
	
	public List<Estoque> getEstoque(){
		return ed.getTodos();
		
	}
	
	public Estoque getEstoquePorId(int id) {
		return ed.getEstoquePorId(id);
	}
	
	public boolean excuir(int id) {
		return ed.excluir(id);
	}
	
	public boolean salvar(Estoque estoque) {
		return ed.salvarEstoque(estoque);
	}
	
	public List<Estoque> buscaEstoquesPorNome(String nome) {
		return ed.getEstoqueViaNome(nome);
	}
}
