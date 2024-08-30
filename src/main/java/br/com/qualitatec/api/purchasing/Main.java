package br.com.qualitatec.api.purchasing;

import br.com.qualitatec.api.purchasing.dto.ProdutoDto;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
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
            res="{\"produtos\":"+res+"}";

            ProdutoDto root = gson.fromJson(res, ProdutoDto.class);

            System.out.println("Resposta JSON");
            System.out.println(response.body());

            System.out.println("Resposta Objeto ");
            System.out.println(root);

            System.out.print("JSON novamente");
            System.out.print(gson.toJson(root));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
