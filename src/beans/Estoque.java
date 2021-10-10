package beans;

public class Estoque {

	private int id;
	private String descricao;
	private double quantidade;
	private double precounit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecounit() {
		return precounit;
	}
	public void setPrecounit(double precounit) {
		this.precounit = precounit;
	}
	
}
