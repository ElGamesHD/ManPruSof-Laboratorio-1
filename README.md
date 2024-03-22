# ManPruSof-Laboratorio-1

Primera práctica de laboratorio de Mantenimiento y Pruebas del Software, sobre las pruebas unitarias con JUnit.

## Integrantes:

- Alejandro Román Sánchez
- Eulogio Quemada Torres

# Errores encontrados

Vamos a enumerar los errores que hemos encontrado, y como los hemos corregido.

## Errores generales

1. El método matricular de la clase ClubDeportivo, presentaba un error grave. En el caso de que se matriculase a un número de personas menor que el número de plazas libres para un grupo específico (es decir, en dicho grupo se pueden matricular todas las personas que se pueden matricular, no hace falta buscar más grupos), si había otro grupo con la misma actividad, se iban a matricular las personas en ambos grupos, o incluso en más si se volvía a dar el caso con el segundo grupo.
    - Añadimos en la parte del else del if que comprueba lo comentado, debajo de la línea grupos[i].matricular(npersonas), la línea npersonas = 0, lo que hace que el bucle deje de iterar y por tanto no matricule a más personas de las que se quería.
    - Si la explicación parece un poco liosa, el test "matricular_TwoGroupsOnlyUseOne_NotMoreThanIndicated" de la clase ClubDeportivoMatricularTest muestra este caso. Si en la implementación de matricular se quita la linea npersonas = 0, fallará, pues matriculará a más personas de las que se querían matricular.

2. El método anyadirActividad (con parámetro array de Strings) de la clase ClubDeportivo, no comprueba que el array "datos" puede tener menos de los 5 elementos esperados, o que el array en si puede ser nulo.
    - Añadimos una sentencia if, que en caso de haber menos de 5 elementos, lance un error. Además, metemos este if dentro del try, y hacemos un catch para que si el array "datos" es nulo, la llamada al atributo length de la lista lance NullPointerException y nosotros lo capturemos para lanzar un ClubException.

3. El método anyadirActividad (con parámetro Grupo) de la clase ClubDeportivo, no comprueba si el club esta lleno, provocando en caso de que lo esté un desbordamiento de array.
    - Añadimos una sentencia if, que en caso de que el parámetro "ngrupos" sea igual al número de grupos que hay en la lista, lance un error.

4. El método matricular de la clase ClubDeportivo, no comprueba que el número de personas a matricular sea estrictamente positivo.
    - Añadimos una sentencia if, que en caso de que el parámetro "npersonas" sea menor o igual a cero, lance un error.

5. El método anyadirActividad de la clase ClubDeportivoAltoRendimiento, no comprueba que el array "datos" puede ser nulo.
    - Metemos la sentencia if que comprueba el número de elementos en el array "datos" dentro del try, y hacemos un catch para que si es nulo, la llamada al atributo length de la lista lance NullPointerException y nosotros lo capturemos para lanzar un ClubException.

## Errores de valores nulos

Los siguientes errores son todos referentes a valores nulos, y todos se arreglan igual, añadiendo una sentencia if que en caso de que dicho valor sea nulo, lance un error. Dado que todos se arreglan de la misma manera, solo los enumeraremos, para no repetir varias veces lo mismo.

6. El constructor de la clase ClubDeportivo, no comprueba que el parámetro "nombre" puede ser nulo.

7. El método plazasLibres de la clase ClubDeportivo, no comprueba que el parámetro "actividad" pueda ser nula.

8. El método matricular de la clase ClubDeportivo, no comprueba que el parámetro "actividad" puede ser nula.

9. El constructor de la clase Grupo, no comprueba que el parámetro "codigo" o el parámetro "actividad" pueden ser nulos.