# Teste Techno Software

### Segue os Endpoints referentes as requisições solicitadas:

### /cidades/1
- Realiza a Importação do CSV, que se encontra em "src/main/resources/arquivos/cidades.csv" para o Banco de Dados.

### /cidades/2
- Retorna somente as cidades que são capitais ordenadas por nome.

### /cidades/3
- Retorna o nome dos estados que contém o maior número de cidades e o menor número de cidades.

### /cidades/4
- Retorna a quantidade de cidades por estado.

### /cidades/5?ibgeid=99999
- Retorna os dados de uma cidade informando o ID do IBGE como parâmetro.

### /cidades/6?uf=sp
- Retorna o nome das cidades informando um estado como parâmetro.

### /cidades/7
- Adiciona uma nova cidade passando como parâmetro no corpo da requisição o valor de todos os campos pertencentes a Cidade.

### /cidades/8?nome=NomedaCidade
- Deleta uma cidade passando como parâmetro o nome da cidade que você deseja deletar.

### /cidades/9?coluna=capital&palavra=teste
- Realiza um filtro na coluna selecionada. Passando como parâmetro o nome da coluna(ibge_id,name,uf,microregion....) e o que você deseja filtrar em palavra.

### /cidades/10/name
- Retorna a quantidade de registros da coluna name, não contando itens iguais.

### /cidades/11/total
- Retorna a quantidade total de registros.

## Tecnologias Utilizadas:
- Java
- Spring
- MySQL
- Postman - Para testar as requisições HTTP