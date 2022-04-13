# Domínios de Software - Grupo 5

Sistema para Correção Automática de Provas com Questões Objetivas de um processo seletivo

## Requisitos
1. Importar o gabarito de um arquivo
2. Ler as respostas de um arquivo
3. Definir grupos de questões por tema e seus pesos
4. Peso padrão é 1
5. Definir número de vagas
6. Definir critério de desempate
7. cadastrar participante
8. definir ranking

## Requisitos para a implantação do sistema
* jdk 8 ou acima
* postegresql

Configuração do Banco de dados:
<br>
Criar um banco com o nome "vestib" ou de outra preferencia porém terá que mudar no sistema
<br>
inserir username e password do usuario postegres no arquivo application.properties

### os campos entre chaves são onde deve-se ser mudado
spring.datasource.url=jdbc:postgresql://localhost:5432/{nome_banco_de_dados}
<br>
spring.datasource.username={username}
<br>
spring.datasource.password={password}
<br>

Ao entrar no projeto esperar a ide carregar as dependencias(conselhavel intellij)
<br>
Por fim dê run no projeto
<br>
pesquise a url: http://localhost:8082/vestib-back/
<br>
<br>
para mudar a url (porta e nome do sistema):
<br>
server.servlet.context-path=/vestib-back
<br>
server.port=8082


