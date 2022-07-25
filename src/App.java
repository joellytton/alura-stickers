import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String urlNasa = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        String urlIMDB = "https://api.mocki.io/v2/549a5d8b/Top250Movies";

        buildGeradoraImagemIMDB(urlIMDB);
        buildGeradoraImagemNasa(urlNasa);
    }

    public static void buildGeradoraImagemIMDB(String url) throws Exception{
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudo extrator= new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

             String nomeArquivo = "assets/img/saida/"+conteudo.getTitulo()+".png";
            
             var geradora = new GeradoraDeFigurinhas();

             geradora.cria(inputStream, nomeArquivo);

             System.out.println(conteudo.getTitulo());
             System.out.println();
         }
    }

    public static void buildGeradoraImagemNasa(String url) throws Exception{
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudo extrator= new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

             String nomeArquivo = "assets/img/saida/"+conteudo.getTitulo()+".png";
            
             var geradora = new GeradoraDeFigurinhas();

             geradora.cria(inputStream, nomeArquivo);

             System.out.println(conteudo.getTitulo());
             System.out.println();
         }
    }
}
