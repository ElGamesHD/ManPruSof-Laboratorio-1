package clubdeportivo;

import java.util.StringJoiner;

public class ClubDeportivo {
	private String nombre;
	private int ngrupos;
	private Grupo[] grupos;
	private static final int TAM = 10;

	public ClubDeportivo(String nombre) throws ClubException {
		this(nombre, TAM);
	}

	/*
	 * Se debería comprobar que el nombre no sea nulo, por lo que añadimos una
	 * sentencia IF que
	 * cuando reciba un nombre nulo lance una excepción.
	 * 
	 * Error no se inicializa ngrupos
	 */
	public ClubDeportivo(String nombre, int n) throws ClubException {
		if (nombre == null) {
			throw new ClubException("ERROR: el nombre del club no puede ser nulo");
		}

		if (n <= 0) {
			throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
		}
		this.ngrupos = 0;
		this.nombre = nombre;
		grupos = new Grupo[n];
	}

	private int buscar(Grupo g) {
		int i = 0;
		while (i < ngrupos && !g.equals(grupos[i])) {
			i++;
		}
		if (i == ngrupos) {
			i = -1;
		}
		return i;
	}

	/*
	 * Se debería comprobar que el tamaño del String que se pase por párametro sea
	 * el necsitado,
	 * es decir, que tenga 5 elementos. Si no es así, se debería lanzar una
	 * excepción.
	 * Añadimos el primer if para comprobar que el tamaño del array sea el correcto.
	 * 
	 * Otra cosa que se debería comprobar es que el parámetro no sea nulo, por lo
	 * que añadimos una sentencia catch que cuando reciba un nullPointerException
	 * lance una excepción.
	 */

	public void anyadirActividad(String[] datos) throws ClubException {
		try {
			if (datos.length < 5) {
				throw new ClubException("ERROR: faltan datos");
			}

			int plazas = Integer.parseInt(datos[2]);
			int matriculados = Integer.parseInt(datos[3]);
			double tarifa = Double.parseDouble(datos[4]);
			Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
			anyadirActividad(g);
		} catch (NumberFormatException e) {
			throw new ClubException("ERROR: formato de número incorrecto");
		} catch (NullPointerException e) {
			throw new ClubException("ERROR: el conjunto de datos es nulo");
		}
	}

	/*
	 * Desbordamiento de array
	 */
	public void anyadirActividad(Grupo g) throws ClubException {
		if (ngrupos == grupos.length) {
			throw new ClubException("ERROR: el club está lleno");
		}
		if (g == null) { // ADDME: anaydido para comprobar los grupos nulos
			throw new ClubException("ERROR: el grupo es nulo");
		}
		int pos = buscar(g);
		if (pos == -1) { // El grupo es nuevo
			grupos[ngrupos] = g;
			ngrupos++;
		} else { // El grupo ya existe --> modificamos las plazas
			grupos[pos].actualizarPlazas(g.getPlazas());
		}
	}

	/*
	 * Se debería comprobar que el nombre de la actividad no sea nulo, por lo que
	 * añadimos una sentencia IF que
	 * cuando reciba un nombre nulo lance una excepción.
	 */

	public int plazasLibres(String actividad) throws ClubException {
		if (actividad == null) {
			throw new ClubException("ERROR: la actividad no puede ser nula");
		}

		int p = 0;
		int i = 0;
		while (i < ngrupos) {
			if (grupos[i].getActividad().equals(actividad)) {
				p += grupos[i].plazasLibres();
			}
			i++;
		}
		return p;
	}

	/*
	 * Se debería comprobar que el nombre de la actividad no sea nulo, por lo que
	 * añadimos una sentencia IF que
	 * cuando reciba un nombre nulo lance una excepción.
	 * 
	 * Se debería comprobar que el número de personas no sea negativo, por lo que
	 * añadimos una sentencia IF que
	 * cuando reciba un número negativo lance una excepción.
	 * 
	 * el cero
	 */

	public void matricular(String actividad, int npersonas) throws ClubException {
		if (actividad == null) {
			throw new ClubException("ERROR: la actividad no puede ser nula");
		}

		if (npersonas <= 0) {
			throw new ClubException("ERROR: el numero de personas para matricular no debe ser negativo");
		}

		int plazas = plazasLibres(actividad);
		if (plazas < npersonas) {
			throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
		}
		int i = 0;
		while (i < ngrupos && npersonas > 0) {
			if (actividad.equals(grupos[i].getActividad())) {
				int plazasGrupo = grupos[i].plazasLibres();
				if (npersonas >= plazasGrupo) {
					grupos[i].matricular(plazasGrupo);
					npersonas -= plazasGrupo;
				} else {
					grupos[i].matricular(npersonas);
					npersonas = 0;
				}
			}
			i++;
		}
	}

	public double ingresos() {
		double cantidad = 0.0;
		int i = 0;
		while (i < ngrupos) {
			cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
			i++;
		}
		return cantidad;
	}

	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
		int i = 0;
		while (i < ngrupos) {
			sj.add(grupos[i].toString());
			i++;
		}
		return nombre + " --> " + sj.toString();
	}
}
