package visao;

import javax.swing.JOptionPane;

import beans.Estoque;
import controle.EstoqueControle;

public class EstoqueVisao {

	public static void main(String[] args) 
	{
		Estoque estoque;
		EstoqueVisao ev = new EstoqueVisao();
		String menu = "1-Incluir\n";
		menu += "2-Alterar\n";
		menu += "3-Excluir\n";
		menu += "4-Consultar por código\n";
		menu += "5-Consultar por descrição\n";
		menu += "6-Fim\n";
		String strOpcao = "";
		int opcao = 0;
		while (opcao != 6) {
			strOpcao = JOptionPane.showInputDialog(null, menu, "Opções", 1);
			try {
				opcao = Integer.parseInt(strOpcao);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "opção inválida", "AVISO", 2);
			}
			if (opcao == 1)
				ev.incluir();
			if (opcao == 2)
				ev.alterar();
			if (opcao == 3)
				ev.excluir();
			if (opcao == 4)
				ev.consultarCodigo();
			if (opcao == 5)
				ev.consultarDescricao();
		}
	}

	public void incluir() 
	{
		Estoque estoque = new Estoque();
		estoque.setDescricao(this.entradaString("informe a descrição"));
		estoque.setPrecounit(this.entradaDouble("informe o preço unitário"));
		estoque.setQuantidade(this.entradaDouble("informe a quantidade"));
		estoque.setId(0);
		EstoqueControle estoqueControle = new EstoqueControle();
		boolean retorno=estoqueControle.salvar(estoque);
		if(retorno)
			EstoqueVisao.mensagem("Cadastro efetuado com sucesso");
		else
			EstoqueVisao.mensagem("erro de cadastro");
	}

	public void alterar() 
	{
		Estoque estoque = new Estoque();
		int codigo = this.entradInt("Informe o código desejado");
		EstoqueControle estoqueControle = new EstoqueControle();
		estoque = estoqueControle.getEstoquePorId(codigo);
		if(estoque !=null)
		{
			String dados = "Você selecionou o item:\n";
			dados+="Descrição:"+estoque.getDescricao()+"\n";
			dados+="Preço unitário:"+estoque.getPrecounit()+"\n";
			dados+="Quantidade:"+estoque.getQuantidade();
			JOptionPane.showMessageDialog(null, dados,"Produto Encontrado",0);
			
			estoque.setDescricao(this.entradaString("informe a descrição("+estoque.getDescricao()+")"));
			estoque.setPrecounit(this.entradaDouble("informe o preço unitário("+estoque.getPrecounit()+")"));
			estoque.setQuantidade(this.entradaDouble("informe a quantidade("+estoque.getQuantidade()+")"));
			boolean retorno = estoqueControle.salvar(estoque);
			if(retorno)
				EstoqueVisao.mensagem("Atualização efetuada com sucesso");
			else
				EstoqueVisao.mensagem("erro de alteração");
		}
	}
	
	public void excluir() 
	{
		Estoque estoque = new Estoque();
		int codigo = this.entradInt("Informe o código desejado");
		EstoqueControle estoqueControle = new EstoqueControle();
		estoque = estoqueControle.getEstoquePorId(codigo);
		if(estoque !=null)
		{
			String dados = "Você selecionou o item:\n";
			dados+="Descrição:"+estoque.getDescricao()+"\n";
			dados+="Preço unitário:"+estoque.getPrecounit()+"\n";
			dados+="Quantidade:"+estoque.getQuantidade();
			JOptionPane.showMessageDialog(null, dados,"Produto Encontrado",0);
			int dexc=0;
			while (true)
			{
				String desejaExcluir= JOptionPane.showInputDialog(null,"Deseja Excluir(1-sim,2-Não","Excluir?",1);
				try {
					dexc=Integer.parseInt(desejaExcluir);
					break;
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Opção errada","erro",0);
				}
			}
			if (dexc==1)
			{
				boolean retorno = estoqueControle.excuir(estoque.getId());
				if (retorno)
					EstoqueVisao.mensagem("Exclusão efetuada com sucesso");
				else
					EstoqueVisao.mensagem("erro de exclusão");
			}
		}
	}
	
	public void consultarCodigo() {
		Estoque estoque = new Estoque();
		int codigo = this.entradInt("Informe o produto desejado");
		EstoqueControle estoqueControle = new EstoqueControle();
		estoque = estoqueControle.getEstoquePorId(codigo);
		if(estoque !=null)
		{
			String dados = "Você selecionou o item:\n";
			dados+="Descrição:"+estoque.getDescricao()+"\n";
			dados+="Preço unitário:"+estoque.getPrecounit()+"\n";
			dados+="Quantidade:"+estoque.getQuantidade();
			JOptionPane.showMessageDialog(null, dados,"Produto Encontrado",0);
			EstoqueVisao.mensagem("Código:" + String.valueOf(estoque.getId()));
		}
	}
	
	public void consultarDescricao() {
		Estoque estoque = new Estoque();
		int codigo = this.entradInt("Informe o produto desejado");
		EstoqueControle estoqueControle = new EstoqueControle();
		estoque = estoqueControle.getEstoquePorId(codigo);
		if(estoque !=null)
		{
			String dados = "Você selecionou o item:\n";
			dados+="Descrição:"+estoque.getDescricao();
			EstoqueVisao.mensagem(dados);
		}
	}
	
	
	public static void mensagem(String msg) 
	{
		JOptionPane.showMessageDialog(null, msg, "Mensagem", 3);
	}

	public String entradaString(String msg) 
	{
		String aux = "";
		aux = JOptionPane.showInputDialog(null, msg, "Entrada", 3);
		return aux;
	}

	public int entradInt(String msg) 
	{
		String aux = "";
		int dado = 0;
		aux = JOptionPane.showInputDialog(null, msg, "Entrada", 3);
		try {
			dado = Integer.parseInt(aux);
		} catch (Exception e) {
			EstoqueVisao.mensagem("Valor inválido");
		}

		return dado;
	}
	
	public double entradaDouble(String msg) 
	{
		String aux = "";
		double dado = 0;
		aux = JOptionPane.showInputDialog(null, msg, "Entrada", 3);
		try {
			dado = Double.parseDouble(aux);
		} catch (Exception e) {
			EstoqueVisao.mensagem("Valor inválido");
		}
		return dado;
	}
}
