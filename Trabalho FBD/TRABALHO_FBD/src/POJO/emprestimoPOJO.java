package POJO;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class emprestimoPOJO {
	private String cpf_cliente;
	private String placa_carro;
	private Date data_emprestimo;
	private int dias_escolhidos;
	private String cpf_vendedor;
	private float juros;
	private float preco_base;
	private boolean devolvido;
	
	public emprestimoPOJO(String cpf_cliente, String placa_carro, Date data, int dias, String cpf_vendedor, float juros, float preco, boolean devolvido) {
		this.cpf_cliente = cpf_cliente;
		this.placa_carro = placa_carro;
		this.data_emprestimo = data;
		this.dias_escolhidos = dias;
		this.cpf_vendedor = cpf_vendedor;
		this.juros = juros;
		this.preco_base = preco;
		this.devolvido = devolvido;
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

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public int getDias_escolhidos() {
		return dias_escolhidos;
	}

	public void setDias_escolhidos(int dias_escolhidos) {
		this.dias_escolhidos = dias_escolhidos;
	}

	public String getCpf_vendedor() {
		return cpf_vendedor;
	}

	public void setCpf_vendedor(String cpf_vendedor) {
		this.cpf_vendedor = cpf_vendedor;
	}

	public float getJuros() {
		return juros;
	}

	public void setJuros(float juros) {
		this.juros = juros;
	}

	public float getPreco_base() {
		return preco_base;
	}

	public void setPreco_base(float preco_base) {
		this.preco_base = preco_base;
	}
	
	public boolean getDevolvido() {
		return this.devolvido;
	}
	
	public boolean equals(emprestimoPOJO empr) {
		boolean a = this.cpf_cliente.equals(empr.cpf_cliente);
		boolean b = this.placa_carro.equals(empr.placa_carro);
		return a && b;
	}
	
	@Override
	public String toString() {
		String temp;
		if(this.devolvido)
			temp = "Sim";
		else
			temp = "Nao";
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		return "Emprestimo [Cliente = " + this.cpf_cliente + ", Placa = " + this.placa_carro + ", Data = " + fmt.format(this.data_emprestimo) + ", Vendedor = " + this.cpf_vendedor + ", Dias escolhidos = " + this.dias_escolhidos + ", Juros = " + this.juros + ", Preco base = " + this.preco_base + ", Devolvido: " + temp + "]";
	}
}
