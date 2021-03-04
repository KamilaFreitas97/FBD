package POJO;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class historicoPOJO {
	private String cpf_cliente;
	private String placa_carro;
	private Date data_entrega;
	private int dias_ficados;
	private String cpf_vendedor;
	private float preco_final;
	
	public historicoPOJO(String cpf_cliente, String placa, Date data, int dias, String cpf_vendedor, float preco) {
		this.cpf_cliente = cpf_cliente;
		this.placa_carro = placa;
		this.data_entrega = data;
		this.dias_ficados = dias;
		this.cpf_vendedor = cpf_vendedor;
		this.preco_final = preco;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getPlaca_carro() {
		return placa_carro;
	}

	public void setPlaca_carro(String placa_carro) {
		this.placa_carro = placa_carro;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	public int getDias_ficados() {
		return dias_ficados;
	}

	public void setDias_ficados(int dias_ficados) {
		this.dias_ficados = dias_ficados;
	}

	public String getCpf_vendedor() {
		return cpf_vendedor;
	}

	public void setCpf_vendedor(String cpf_vendedor) {
		this.cpf_vendedor = cpf_vendedor;
	}

	public float getPreco_final() {
		return preco_final;
	}

	public void setPreco_final(float preco_final) {
		this.preco_final = preco_final;
	}
	
	public boolean equals(historicoPOJO hist) {
		boolean a = this.cpf_cliente.equals(hist.cpf_cliente);
		boolean b = this.placa_carro.equals(hist.placa_carro);
		
		return a && b;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		return "Historico [Cliente = " + this.cpf_cliente + ", Placa = " + this.placa_carro + ", Data = " + fmt.format(this.data_entrega) +
				 ", Vendedor = " + this.cpf_vendedor + ", Ficados = " + this.dias_ficados + ", Preco final = " + this.preco_final + "]";
	}
}
