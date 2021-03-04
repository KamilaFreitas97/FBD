package POJO;

public class clientePOJO {
	private String cpf;
	private String nome;
	private char sexo;
	
	public String getCPF() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public char getSexo() {
		return sexo;
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
	
	public clientePOJO(String cpf, String nome, char sexo) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
	}
	
	public boolean equals(clientePOJO c) {
		return c.cpf == this.cpf;
	}
	
	@Override
	public String toString() {
		return "Cliente [CPF = " + this.cpf + ", Nome = " + this.nome + ", Sexo = " + this.sexo + "]";
	}
}
