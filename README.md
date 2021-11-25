# cooperativism-api

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias,
por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de
votação.
- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas Sim/Não. Cada
associado é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

## Rodando o serviço localmente via docker

```shell
docker-compose up
```
```shell
docker-compose down
```

## Url da documentação do serviço local

http://localhost:8080/swagger-ui/index.html

## Url para rodar o serviço localmente

http://localhost:8080

## Url da documentação do serviço (AWS)

http://cooperativism-lb-1900218637.us-east-1.elb.amazonaws.com/swagger-ui/index.html

## Url para rodar o serviço (AWS)

http://cooperativism-lb-1900218637.us-east-1.elb.amazonaws.com
