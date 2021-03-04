package POJO;

public class estoquePOJO {
	private String placa;
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public estoquePOJO(String placa) {
		this.placa = placa;
	}
	
	public boolean equals(estoquePOJO e) {
		return this.placa.equals(e.placa);
	}
	
	@Override
	public String toString() {
		return "Estoque [Placa = " + this.placa + "]";
	}
}
