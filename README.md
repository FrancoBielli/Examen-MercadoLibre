<h2>Examen de Ingreso MercadoLibre</h2>
<br />

<p><strong>Endpoints:</strong>
<br>
<code>/api/mutante</code>
<br />
<code>/api/stats</code>
</p>
<p>
<h4>Instrucciones de Uso:</h4>
<br />
Crear la base de datos "mutantes" ejecutando el siguiente código SQL:
<br />
<code>CREATE SCHEMA IF NOT EXISTS mutantes</code>
<br />
<br />
Y configurar la propiedad <code>spring.datasource.url=</code> en el archivo de propiedades <code>application.properties</code>
<br />

<strong>Método: <code>POST</code></strong> <br />
<strong>Endpoint:</strong> <code>/api/mutante</code> <br />
<strong>Entrada de ejemplo:</strong> <code>{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}</code> <br />
<br>
El método evalúa si el ADN ingresado corresponde a un mutante o a un humano. <br />
<ul>
<li>Si corresponde a un mutante devuelve un Status 200 OK,</li>
<li>Si corresponde a un humano devuelve un Status 403 Forbidden </li>
</ul>
<br />
<br>
<strong>Método: <code>GET</code></strong><br />
<strong>Endpoint:</strong><code>/api/stats</code><br />
<strong>Salida:</strong> <code>{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}</code><br />
<br>
El método calcula la relación entre los mutantes y los humanos que han sido ingresados en el método anterior.
</p>
