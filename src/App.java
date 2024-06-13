import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
        // acima.
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
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

        // funcionarios.forEach(System.out::println);

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com suas informações
        System.out.println("\n## LISTA DE FUNCIONÁRIOS");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentar o salário dos funcionários em 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));
        funcionarios.forEach(System.out::println);

        // 3.5 - Agrupar os funcionários por função em um MAP
        System.out.println("\n## FUNCIONÁRIOS POR FUNÇÃO");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, funcionariosFuncao) -> {
            System.out.println("\n############# " + funcao.toUpperCase() + " #############");
            funcionariosFuncao.forEach(System.out::println);
        });

        // 3.6 - Imprimir os funcionários que fazem aniversário no mês 10 ou 12
        System.out.println("\n## FUNCIONÁRIOS ANIVERSARIANTES E OUTUBRO E DEZEMBRO");
        List<Funcionario> aniversariantesOutubroDezembro = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                        f.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        if (aniversariantesOutubroDezembro.isEmpty()) {
            System.out.println("Não há funcionários com aniversário em outubro ou dezembro.");
        } else {
            aniversariantesOutubroDezembro.forEach(System.out::println);
        }

        // 3.7 - Imprimir o funcionário com a maior idade
        System.out.println("\n## FUNCIONARIO COM MAIOR IDADE");
        Funcionario funcionarioMaisIdade = funcionarios.stream()
                .min(Comparator.comparingInt(f -> f.getDataNascimento().getYear()))
                .orElse(null);

        System.out.println(funcionarioMaisIdade.getNome() + " tem "
                + calcularIdade(funcionarioMaisIdade.getDataNascimento()) + " anos");

        // 3.8 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n## FUNCIONÁRIOS POR ORDEM ALFABÉTICA");
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        funcionariosOrdenados.forEach(System.out::println);

        // 3.9 - Imprimir o total dos salários dos funcionários
        System.out.println("\n## TOTAL DA FOLHA DE PAGAMENTO");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("R$" + totalSalarios.setScale(2,
                BigDecimal.ROUND_HALF_UP).toString().replace(".", ","));

        // 3.10 - Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\n## QUANTIDADE DE SALÁRIOS MÍNIMOS POR FUNCIONÁRIOS");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salário mínimo vigente: R$"
                + salarioMinimo.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".", ","));

        funcionarios.forEach(f -> {
            int salariosMinimos = f.getSalario().divideToIntegralValue(salarioMinimo).intValue();
            String textoSalarioMinimo;
            if (salariosMinimos <= 1)
                textoSalarioMinimo = " salário mínimo.";
            else
                textoSalarioMinimo = " salários mínimos.";
            System.out.println(f.getNome() + " ganha " + salariosMinimos + textoSalarioMinimo);
        });

    }

    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.getYear() - dataNascimento.getYear();
    }
}
