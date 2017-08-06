# Back-end code challenge ![alt Batlogo](public/images/batlogo-small.png)

<b>O homem morcego precisa de sua ajuda!</b>

Você deve criar um algoritmo para o supercomputador da Batcaverna que deve ser capaz de calcular a probabilidade do arqui-inimigo Coringa atacar as imediações de Gotham.

Este algorítimo deve esperar um endereço, uma localidade ou uma coordenada (a torre do relógio ou a posiçao atual do Coringa, por exemplo). Quando o input for um endereço ou uma localidade, você deve transformá-lo em uma coordenada (utilizando Google, Yahoo, Bing, etc).

Você irá utilizar esta coordenada para determinar a probabilidade de um ataque do Coringa nos [locais](https://gist.githubusercontent.com/pitteri/b0c06e2c9b89541559fb2d90c6ae7ccd/raw/8553d6bbbadde292548d66afd7923026ddd3e402/targets.json) próximos, dentro dos [limites de Gotham](https://gist.githubusercontent.com/pitteri/d56780d610cb8e0a43bfa94fc54b71cd/raw/dcdd965c84cd05d856ae32646be69868d4a80afa/gotham_bbox.json).

A probabilidade se dá pela distância linear entre o Coringa e o alvo, ou seja, quanto mais próximo, maior a probabilidade de um ataque. Para calcular a distância linear utilize a [Fórmula de Haversine](https://rosettacode.org/wiki/Haversine_formula). Considere o raio da Terra como 6371km, e para facilitar desconsidere a elevação nos cálculos!

O raio de atuação do Coringa é de 2km, e a probabilidade máxima de ataque é de 95% nos casos em que o resultado seja maior que isso. (Afinal, quem entenderia o que se passa na cabeça do Coringa?!)

> Lembre-se: O Batman é muito exigente, inclusive com a qualidade dos códigos. Surpreenda-o!

### Input

O endereço, localidade ou coordenada do vilão.

#### Endpoints:
1) GET /address?q=\<endereço\>
2) GET /location?q=\<localidade\>
3) GET /coordinate?q=\<latitude\>,\<longitude\>

A coordenada deve estar dentro dos limites de Gotham, caso contrário, é preciso notificar de alguma forma.

### Output

Cada resposta deve conter a localização do Coringa e as informações dos locais que sofrem risco de ataque.

[Exemplo](https://gist.githubusercontent.com/pitteri/578a6801d6f504eda6f6ce84cad59f89/raw) com dados fictícios.

### Solution
A solução backend foi desenvolvida usando **Java**(8) e com base no framework **Spring**. Usaram-se os serviços de [geocoficiação](https://developers.google.com/maps/documentation/geocoding/intro?hl=pt-br) e de [busca de lugares](https://developers.google.com/places/web-service/?hl=pt-br) do Google para obter as coordenadas e lugares próximos. 

- A solução inclui testes unitários e alguns de integração dos serviços. Para rodar os testes usar:
`mvn test`
-  Para  rodar a aplicação:
`mvn spring-boot:run`
- Para testar a API pode se usar diretamente um browser, **curl**, um cliente REST, entre outros. A seguir alguns exemplos: 
<pre> curl -i  http://localhost:8080/api/villainattack/coordinate?q=40.746489872673846,-73.94090362548828</pre>
Resposta:
```json
{
    "villain": {
        "name": "Joker",
        "location": {
            "lat": 40.74648987267385,
            "lng": -73.94090362548827
        }
    },
    "targets": [
        {
            "place": "Chrysler Building",
            "location": {
                "lat": 40.75175779999999,
                "lng": -73.9755189
            },
            "probability": 0.03847590943509668
        },
        {
            "place": "Midtown",
            "location": {
                "lat": 40.7549309,
                "lng": -73.9840195
            },
            "probability": 0
        }
    ]
}
```
<pre>curl -i http://localhost:8080/api/villainattack/address?q=6275%20Bryan%20 Park,%20Browns%20Summit </pre>
<pre>curl -i http://localhost:8080/api/villainattack/location?q=Bryan%20Park</pre>

- Caso as coordenadas do Joker não forem achadas, a API responde com:
```json
{
    "status": "Not Found",
    "message": "Validation Error",
    "errors": [
        "Coordinates not found"
    ]
}
```

- Se as coordenadas na requisição estiverem mal formadas, por exemplo:
<pre>http://localhost:8080/api/villainattack/coordinate?q=40.Jokerwashere!6,12hahahaha</pre>
Nesse caso a API responde com:
```json
{
    "status": "Bad Request",
    "message": "Validation Error",
    "errors": [
        "Malformed coordinates parameter. Example of pattern expected: 40.754671,-73.988568"
    ]
}
```

### Melhoras Futuras

 - Testes do Controller e maior cobertura de testes
 - Uso de Cucumber e Swagger.
 - Desenvolver um cliente Web e/ou mobile.
 - Melhorar gerenciamento de exceções e status.
 - Melhoras com foco em paradigma funcional.
 - Criar perfis e configurações para dev, qa, hm e prod.
 - Outros :).
