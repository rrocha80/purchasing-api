package br.com.qualitatec.api.purchasing.integration;

import br.com.qualitatec.api.purchasing.dto.CompraDto;
import br.com.qualitatec.api.purchasing.dto.ProdutoDto;
import br.com.qualitatec.api.purchasing.dto.Response;
import br.com.qualitatec.api.purchasing.repository.CompraRepository;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import br.com.qualitatec.api.purchasing.service.CompraService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CompraService compraService;

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

            ProdutoDto produtoDto = gson.fromJson(res, ProdutoDto.class);

            produtoDto.getProdutos().forEach(e -> produtoRepository.save(e));

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

            compraService.alimentarDadosCompras(resp);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
