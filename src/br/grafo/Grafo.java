package br.grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private static final char NOME_VERTICE[] = { 's', 't', 'x', 'y', 'z' };

	protected List<Vertice> vertices = new ArrayList<Vertice>();
	protected List<Aresta> arestas = new ArrayList<Aresta>();

	// este grafo eh direcionado??
	boolean directed = true;

	public String toString() {
		String msg = "Printing G = { V, E }::\n";
		msg += "V[" + vertices.size() + "] = " + vertices + "\n";
		msg += "E[" + arestas.size() + "] = " + arestas + "\n";
		return msg;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void transpose() {
		for (Aresta a : arestas) {
			a.transpose();
		}
	}

	public void init(List<Aresta> la, List<Vertice> lv) throws Exception {
		if (la == null || lv == null)
			return;

		arestas.clear();
		vertices.clear();
		arestas.addAll( la );
		vertices.addAll( lv );

	}

	public static Aresta getAresta( List<Aresta> la, boolean direct , Vertice v1, Vertice v2, float w ) {
		for( Aresta a : la ) {
			if ( !direct && (v1 == a.v[0] || v1 == a.v[1]) && (v2 == a.v[0] || v2 == a.v[1])
					&& a.peso==w ) {
				return a;
			}

			if( direct && v1==a.v[0] && v2==a.v[1] && a.peso==w )
				return a;
		}
		return null;
	}
	
	public Aresta getAresta(Vertice v1, Vertice v2, float w ) {
		return getAresta( arestas, directed, v1, v2, w );
	}

	public void addAresta(Vertice v1, Vertice v2, float w ) throws Exception {
		if (getAresta(v1, v2, w) == null) {
			Aresta a = new Aresta();
			a.setInfo(v1, v2, w);
			v1.addAdj(a);
			if (!directed)
				v2.addAdj(a);
			arestas.add(a);
		}
	}

	public void buildAdjacentes() throws Exception {
		List<Aresta> tmp = new ArrayList<Aresta>();

		for (Vertice vertex : vertices) {
			tmp.clear();
			for (Aresta a : arestas) {
				if( getAresta( tmp, directed, a.v[0], a.v[1], a.peso ) != null ) {
					continue;
				}

				if( a.v[0].equals( vertex ) )
					tmp.add( a );
				else
					if( !directed && a.v[1].equals( vertex ) )
						tmp.add( a );
			}
			vertex.adjacentes.clear();
			vertex.adjacentes.addAll(tmp);
		}
	}

	public void criaVertices(int len) throws Exception {
		boolean nomesDefinidos = false;
		if (len == NOME_VERTICE.length) {
			nomesDefinidos = true;
		}
		vertices.clear();
		String label;
		char tmp = 'a';
		for (int i = 0; i < len; i++) {
			if (nomesDefinidos)
				label = "" + NOME_VERTICE[i];
			else
				label = "" + tmp++;
			Vertice v = new Vertice( i, label );
			vertices.add(v);
		}
	}
}
