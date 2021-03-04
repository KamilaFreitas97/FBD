package POJO;

public class carroPOJO {
	private String placa;
	private String modelo;
	private float preco;
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public void setModelo(String placa) {
		this.placa = placa;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public float getPreco() {
		return this.preco;
	}
	
	public carroPOJO(String placa, String modelo, float preco) {
		this.placa = placa;
		this.modelo = modelo;
		this.preco = preco;
	}
	
	public boolean equals(carroPOJO c) {
		return this.placa.equals(c.placa);
	}
	
	@Override
	public String toString() {
		return "Carro [Placa = " + this.placa + ", Modelo = " + this.modelo + ", Preco = " + this.preco + "]";
	}
}
