CRUD de uma API feita com Java e Spring com tema de pizzaria para consumo com os principais verbos HTTP.

JSON:

TABLE pizza:

{
  "nome": "Pizzaria Now",
  "descricao": "Pizza de calabresa com cebola e azeitona"
}


TABLE pedido:

{
  "dataPedido": "2025-10-16T18:30:00",
  "statusPedido": "EM_PREPARO",
  "pizza": {
    "id": 1
  },
  "cliente": {
    "id": 2
  }
}


TABLE cliente:

{
  "nome": "Elevir.Junior7",
  "telefone": "58315000",
  "endereco": {
    "rua": "Rua das Flores",
    "numero": "123",
    "bairro": "Gramado",
    "cidade": "Gramado",
    "estado": "SC",
    "cep": "444422"
  }
}




