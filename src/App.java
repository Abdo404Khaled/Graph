import java.util.Arrays;

public class App {

	public static void main(String args[])
	{

		Graph<Integer> g = new Graph<Integer>();

		g.addEdge(0, 1, 4, true);
		g.addEdge(0, 2, 6, true);
		g.addEdge(1, 2, 6, true);
		g.addEdge(1, 3, 3, true);
		g.addEdge(1, 4, 4, true);
		g.addEdge(2, 3, 1, true);
		g.addEdge(3, 4, 2, true);
		g.addEdge(3, 5, 3, true);
		g.addEdge(4, 5, 7, true);

		System.out.println("Graph:\n" + g.toString());

		System.out.println(g.getVertexCount());

		System.out.println(g.getEdgesCount(true));

		System.out.println(g.hasEdge(new weightedEdge<Integer>(3, 4, 2)));

		System.out.println(g.hasVertex(5));

		Object[][] temp = g.getAdjMatrix();

		System.out.println("Adjacency Matrix: ");
		for(Object[] x: temp){
			System.out.println(Arrays.toString(x));
		}

		System.out.println("CITIES QUESTIONS **********************************************************************");
		int[][] cities = {{0,0},{2,2},{3,10},{5,2},{7,0}};

		Graph<Integer> tempGraph = makeConnectedGraph(cities);

		int[][] citiesMST = tempGraph.primAlgorithmMST();

		int res = 0;

		for(int[] i: citiesMST){
			res += i[2];
			if(i[0] != i[1])
				System.out.println(i[0] + "->" + i[1] + " w: " + i[2]);
		}
		System.out.println("MIN COST = " + res);
	}

	public static Graph<Integer> makeConnectedGraph(int cities[][]){
		Graph<Integer> graph = new Graph<>();

		for(int i = 0;i < cities.length;i++)
			graph.addVertex(i);

		for(int i =0;i < cities.length;i++)
			for(int j = 0; j < cities.length;j++)
				if(i != j)
					graph.addEdge(i, j, ( (int) (Math.abs(cities[i][0] - cities[j][0]) + Math.abs(cities[i][1] - cities[j][1]))), false);

		return graph;
	}
}
