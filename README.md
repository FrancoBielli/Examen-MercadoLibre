<h2>Examen de Ingreso MercadoLibre</h2>
<br />

<p><strong>Endpoints:</strong>
<br>
<code>/api/mutante</code>
<br />
<code>/api/stats</code>
</p>
<p>
<h4>Instrucciones para instalar:</h4>
<br />
Enviroment<br />
<ul>
  <li>Java 8</li>
  <li>Maven</li>
  <li>Spring-boot 1.5.9</li>
  <li>MySQL</li>
</ul>
Situarse en el directorio deseado y clonar el repositorio:<br>
<code>$ git clone https://github.com/FrancoBielli/Examen-MercadoLibre.git</code>
<br />
E importarlo en su IDE de Java.
<br />
<br />
Crear la base de datos "mutantes" y la tabla "persona" ejecutando el siguiente código SQL:
<br />
<code>CREATE SCHEMA mutantes;

CREATE TABLE mutantes.persona (
  adn varchar(255) NOT NULL,
  mutante bit(1) NOT NULL,
  PRIMARY KEY (`adn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
</code>
<br />
Configurar los siguientes campos de la base de datos en el archivo application.properties:<br />
<ul>
  <li><code>Usuario: spring.datasource.username=</code></li>
  <li><code>Contraseña: spring.datasource.password=</code></li>
  <li>*<code>spring.datasource.url=</code></li>
</ul>
<em>*Solo modificar en el caso de que su aplicación no se ejecute en localhost:8080 y su base de datos MySQL no esté en el puerto 3306</em>
<br />
</p>
<p>
<h4>Instrucciones de Uso:</h4>
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
    <li>Cadenas que tengan menos de 4 filas, ya que no pueden ser evaluadas de forma vertical ni oblicua</li>
    <li>Cadenas que no sean del formato NxN</li>
  </ul>
  </li>
</ul>
<em>*El sistema verifica que haya 4 letras iguales consecutivas en cualquier dirección (horizontal, vertical y oblicua en los dos sentidos [de derecha a izquierda y de izquierda a derecha]).</em>
<br />
<br>
<strong>Método: <code>GET</code></strong><br />
<strong>Endpoint:</strong><code>/api/stats</code><br />
<strong>Salida:</strong> <code>{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}</code><br />
<br>
El método calcula la relación entre los mutantes y los humanos que han sido ingresados en el método anterior.
</p>
