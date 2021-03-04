package POJO;

public class vendedorPOJO {
	private String cpf;
	private String nome;
	private char sexo;
	private float salario;
	private String senha;
	
	public String getCPF() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public vendedorPOJO(String cpf, String nome, char sexo, String senha, float salario) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.salario = salario;
		this.senha = senha;
	}
	
	public boolean equals(vendedorPOJO v) {
		return this.cpf.equals(v.cpf);
	}
	
	@Override
	public String toString() {
		return "Vendedor [CPF = " + this.cpf + ", Nome = " + this.nome + ", Sexo = " + this.sexo + ", Salario = " + this.salario + "]";
	}
}
