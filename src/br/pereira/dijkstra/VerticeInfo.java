package br.pereira.dijkstra;

import br.grafo.Vertice;

public class VerticeInfo implements Comparable< VerticeInfo >{
	public float du = 0f;		// shortest path-estimate	Cormen, pg. 608 - cap. relaxation
	public Vertice pi = null; // indice do vertice anterior

	public String toString() {
		String msg = "[du:"+du+",pi:";
		msg += ( pi == null ) ? "null" : pi.label;
		msg += "]";
		return msg;
	}
	
	public boolean equals( VerticeInfo vi ) {
		if( du == vi.du && pi == vi.pi )
			return true;
		return false;
	}
	
	@Override
	public int compareTo(VerticeInfo o) {
		if( du == o.du )
			return 0;
		else if( du < o.du ) 
			return -1;
		else
			return 1;
	}

}
