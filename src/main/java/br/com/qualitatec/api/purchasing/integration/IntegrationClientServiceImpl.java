package br.com.qualitatec.api.purchasing.integration;

import br.com.qualitatec.api.purchasing.dto.ProdutoResponse;
import br.com.qualitatec.api.purchasing.dto.Response;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class IntegrationClientServiceImpl implements IntegrationClientService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    IntegrationService integrationService;

    @Override
    public void productIntegration() {
        try {
            String url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            String res = response.body();
            res = "{\"produtos\":" + res + "}";

            ProdutoResponse produtoResponse = gson.fromJson(res, ProdutoResponse.class);

            produtoResponse.getProdutos().forEach(e -> produtoRepository.save(e));

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void compraIntegration() {
        try {
            String url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            String res = response.body();
            res = "{\"compras\":" + res + "}";

            Response resp = gson.fromJson(res, Response.class);

            integrationService.alimentarDadosCompras(resp);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
