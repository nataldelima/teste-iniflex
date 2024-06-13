import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "\nNome: " + getNome() + "\nData de nascimento: "
                + getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nSalário: R$"
                + getSalario().setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".", ",") + "\nFunção: "
                + getFuncao() + "\n-------------------------------------";
    }

}
