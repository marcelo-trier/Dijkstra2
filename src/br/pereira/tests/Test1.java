package br.pereira.tests;

import java.io.File;

import br.grafo.Grafo;
import br.pereira.dijkstra.Dijkstra;
import br.pereira.util.GerenteArquivos;
import br.pereira.util.GraphUtils;

public class Test1 {

	public static void main(String[] args) throws Exception {
		System.out.println( "RUNNING: " + Test1.class.getName() + "\n\n" );

		File f = GerenteArquivos.getInstance().getOpenFile();
		Grafo g = GraphUtils.loadFromFile( f );
		System.out.println( g );

		Dijkstra dj = new Dijkstra( g );
		dj.execute( 1 );
		System.out.println( dj );
	}
}
