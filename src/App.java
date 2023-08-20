public class App {
    public static void main(String[] args) {
        AcessoDadosRepository dados = new AcessoDadosRepository();
        Consultas consultas = new Consultas(dados);
        System.out.println("Dia em que mais choveu no ano de 1980: ");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));
        System.out.println("Datas em que choveu mais de 90 milimetros");
        consultas.datasEmQueChouveuMaisDe(90)
            .forEach(System.out::println);
        
        // padrao strategy para tarefa 3:
        consultas.diasEmQue().forEach(System.out::println);
    }
}
