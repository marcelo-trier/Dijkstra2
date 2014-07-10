package br.pereira.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class Dijkstra extends Grafo {
	public Dijkstra( Grafo g ) throws Exception {
		super();
		init( g.getArestas(), g.getVertices() );
	}
	
	public void initializeSingleSource( int index ) {
		for( Vertice v : vertices ) {
			v.info.du = 999;
			v.info.pi = null;
		}
		Vertice u = vertices.get( index );
		u.info.du = 0;
	}
	
	public void initializeSingleSource( String label ) {
		Vertice u = null;
		for( Vertice v : vertices ) {
			v.info.du = 999;
			v.info.pi = null;
			if( v.label.equals( label ) ) {
				u = v;
			}
		}
		u.info.du = 0;
	}
	
	public void relax( Vertice u, Vertice v, float w ) {
		if( v.info.du > u.info.du + w ) {
			v.info.du = u.info.du + w;
			v.info.pi = u;
		}
	}
	
	public void execute( int id ) {
		initializeSingleSource( id );
		List<Vertice> S = new ArrayList<Vertice>();
		List<Vertice> Q = new ArrayList<Vertice>();
		Q.addAll( vertices );
		while( Q.size() > 0 ) {
			Collections.sort( Q );
			Vertice u = Q.remove( 0 );
			S.add( u );
			for( Aresta a : u.getAdjacentes() ) {
				Vertice v = a.v[1];
				float w = a.peso;
				relax( u, v, w );
			}
		}
		Collections.sort( vertices );
	}
	

	String fltFormat = "%2.1f, ";
	String strFormat = "%3s, ";
	public String toString() {
		String msg;
		String vu = "u    = { ";
		String du = "du[] = { ";
		String pi = "pi[] = { ";
		msg = "#DIJKSTRA :\n";
		for( Vertice v : vertices ) {
			//du += bi.du + ", ";
			//fu += bi.fu + ", ";
			vu += String.format( strFormat, v.label );
			du += String.format( fltFormat, v.info.du );
			pi += ( v == null || v.info==null || v.info.pi==null ) ? "---, " : String.format( strFormat, v.info.pi.label );
			//int vertex = ( bi.pi == null ) ? -1 : bi.pi.vertex;
			//pi += ( bi.pi == null ) ? "--, " : String.format( strFormat, bi.pi.toString() );
			//pi += ", ";
			//pi += bi.pi + ", ";
		}
		vu = vu.substring( 0, vu.length() - 2 ) + " };";
		du = du.substring( 0, du.length() - 2 ) + " };";
		pi = pi.substring( 0, pi.length() - 2 ) + " };";
		
		msg += vu + "\n" + du + "\n" + pi;

		msg = super.toString() + msg;
		return msg;
	}

	
	
	public String toString__OLD() {
		String msg = "Dijkstra.toString()\n";
		msg += "V["+ vertices.size() + "] = " + vertices + "\n";
		msg += "E["+ arestas.size() + "] = " + arestas + "\n";
		return msg;
	}
}
