import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale; 
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import POJO.*;
import DAO.*;
//import JDBC.*;

public class Main {
	public static void main(String[] args) {
		carroDAO carDAO = new carroDAO();
		clienteDAO cliDAO = new clienteDAO();
		vendedorDAO vendDAO = new vendedorDAO();
		//estoqueDAO estDAO = new estoqueDAO();
		emprestimoDAO emprDAO = new emprestimoDAO();
		historicoDAO histDAO = new historicoDAO();
		
		int option;
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.ENGLISH);
		boolean end = false;

		while(!end) {
			System.out.println("|  1  | Cadastrar vendedor");
			System.out.println("|  2  | Cadastrar carro");
			System.out.println("|  3  | Cadastrar cliente");
			System.out.println("|  4  | Deletar vendedor");
			System.out.println("|  5  | Deletar carro");
			System.out.println("|  6  | Deletar cliente");
			System.out.println("|  7  | Atualizar carro");
			System.out.println("|  8  | Realizar emprestimo");
			System.out.println("|  9  | Devolver carro");
			System.out.println("|  10 | Visualizar registros");
			System.out.println("|  11 | Calcular saldo");
			System.out.println("|  0  | Sair");
			
			option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
			case 1: {
				String cpf, nome, senha;
				char sexo;
				float salario;
				
				System.out.println("Digite o nome do vendedor:");
				nome = scanner.nextLine();
				System.out.println("Digite o CPF do vendedor:");
				cpf = scanner.nextLine();
				System.out.println("Digite o sexo do vendedor:");
				sexo = scanner.nextLine().charAt(0);
				System.out.println("Digite a senha do vendedor:");
				senha = scanner.nextLine();
				System.out.println("Digite o salario do vendedor:");
				salario = scanner.nextFloat();
				
				vendedorPOJO v = new vendedorPOJO(cpf, nome, sexo, senha, salario);
				
				if(vendDAO.addVendedor(v)) {
					System.out.println("Inserido com sucesso!");
				} else {
					System.err.println("Erro ao inserir o vendedor.");
				}
				break;
			}
			case 2: {
				String placa, modelo;
				float preco;
				
				System.out.println("Digite a placa do carro:");
				placa = scanner.nextLine();
				System.out.println("Digite o modelo do carro:");
				modelo = scanner.nextLine();
				System.out.println("Digite o preco do carro:");
				preco = scanner.nextFloat();
				
				carroPOJO c = new carroPOJO(placa, modelo, preco);
				
				if(carDAO.addCarro(c)) {
					System.out.println("Inserido com sucesso!");
				} else {
					System.err.println("Erro ao inserir.");
				}
				break;
			}
			case 3: {
				String cpf, nome;
				char sexo;
				
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.nextLine();
				System.out.println("Digite o nome do cliente:");
				nome = scanner.nextLine();
				System.out.println("Digite o sexo do cliente:");
				sexo = scanner.nextLine().charAt(0);
				
				clientePOJO c = new clientePOJO(cpf, nome, sexo);
				
				if(cliDAO.addCliente(c)) {
					System.out.println("Inserido com sucesso!");
				} else {
					System.err.println("Erro ao inserir.");
				}
				break;
			}
			case 4: {
				String cpf, senha;
				
				System.out.println("Digite o CPF do vendedor: ");
				cpf = scanner.nextLine();
				System.out.println("Digite a senha do vendedor: ");
				senha = scanner.nextLine();
				
				if(vendDAO.deleteVendedor(cpf, senha)) {
					System.out.println("Deletado com sucesso!");
				} else {
					System.err.println("Erro ao deletar.");
				}
				break;
			}
			case 5: {
				String placa;
				
				System.out.println("Digite a placa do carro: ");
				placa = scanner.nextLine();
				
				if(carDAO.deleteCarro(placa)) {
					System.out.println("Deletado com sucesso!");
				} else {
					System.err.println("Erro ao deletar.");
				}
				break;
			}
			case 6: {
				String cpf;
				
				System.out.println("Digite o CPF do cliente: ");
				cpf = scanner.nextLine();
				
				if(cliDAO.deleteCliente(cpf)) {
					System.out.println("Deletado com sucesso!");
				} else {
					System.err.println("Erro ao deletar.");
				}
				break;
			}
			case 7: {
				String placa;
				float preco;
				
				System.out.println("Entre com a placa do carro: ");
				placa = scanner.nextLine();
				System.out.println("Entre com o novo preco: ");
				preco = scanner.nextFloat();
				
				if(carDAO.updateCarroPreco(placa, preco)) {
					System.out.println("Carro atualizado com sucesso!");
				} else {
					System.out.println("Erro ao atualizar valor do carro!");
				}
				break;
			}
			case 8: {
				String cpf_cliente, cpf_vendedor, senha, placa_carro;
				String data;
				int dias_escolhidos;
				float juros = 0;
				
				System.out.println("Digite o CPF do vendedor: ");
				cpf_vendedor = scanner.nextLine();
				System.out.println("Digite a senha do vendedor: ");
				senha = scanner.nextLine();
				
				if(vendDAO.getVendedorByCPF(cpf_vendedor, senha) == null) {
					System.err.println("Vendedor nao encontrado!");
					break;
				}
				
				System.out.println("Digite o cpf do cliente: ");
				cpf_cliente = scanner.nextLine();
				System.out.println("Digite a placa do carro");
				placa_carro = scanner.nextLine();
				
				carroPOJO cp = carDAO.getCarroPlaca(placa_carro);
				
				if(cp == null) {
					System.err.println("Carro nao disponivel.");
					break;
				}
				
				System.out.println("Entre com a data do emprestimo: ");
				data = scanner.nextLine();
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dd = null;
				try {
					dd = df.parse(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date datafinal = new Date(dd.getTime());
				
				System.out.println("Entre com a quantidade de dias escolhidos pelo cliente: ");
				dias_escolhidos = scanner.nextInt();
				
				emprestimoPOJO emp = new emprestimoPOJO(cpf_cliente, placa_carro, datafinal, dias_escolhidos, cpf_vendedor, juros, cp.getPreco(), false);
				
				if(emprDAO.addEmprestimo(emp)) {
					System.out.println("Emprestimo realizado com sucesso!");
				} else {
					System.err.println("Erro inesperado ao realizar emprestimo!");
				}
				break;
			}
			case 9: {
				String cpf_cliente, cpf_vendedor, senha, placa_carro;
				String data;
				int dias_ficados;
				float preco = 0;
				
				System.out.println("Digite o CPF do vendedor: ");
				cpf_vendedor = scanner.nextLine();
				System.out.println("Digite a senha do vendedor: ");
				senha = scanner.nextLine();
				
				if(vendDAO.getVendedorByCPF(cpf_vendedor, senha) == null) {
					System.err.println("Vendedor nao encontrado!");
					break;
				}
				
				System.out.println("Digite o cpf do cliente: ");
				cpf_cliente = scanner.nextLine();
				System.out.println("Digite a placa do carro");
				placa_carro = scanner.nextLine();
				
				carroPOJO cp = carDAO.getCarroPlaca(placa_carro);
				
				if(cp == null) {
					System.err.println("Carro nao disponivel.");
					break;
				}
				
				System.out.println("Entre com a data da devolucao: ");
				data = scanner.nextLine();
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dd = null;
				try {
					dd = df.parse(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date datafinal = new Date(dd.getTime());
				
				//System.out.println("Entre com a quantidade de dias ficados pelo cliente: ");
				dias_ficados = 0;
				
				emprestimoPOJO temp = emprDAO.getEmprestimoByCPFPLACA(cpf_cliente, placa_carro);
				
				if(temp == null) {
					System.err.println("Emprestimo nao realizado.");
					break;
				}
				
				/*
				if(temp.getJuros() <= 0) {
					preco = temp.getPreco_base();
				} else {
					preco = temp.getJuros() * temp.getPreco_base();
				}
				*/
				historicoPOJO histp = new historicoPOJO(cpf_cliente, placa_carro, datafinal, dias_ficados, cpf_vendedor, preco);
				
				if(histDAO.addHistorico(histp)) {
					//if(histDAO.updatePrecoFinal(cpf_cliente, placa_carro, datafinal, preco))
						System.out.println("Devolucao realizada com sucesso.");
					//else
						//System.err.println("Erro inesperado ao realizar devolucao.");
				} else {
					System.err.println("Erro inesperado ao realizar devolucao.");
				}
				break;
			}
			case 10: {
				int op;
				boolean end2 = false;
				
				while(!end2) {
					System.out.println("|  1  | Ver carros do estoque");
					System.out.println("|  2  | Ver clientes registrados");
					System.out.println("|  3  | Ver vendedores registrados");
					System.out.println("|  4  | Registros de um cliente");
					System.out.println("|  5  | Emprestimos nao devolvidos");
					System.out.println("|  6  | Todos os registros");
					System.out.println("|  0  | Voltar");
					op = scanner.nextInt();
					scanner.nextLine();
					
					switch(op) {
					case 1: {
						int check = 0;
						ArrayList<carroPOJO> ep = carDAO.getAllEstoque();

						for(carroPOJO aux : ep) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.out.println("Nenhum carro disponivel!");
						}
	
						break;
					}
					
					case 2: {
						int check = 0;
						ArrayList<clientePOJO> cp = cliDAO.getClienteLista();

						for(clientePOJO aux : cp) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.out.println("Nenhum cliente cadastrado!");
						}

						break;
					}
					case 3: {
						int check = 0;
						ArrayList<vendedorPOJO> vp = vendDAO.getListaVendedores();

						for(vendedorPOJO aux : vp) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.out.println("Nenhum vendedor cadastrado!");
						}

						break;
					}
					case 4: {
						int check = 0;
						String cpf;

						System.out.println("Digite o CPF do cliente: ");
						cpf = scanner.nextLine();

						ArrayList<emprestimoPOJO> ep = emprDAO.getRegistroByCPF(cpf);
						ArrayList<historicoPOJO> ep2 = histDAO.getRegistroByCPF(cpf);

						for(emprestimoPOJO aux : ep) {
							System.out.println(aux.toString());
							check++;
						}

						for(historicoPOJO aux : ep2) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.err.println("Cliente nao encontrado!");
						}

						break;
					}
					case 5: {
						int check = 0;

						ArrayList<emprestimoPOJO> ep = emprDAO.getEmprestimoNaoDevolvidos();

						for(emprestimoPOJO aux : ep) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.out.println("Lista de emprestimos pendentes vazia!");
						}

						break;
					}
					case 6: {
						int check = 0;

						ArrayList<emprestimoPOJO> ep = emprDAO.getListaEmprestimos();
						ArrayList<historicoPOJO> ep2 = histDAO.getListaHistorico();

						for(emprestimoPOJO aux : ep) {
							System.out.println(aux.toString());
							check++;
						}

						for(historicoPOJO aux : ep2) {
							System.out.println(aux.toString());
							check++;
						}

						if(check == 0) {
							System.out.println("Nenhum registro encontrado!");
						}

						break;
					}
					default: {
						end2 = true;
						break;
					}
					}
				}
				break;
			}
			case 11: {
				String cpf;
				
				System.out.println("Digite o CPF do cliente");
				cpf = scanner.nextLine();
				
				clientePOJO cp = cliDAO.getClienteByCPF(cpf);
				
				if(cp == null) {
					System.out.println("Cliente nao encontrado!");
					break;
				}
				
				float preco = cliDAO.getSaldo(cp);
				
				System.out.println("Valor pendente: ");
				System.out.println(preco);
				
				break;
			}
			default: {
				scanner.close();
				end = true;
				break;
			}
			}
		}
	}

}