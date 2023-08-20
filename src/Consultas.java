import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Consultas {
    private AcessoDadosRepository dados;
    private List<RegistroDoTempo> registros;

    public Consultas(AcessoDadosRepository dados){
        this.dados = dados;
        registros = new LinkedList<>();
        registros = dados.getAll();
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
}
