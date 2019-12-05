# GoldenRaspberryAwards
O projeto GoldenRaspberryAwards foi desenvolvido para participar do processo seletivo da empresa TexoIT. Ao subir o servidor de aplicação um arquivo csv com os premios é carregado para um banco de dados em memoria. Apos ficam disponiveis endpoints para listar os piores filmes concorrentes ao premio. Também esta disponivel um endpoint para lista o produtos com menor e maximo intervalo entre os premios.

## Como Executar

- Importar o projeto atraves do eclipse ou IDE de preferencia

- Selecionar a opção Porject -> Build All e  com o botão direito no projeto Maven -> Update Project para que a IDE possa baixar as dependências. 

    URL Base: http://localhost:8080/
    os recuros podem e seus payloads podem ser visualizados n proxima seção

- A porta pode ser alterada através do arquivo de configuração "application.yml"

- Iniciar atraves do run da IDE o arquivo GoldenRaspberryAwardsApplication.java

- O deploy do projeto pode ocorrer de duas maneira: a convencional que é gerar o ".war" e realizar o deploy em um servidor de aplicação externo ou gerar um arquivo ".jar" e utilizar o suporte do Spring encapsular Tomcat.  

- Os testes unitario podem ser rodados através da ferramenta maven, rodando o comando mvn test ou maven test

## Recursos

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Winners</h3>
<p>Permite obter os produtores com maximo e minimo intervalo entre premios.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/v1/winners</code></p>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/winners</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>{
  "min": [
    {
      "producer": "Joel Silver",
      "interval": 1,
      "previousWin": 1990,
      "followingWin": 1991
    }
  ],
  "max": [
    {
    "producer": "Matthew Vaughn",
    "interval": 13,
    "previousWin": 2002,
    "followingWin": 2015
      }
  ]
}</code></pre>
<h3>

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Awards</h3>
<p>Permite listar todos os filmes que concorrem ao premio.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/v1/awards</code></p>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/v1/awards</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>[
    {
        "id": 1,
        "year": 1980,
        "title": "Can't Stop the Music",
        "studios": [
            "Associated Film Distribution"
        ],
        "producers": [
            "Allan Carr"
        ],
        "winner": true
    }
]</code></pre>
<h3>
  
<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Awards</h3>
<p>Permite buscar um filme que concorrem ao premio.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/winners/{id}</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li><strong>Id</strong> - Obrigatório</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/v1/awards/1</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>
    {
        "id": 1,
        "year": 1980,
        "title": "Can't Stop the Music",
        "studios": [
            "Associated Film Distribution"
        ],
        "producers": [
            "Allan Carr"
        ],
        "winner": true
    }
</code></pre>
<h3>



