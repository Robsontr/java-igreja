import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import balance.values.Balance;
import bridge.values.Bridge;
import entry.log.EntryLog;
import members.Members;
import out.log.OutLog;

public class Application {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Balance balance = new Balance();
		Bridge bridge = new Bridge();

		int opcao = 1;

		while (opcao != 0) {
			System.out.println("-------DIGITE A OPÇÃO DESEJADA-------");
			System.out.println("1-Financeiro");
			System.out.println("2-Membros");
			System.out.println("0-Encerrar programa");

			opcao = sc.nextInt();

//INICIO PRIMEIRO BLOCO DE ESCOLHA
			if (opcao == 1) {
				System.out.println("-------FINANCEIRO------");
				System.out.println("1-Adicionar valores");
				System.out.println("2-Consultar caixa");
				System.out.println("3-Saída de valores");
				System.out.println("4-LOG entrada");
				System.out.println("5-LOG saída");
				opcao = sc.nextInt();

				if (opcao == 1) {
					char add = 's';
					while (add == 's' || add == 'S') {

						System.out.print("Data: ");
						LocalDate date = LocalDate.parse(sc.next(), dt);

						System.out.print("Título: ");
						sc.nextLine();
						String title = sc.nextLine();

						System.out.print("Valor a adicionar: ");
						double value = sc.nextDouble();

						EntryLog entryLog = new EntryLog(date, title, value);
						bridge.addLog(entryLog);

						System.out.printf("%s NO VALOR DE R$ %.2f ADICIONADO(A) COM SUCESSO!\n\n", title.toUpperCase(),
								value);

						balance.addValues(value);
						System.out.print("Deseja adicional mais algum valor? s/n ");
						add = sc.next().charAt(0);
						System.out.println();
					}
				}

				else if (opcao == 2) {
					System.out.println("---------------------------------");
					System.out.println(balance);
					System.out.println("---------------------------------");
				}

				else if (opcao == 3) {

					char add = 's';
					while (add == 's' || add == 'S') {

						System.out.print("Valor a retirar: ");
						double value = sc.nextDouble();

						if (value > balance.getBalance()) {
							System.out.printf("SALDO ATUAL: R$ %.2f\n", balance.getBalance());
							System.out.println("VOCÊ NÃO POSSUI SALDO ATUALMENTE\n");
						} else {
							System.out.print("Motivo da retirada: ");
							sc.nextLine();
							String title = sc.nextLine();

							System.out.printf("Saldo anterior: R$ %.2f\n", balance.getBalance());

							balance.removeValue(value);

							System.out.printf("Valor removido: R$ %.2f\n", value);
							System.out.printf("Saldo Atualizado: R$ %.2f\n\n", balance.getBalance());

							LocalDateTime date = LocalDateTime.now();

							OutLog outLog = new OutLog(date, title, value);
							bridge.addLogOut(outLog);

						}
						System.out.println("DESEJA OUTRA OPERAÇÃO? S/N");
						add = sc.next().charAt(0);
					}
				} else if (opcao == 4) {
					System.out.println("-------LOG DE ENTRADA DE VALORES-------");
					System.out.println(bridge.getEl());
				} else if (opcao == 5) {
					System.out.println("-------LOG DE SAÍDA DE VALORES-------");
					System.out.println(bridge.getOl());

				}

			}
//FINAL PRIMEIRO BLOCO DE ESCOLHA			

//INICIO SEGUNDO BLOCO DE ESCOLHA
			else if (opcao == 2) {
				System.out.println("1-Cadastrar Membros");
				System.out.println("2-Consulta Membros");
				System.out.println("3-Editar Membro");

				int opcao2 = sc.nextInt();

				if (opcao2 == 1) {
					System.out.println("---------CADASTRO DE MEMBROS---------");

					char add = 's';
					while (add == 's' || add == 'S') {

						sc.nextLine();

						System.out.print("NOME: ");
						String name = sc.nextLine();

						System.out.print("DATA NASCIMENTO: ");
						LocalDate age = LocalDate.parse(sc.next(), dt);

						System.out.print("MINISTÉRIO: ");
						sc.nextLine();
						String position = sc.nextLine();

						System.out.print("STATUS: ");
						String status = sc.next();

						Members members = new Members(name, age, position, status);
						bridge.addMembers(members);

						System.out.println("MEMBRO CADASTRADO COM SUCESSO!");
						System.out.println();

						System.out.println("DESEJA CADASTRAR MAIS MEMBROS? S/N ");
						add = sc.next().charAt(0);
					}
				} else if (opcao2 == 2) {

					System.out.println("-------MEMBROS-------");
					List<Members> members = bridge.getMembers();

					for (Members mb : members) {
						System.out.println("COD: " + mb.getId());
						System.out.println("NOME: " + mb.getName());
						System.out.println("IDADE: " + mb.getAge());
						System.out.println("MINISTÉRIO: " + mb.getPosition());
						System.out.println("STATUS: " + mb.getStatus());
						System.out.println("-------------------------------");
					}

				} else if (opcao2 == 3) {

					System.out.println("-------EDIÇÃO DE MEMBROS-------");
					System.out.println("1-Editar por ID");
					System.out.println("2-Editar por Nome");
					int opcao3 = sc.nextInt();

					if (opcao3 == 2) {
						sc.nextLine();
						System.out.print("DIGITE O NOME A SER LOCALIZADO: ");
						String editName = sc.nextLine();

// LAMBDA PARA LOCALIZAR SE O NOME EXISTE NA LISTA
						List<Members> memberEdit = bridge.getMembers().stream().filter(m -> {
							String[] names = m.getName().split(" ");
							if (names.length > 0) {
								return names[0].equalsIgnoreCase(editName);
							}
							return false;
						}).collect(Collectors.toList());

						if (!memberEdit.isEmpty()) {
							for (Members member : memberEdit) {
								System.out.println("COD: " + member.getId());
								System.out.println("NOME: " + member.getName());
								System.out.println("IDADE: " + member.getAge());
								System.out.println("CARGO: " + member.getPosition());
								System.out.println("STATUS: " + member.getStatus());

								System.out.println();

								System.out.println("QUAL INFORMAÇÃO DESEJA ALTERAR? ");
								System.out.println("1-NOME");
								System.out.println("2-DATA NASCIMENTO");
								System.out.println("3-CARGO");
								System.out.println("4-STATUS");
								System.out.println("0-NENHUMA");

								int atributEdit = sc.nextInt();

								switch (atributEdit) {

								case 1:
									System.out.println("DIGITE O NOVO NOME: ");
									sc.nextLine();
									String newName = sc.nextLine();
									member.setName(newName);
									break;

								case 2:
									System.out.print("DIGITE A NOVA DATA DE NASCIMENTO: ");
									sc.nextLine();
									LocalDate newAge = LocalDate.parse(sc.nextLine());
									member.setAge(newAge);
									break;

								case 3:
									System.out.print("DIGITE O NOVO CARGO: ");
									sc.nextLine();
									String newPosition = sc.nextLine();
									member.setPosition(newPosition);
									break;

								case 4:
									System.out.print("DIGITE O NOVO STATUS: ");
									sc.nextLine();
									String newStatus = sc.nextLine();
									member.setStatus(newStatus);
									break;

								case 0:
									System.out.println("NENHUMA EDIÇÃO FEITA!");
									break;

								case default:
									System.out.println("OPÇÃO INVALIDA!");
									break;

								}
							}

							System.out.println("ATUALIZAÇÃO REALIZADA COM SUCESSO!");

						} else {
							System.out.println("MEMBRO NAO LOCALIZADO!");
						}

						Members edited = memberEdit.get(0);

						System.out.println("COD: " + edited.getId());
						System.out.println("NOME: " + edited.getName());
						System.out.println("IDADE: " + edited.getAge());
						System.out.println("CARGO: " + edited.getPosition());
						System.out.println("STATUS: " + edited.getStatus());
						System.out.println("----------------------------");

					}

				} else {

					System.out.println("OPÇÃO INVÁLIDA!");
				}

			} else if (opcao < 0 || opcao > 2) {
				System.out.println("OPÇÃO INVÁLIDA!");
				System.out.println();
			}
//FINAL SEGUNDO BLOCO DE ESCOLHA
		}

		System.out.println("PROGRAMA ENCERRADO!");

		sc.close();
	}
}
