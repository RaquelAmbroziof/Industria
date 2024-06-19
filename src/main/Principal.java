package main;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Principal {

	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();

		// 3.1 Inserir todos os funcionários, na mesma ordem e informações da tabela
		// fornecida
		inserirFuncionarios(funcionarios);

		// 3.2 – Remover o funcionário “João” da lista
		removerFuncionario(funcionarios, "João");

		// 3.3 Imprimir todos os funcionários com formatação específica
		System.out.println("Funcionários:");
		imprimirFuncionarios(funcionarios);

		// 3.4 Aplicar aumento de 10% nos salários dos funcionários
		aplicarAumentoSalario(funcionarios, BigDecimal.valueOf(0.10));

		// 3.5 Agrupar os funcionários por função em um MAP
		Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);

		// 3.6 Imprimir os funcionários agrupados por função
		System.out.println("\nFuncionários agrupados por função:");
		imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

		// 3.8 Imprimir os funcionários que fazem aniversário nos meses 10 e 12
		System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
		imprimirAniversariantes(funcionarios, 10);
		imprimirAniversariantes(funcionarios, 12);

		// 3.9 Imprimir o funcionário com maior idade
		System.out.println("\nFuncionário mais velho:");
		imprimirFuncionarioMaisVelho(funcionarios);

		// 3.10 Imprimir a lista de funcionários por ordem alfabética
		System.out.println("\nFuncionários em ordem alfabética:");
		ordenarFuncionariosPorNome(funcionarios);
		imprimirFuncionarios(funcionarios);

		// 3.11 Imprimir o total dos salários dos funcionários
		imprimirTotalSalarios(funcionarios);

		// 3.12 Imprimir quantos salários mínimos cada funcionário ganha
		imprimirSalariosMinimos(funcionarios);

	}

	// 3.1 - Método para inserir todos os funcionários
	private static void inserirFuncionarios(List<Funcionario> funcionarios) {
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1961, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios
				.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios
				.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
	}

	// 3.2 - Método para remover um funcionário pelo nome
	private static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
	}

	// 3.3 - Método para imprimir todos os funcionários com formatação específica
	private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

		for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() + ", " +
                    funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", " +
                    "Salário: " + decimalFormat.format(funcionario.getSalario()) + ", " +
                    "Função: " + funcionario.getFuncao());
        }
	}

	// 3.4 - Método para aplicar aumento de salário a todos os funcionários
	private static void aplicarAumentoSalario(List<Funcionario> funcionarios, BigDecimal percentualAumento) {
		for (Funcionario funcionario : funcionarios) {
			BigDecimal aumento = funcionario.getSalario().multiply(percentualAumento);
			funcionario.setSalario(funcionario.getSalario().add(aumento));
		}
	}

	// 3.5 - Método para agrupar os funcionários por função em um Map
	private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
		return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
	}

	// 3.6 - Método para imprimir os funcionários agrupados por função
	private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

		funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println(funcao + ":");
            listaFuncionarios.forEach(funcionario -> System.out.println(funcionario.getNome() + ", " +
                    funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", " +
                    "Salário: " + decimalFormat.format(funcionario.getSalario()) + ", " +
                    "Função: " + funcionario.getFuncao()));
        });
	}

	// 3.8 - Método para imprimir os funcionários que fazem aniversário no mês
	// especificado
	private static void imprimirAniversariantes(List<Funcionario> funcionarios, int mes) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		funcionarios.stream().filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
				.forEach(funcionario -> System.out.println(
						funcionario.getNome() + ", Aniversário: " + funcionario.getDataNascimento().format(formatter)));
	}

	// 3.9 - Método para imprimir o funcionário com maior idade
	private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
		Optional<Funcionario> maisVelho = funcionarios.stream()
				.min(Comparator.comparing(Funcionario::getDataNascimento));

		maisVelho.ifPresent(funcionario -> {
			long idade = funcionario.getDataNascimento().until(LocalDate.now()).getYears();
			System.out.println(funcionario.getNome() + ", Idade: " + idade);
		});
	}

	// 3.10 - Método para ordenar os funcionários por nome
	private static void ordenarFuncionariosPorNome(List<Funcionario> funcionarios) {
		Collections.sort(funcionarios, new Comparator<Funcionario>() {
			@Override
			public int compare(Funcionario f1, Funcionario f2) {
				return f1.getNome().compareTo(f2.getNome());
			}
		});
	}

	// 3.11 - Método para calcular o total dos salários dos funcionários
	private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
		BigDecimal totalSalarios = BigDecimal.ZERO;

		for (Funcionario funcionario : funcionarios) {
			totalSalarios = totalSalarios.add(funcionario.getSalario());
		}

		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		System.out.println("\nTotal dos Salários dos Funcionários: " + decimalFormat.format(totalSalarios));
	}

	// 3.12 - Método para imprimir quantos salários mínimos cada funcionário ganha
	private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
		final double salarioMinimo = 1212.00;
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

		System.out.println("\nQuantidade de Salários Mínimos:");
		funcionarios.forEach(funcionario -> {
			double salariosMinimos = funcionario.getSalario().doubleValue() / salarioMinimo;
			System.out.println(funcionario.getNome() + ": " + decimalFormat.format(salariosMinimos));
		});
	}

}
