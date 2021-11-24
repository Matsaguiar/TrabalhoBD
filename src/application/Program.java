package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import java.util.InputMismatchException;

import model.dao.DaoAplication;
import model.dao.DaoCartao;
import model.dao.DaoEstadio;
import model.dao.DaoGerente;
import model.dao.DaoIngresso;
import model.dao.DaoPartida;
import model.dao.DaoTelefone;
import model.dao.DaoTipoEsporte;
import model.dao.DaoUsuario;
import model.entities.Cartao;
import model.entities.Estadio;
import model.entities.Gerente;
import model.entities.Ingresso;
import model.entities.Partida;
import model.entities.Telefone;
import model.entities.TipoEsporte;
import model.entities.Usuario;


public class Program {


	public static void main(String[] args) throws ParseException{
		
		Scanner sc = new Scanner(System.in);
		int entrada=-1;
		
		DaoUsuario daoUsuario = DaoAplication.createDaoUsuario();
		DaoEstadio daoEstadio = DaoAplication.createDaoEstadio();
		DaoCartao daoCartao = DaoAplication.createDaoCartao();
		DaoPartida daoPartida = DaoAplication.createDaoPartida();
		DaoTipoEsporte daoTipoEsporte = DaoAplication.createDaoTipoEsporte();
		DaoGerente daoGerente = DaoAplication.createDaoGerente();
		DaoIngresso daoIngresso = DaoAplication.createDaoIngresso();
		DaoTelefone daoTelefone = DaoAplication.createDaoTelefone();
		
		System.out.println("BEM VINDO!!!");
		while(entrada!=0) {
			System.out.print("O que deseja fazer?\n"
					+ "1 - Area do usuario\n"
					+ "2 - Area do gerente\n"
					+ "3 - Partidas disponiveis\n"
					+ "4 - Cadastrar/Visualizar os Tipos de Esporte\n"
					+ "5 - Cadastrar/Visualizar Estadio\n"
					+ "6 - Receber um arquivo externo\n"
					+ "0 - Sair do Programa\n"
					+ "Sua Escolha: ");
			entrada = sc.nextInt();
			switch(entrada) {
			case 1:
				int area_usuario=-1;
				System.out.print("\nArea do usuario\n"
						+ "\n1 - Cadastrar usuario\n"
						+ "2 - Alterar senha do usuario\n"
						+ "3 - Cadastrar/Excluir um Cartao de Credito\n"
						+ "4 - Cadastrar/Excluir telefone\n"
						+ "0 - Voltar\n"
						+ "Sua Escolha: ");
				area_usuario = sc.nextInt();
				Usuario usuarioA = null;
				if(area_usuario == 1) {
					sc.nextLine();
					System.out.print("\nDigite o seu CPF: ");
					String cpf = sc.nextLine();
					if(ValidaCPF.isCPF(cpf)) {
						usuarioA = daoUsuario.findByCpf(cpf);
						if(usuarioA == null) {
							System.out.print("\nDigite o seu nome: ");
							String nome = sc.nextLine();
							System.out.print("\nDigite a sua nova senha: ");
							String senha = sc.nextLine();
							System.out.print("\nDigite a sua idade: ");
							int idade = sc.nextInt();
							Usuario usuario = new Usuario(cpf, senha, nome, idade);
							daoUsuario.insert(usuario);
							System.out.println("\nUsuario inserido com sucesso!\n");
						}else
							System.out.println("\nUsuario ja cadastrado\n");
					}else
						System.out.println("\nCPF invalido!\n");
				}else if(area_usuario == 2) {
					System.out.print("\nAlteracao de senha");
					sc.nextLine();
					System.out.print("\nDigite o seu CPF: ");
					String cpf = sc.nextLine();
					usuarioA = daoUsuario.findByCpf(cpf);
					if(usuarioA != null) {
						System.out.print("\nDigite a sua senha antiga: ");
						String senha = sc.nextLine();
						while(!usuarioA.getSenha().equals(senha.intern())) {
							System.out.println("\nSenha invalida!");
							System.out.print("\nDigite novamente sua senha antiga: ");
							senha = sc.nextLine();
						}
						System.out.print("\nDigite sua nova senha: ");
						senha = sc.nextLine();
						usuarioA.setSenha(senha);
						daoUsuario.update(usuarioA);
						System.out.println("\nSenha atualizada com sucesso!\n");
					}else 
						System.out.println("\nEsse CPF nao esta cadastrado!\n");
					
				}else if(area_usuario == 3) {
					System.out.println("\n1 - Cadastrar cartao de credito\n"
							+ "2 - Excluir cartao de credito\n"
							+ "Sua Escolha: ");
					int escol = sc.nextInt();
					switch(escol) {
					case 1:
						System.out.print("\nCadastrar cartao de credito");
						sc.nextLine();
						System.out.print("\nDigite o seu CPF: ");
						String cpf = sc.nextLine();
						usuarioA = daoUsuario.findByCpf(cpf);
						System.out.print("\nDigite a sua senha: ");
						String senha = sc.nextLine();
						if(usuarioA != null && usuarioA.getSenha().equals(senha.intern())) {
							int id = usuarioA.getId_usuario();
							System.out.print("\nDigite o numero do cartao: ");
							String numero = sc.nextLine();
							System.out.print("\nDigite a validade do cartao: ");
							String validade = sc.nextLine();
							System.out.print("\nDigite o codigo de segurança: ");
							String codSeg = sc.nextLine();
							Cartao cartao = new Cartao(id, numero, validade, codSeg);
							daoCartao.insert(cartao);
							System.out.println("\nCartao inserido com sucesso\n");
						}else 
							System.out.println("\nLogin invalido!\n");
						break;
					case 2: 						
						System.out.print("\nExcluir um cartao de credito");
						sc.nextLine();
						System.out.print("\nDigite o seu CPF: ");
						cpf = sc.nextLine();
						usuarioA = daoUsuario.findByCpf(cpf);
						System.out.print("\nDigite a sua senha: ");
						senha = sc.nextLine();
						if(usuarioA != null && usuarioA.getSenha().equals(senha.intern())) {
							List<Cartao> cartoes = daoCartao.findByIdUsuario(usuarioA.getId_usuario());
							System.out.println("\nSeus cartões de credito:\n");
							for(Cartao j : cartoes) {
								System.out.println(j);
							}
							System.out.println("\nDigite o id do cartao que voce deseja excluir: ");
							int cartao = sc.nextInt();
							daoCartao.deleteById(cartao);
							System.out.println("\nCartao excluido com sucesso!\n");
						}else 
							System.out.println("\nLogin invalido!\n");
						break;
					}
				}else if(area_usuario == 4) {
					System.out.println("\n1 - Cadastrar telefone\n"
							+ "2 - Excluir telefone\n"
							+ "Sua Escolha: ");
					int esco = sc.nextInt();
					switch(esco) {
					case 1:
						System.out.print("\nCadastrar telefone");
						sc.nextLine();
						System.out.print("\nDigite o seu CPF: ");
						String cpf = sc.nextLine();
						usuarioA = daoUsuario.findByCpf(cpf);
						System.out.print("\nDigite a sua senha: ");
						String senha = sc.nextLine();
						if(usuarioA != null && usuarioA.getSenha().equals(senha.intern())) {
							int id = usuarioA.getId_usuario();
							System.out.print("\nDigite o numero do telefone: ");
							String numero = sc.nextLine();
							Telefone fone = new Telefone(id, numero);
							daoTelefone.insert(fone);
							System.out.println("\nTelefone inserido com sucesso\n");
						}else 
							System.out.println("\nLogin invalido!\n");
						break;
					case 2:
						System.out.print("\nExcluir um telefone");
						sc.nextLine();
						System.out.print("\nDigite o seu CPF: ");
						cpf = sc.nextLine();
						usuarioA = daoUsuario.findByCpf(cpf);
						System.out.print("\nDigite a sua senha: ");
						senha = sc.nextLine();
						if(usuarioA != null && usuarioA.getSenha().equals(senha.intern())) {
							System.out.println("\nSeus telefones cadastrados:\n");
							daoTelefone.findAll(usuarioA.getId_usuario());
							System.out.println("\nDigite o telefone que voce deseja excluir: ");
							String f = sc.nextLine();
							daoTelefone.deleteByFone(f);
							System.out.println("\nTelefone excluido com sucesso!\n");
						}else 
							System.out.println("\nLogin invalido!\n");
						break;
					}
				}
				break;
			case 2: //2 - Area do gerente
				int area_gerente=-1;
				System.out.print("\nArea do gerente\n"
						+ "\n1 - Cadastrar partida\n"
						+ "2 - Alterar data da partida\n"
						+ "3 - Excluir partida\n"
						+ "4 - Mostrar suas partidas\n"
						+ "0 - Voltar\n"
						+ "Sua Escolha: ");
				area_gerente = sc.nextInt();
				if(area_gerente != 0) {
					usuarioA = null;
					sc.nextLine();
					System.out.print("\nDigite o seu CPF: ");
					String cpf = sc.nextLine();
					usuarioA = daoUsuario.findByCpf(cpf);
					System.out.print("\nDigite a sua senha: ");
					String senha = sc.nextLine();
					if(usuarioA != null && usuarioA.getSenha().equals(senha.intern())) {
						if(area_gerente == 1) {
							int id = usuarioA.getId_usuario();
							System.out.print("\nDigite o codigo da partida: ");
							int codigo = sc.nextInt();
							sc.nextLine();
							System.out.print("\nDigite o nome da partida: ");
							String nome = sc.nextLine();
							System.out.print("\nDigite o id do esporte da partida: ");
							int esporte = sc.nextInt();
							sc.nextLine();
							System.out.print("\nDigite a data da partida (dd/mm/yy): ");
							String data = sc.nextLine();
							System.out.print("\nDigite o horario da partida (hh:mm): ");
							String hora = sc.nextLine();
							System.out.print("\nDigite o id do estadio da partida: ");
							int estadio = sc.nextInt();
							System.out.print("\nDigite o preço do ingresso da partida: ");
							int ingre = sc.nextInt();
							Partida partida = new Partida(codigo, nome, esporte, data, hora, estadio, ingre);
							daoPartida.insert(partida);
							Gerente gerente = new Gerente(id, codigo);
							daoGerente.insert(gerente);
							Ingresso ingressoo = new Ingresso(codigo, 1000, estadio);
							daoIngresso.insert(ingressoo);
							System.out.println("\nPartida cadastrada com sucesso!\n");
						}else if(area_gerente == 2) {
							
							int id = usuarioA.getId_usuario();
							System.out.print("\nDigite o codigo da partida para alteração: ");
							int codigo = sc.nextInt();
							
							Gerente gerenteA = daoGerente.findById_partida(codigo);
							
							if(gerenteA.getId_usuario() == id) {
								Partida partidaA = daoPartida.findById_partida(codigo);
								sc.nextLine();
								System.out.print("\nDigite a nova data da partida (dd/mm/yy): ");
								String ndata = sc.nextLine();
								System.out.print(ndata);
								partidaA.setData_partida(ndata);
								daoPartida.update(partidaA);
								System.out.println("\nData alterada com sucesso!\n");
							}else
								System.out.println("\nVocê não é o gerente dessa partida!\n");
						}else if(area_gerente == 3) {
							int id = usuarioA.getId_usuario();
							System.out.println("\nDigite o id da partida que voce deseja excluir: ");
							int codigo = sc.nextInt();
							Gerente gerenteA = daoGerente.findById_partida(codigo);
							if(gerenteA.getId_usuario() == id) {
								daoGerente.deleteById(codigo);
								daoPartida.deleteById(codigo);
								daoIngresso.deleteById(codigo);
								System.out.println("\nPartida excluida com sucesso!\n");
							}else
								System.out.println("\nVocê não é o gerente dessa partida!\n");
							
						}else if(area_gerente == 4) {
							int id = usuarioA.getId_usuario();
							List<Partida> partida = daoPartida.findAll(id);
							System.out.println("\nSuas Partidas:\n");
							for(Partida j : partida) {
								System.out.println(j); 
							}
							System.out.print("\n");
						}
					}else 
						System.out.println("\nLogin invalido!\n");
				} 
				break;
			case 3:
				System.out.println("\nPartidas disponiveis\n"
						+ "1 - Todas as partidas\n"
						+ "2 - Filtrar partidas por estadio\n"
						+ "3 - Filtrar partidas por esporte\n"
						+ "4 - Filtrar partidas por estado\n"
						+ "Sua Escolha: ");
				int escolha2 = sc.nextInt();
				switch(escolha2) {
				case 1:	
					List<Partida> todasPartidas = daoPartida.todasPartidas();
					System.out.println("\nTodas as partidas:\n");
					for(Partida j : todasPartidas) {
						System.out.println(j);
					}
					System.out.print("\n");
					break;
				case 2:
					sc.nextLine();
					System.out.print("\nDigite o nome do Estadio: ");
					String nome_estadio = sc.nextLine();
					List<Partida> partEstadio = daoPartida.findByEstadio(nome_estadio);
					for(Partida q : partEstadio) {
						System.out.println(q);
					}
					System.out.print("\n");
					break;
				case 3:
					sc.nextLine();
					System.out.print("\nDigite o nome do Esporte: ");
					String nome_esporte = sc.nextLine();
					List<Partida> partEsporte = daoPartida.findByEsporte(nome_esporte);
					for(Partida h : partEsporte) {
						System.out.println(h);
					}
					System.out.print("\n");
					break;
				case 4:
					sc.nextLine();
					System.out.print("\nDigite o nome do Estado: ");
					String estado = sc.nextLine();
					List<Partida> partEstado = daoPartida.findByEstado(estado);
					for(Partida w : partEstado) {
						System.out.println(w);
					}
					System.out.print("\n");
					break;
				}
				break;
			case 4:
				System.out.println("\n1 - Cadastrar Esporte\n"
						+ "2 - Vizualizar Esportes\n"
						+ "Sua Escolha: ");
				int escolha3 = sc.nextInt();
				switch(escolha3) {
				case 1:	
					sc.nextLine();
					System.out.print("\nInsira o nome do esporte: ");
					String nome = sc.nextLine();
					TipoEsporte esporte = new TipoEsporte(nome);
					daoTipoEsporte.insert(esporte);
					System.out.println("\nEsporte inserido com sucesso\n");
					break;
				case 2:
					System.out.print("\nLista de esportes cadastrados:\n");
					
					List<TipoEsporte> listaEsporte = daoTipoEsporte.findAll();
					
					for(TipoEsporte t : listaEsporte) {
						System.out.println(t);
					}
					System.out.println("\n");
					break;
				}
				break;
				
			case 5:
				System.out.println("\n1 - Cadastrar Estadio\n"
						+ "2 - Vizualizar Estadios\n"
						+ "Sua Escolha: ");
				int escolha4 = sc.nextInt();
				switch(escolha4) {
				case 1:	
					sc.nextLine();
					System.out.print("\nInsira o nome do estadio: ");
					String nomeEst = sc.nextLine();
					System.out.print("\nInsira o estado do estadio: ");
					String estado = sc.nextLine();
					System.out.print("\nInsira a cidade do estadio: ");
					String cidade = sc.nextLine();
					Estadio estadio = new Estadio(nomeEst, 1000, estado, cidade);
					daoEstadio.insert(estadio);
					System.out.println("\nEstadio inserido com sucesso\n");
					break;
				case 2:
					System.out.print("\nLista de estadios cadastrados:\n");
					
					List<Estadio> listaEstadio = daoEstadio.findAll();
					
					for(Estadio k : listaEstadio) {
						System.out.println(k);
					}
					System.out.println("\n");
				
					break;
		
				}
				break;
			case 6:
				
				sc.nextLine();
				System.out.print("Digite o caminho do arquivo: ");
				String sourceFileStr = sc.nextLine();
				
				try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
					
					String itemCsv = br.readLine();
					while(itemCsv != null) {
						
						String[] fields = itemCsv.split(",");
						String nomeU = fields[0];
						String cpfU = fields[1];
						String senhaU = fields[2];
						int idadeU = Integer.parseInt(fields[3]);
						
						Usuario usuarioArquivo = new Usuario(cpfU, senhaU, nomeU, idadeU);
						daoUsuario.insert(usuarioArquivo);
						
						itemCsv = br.readLine();
					}
				}catch(IOException e) {
					System.out.println("ERROR READING FILE: "+ e.getMessage());
				}
				break;
			}
		}
		
		
		sc.close();
	}
	
	
	static class ValidaCPF {
	
	    public static boolean isCPF(String CPF) {
	        // considera-se erro CPF's formados por uma sequencia de numeros iguais
	        if (CPF.equals("00000000000") ||
	            CPF.equals("11111111111") ||
	            CPF.equals("22222222222") || CPF.equals("33333333333") ||
	            CPF.equals("44444444444") || CPF.equals("55555555555") ||
	            CPF.equals("66666666666") || CPF.equals("77777777777") ||
	            CPF.equals("88888888888") || CPF.equals("99999999999") ||
	            (CPF.length() != 11))
	            return(false);
	
	        char dig10, dig11;
	        int sm, i, r, num, peso;
	
	        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	        try {
	        // Calculo do 1o. Digito Verificador
	            sm = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {
	        // converte o i-esimo caractere do CPF em um numero:
	        // por exemplo, transforma o caractere '0' no inteiro 0
	        // (48 eh a posicao de '0' na tabela ASCII)
	            num = (int)(CPF.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }
	
	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
	
	        // Calculo do 2o. Digito Verificador
	            sm = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
	            num = (int)(CPF.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }
	
	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                 dig11 = '0';
	            else dig11 = (char)(r + 48);
	
	        // Verifica se os digitos calculados conferem com os digitos informados.
	            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	                 return(true);
	            else return(false);
	                } catch (InputMismatchException erro) {
	                return(false);
	            }
	        }

	}
}