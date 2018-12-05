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
Crear la base de datos "mutantes" y la tabla "persona" ejecutando el siguiente código SQL:
<br />
<code>CREATE SCHEMA prueba;

CREATE TABLE prueba.persona (
  adn varchar(255) NOT NULL,
  mutante bit(1) NOT NULL,
  PRIMARY KEY (`adn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
</code>
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
<li>Si corresponde a un mutante* devuelve un Status 200 OK</li>
<li>Si corresponde a un humano devuelve un Status 403 Forbidden </li>
<li>Si se ingresa una cadena que no puede ser procesada devuelve un Status 400 Bad Request:<br />
  Las cadenas que no pueden ser procesadas son:
  <ul>
    <li>Cadenas con caracteres diferentes a A, T, G o C</li>
    <li>Cadenas que tengan menos de 4 filas, ya que no pueden ser evaluadas de forma vertical ni oblícua</li>
    <li>Cadenas que no sean del formato NxN</li>
  </ul>
  </li>
</ul>
<em>*El sistema verifica que hayan 4 letras iguales consecutivas en cualquier dirección (horizontal, vertical y oblícua en los dos sentidos [de derecha a izquierda y de izquierda a derecha]).</em>
<br />
<br>
<strong>Método: <code>GET</code></strong><br />
<strong>Endpoint:</strong><code>/api/stats</code><br />
<strong>Salida:</strong> <code>{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}</code><br />
<br>
El método calcula la relación entre los mutantes y los humanos que han sido ingresados en el método anterior.
</p>
