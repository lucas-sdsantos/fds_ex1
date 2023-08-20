import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Consultas implements IStrategy{
    private AcessoDadosRepository dados;
    private List<RegistroDoTempo> registros;
    private Predicate<RegistroDoTempo> consulta;

    public Consultas(AcessoDadosRepository dados){
        this.dados = dados;
        registros = new LinkedList<>();
        registros = dados.getAll();
        
        // definindo padrao da strategy: os dias dos anos que tiveram mais de 11 horas de sol
        consulta = i->i.getHorasInsolacao() >= 11;
    }

    public List<String> datasEmQueChouveuMaisDe(double milimetros){
        return registros
            .stream()
            .filter(r->r.getPrecipitacao() > milimetros)
            .map(r->r.getDia()+"/"+r.getMes()+"/"+r.getAno())
            .toList();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = registros
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacao();
        return resp;
    }

    public List<Data> diasEmQue() {
        return registros
            .stream()
            .filter(consulta)
            .map(r->r.getData())
            .toList();
    }

    public void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta) {

    }
}
