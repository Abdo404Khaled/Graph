import java.util.*;

public class Graph<T> implements graphInf <T> {

	private Map<T, List<weightedEdge<T>> > map = new HashMap<>();

	@Override
	public void addVertex(T vertex)
	{
		map.put(vertex, new LinkedList<weightedEdge<T>>());
	}
	@Override
	public void addEdge(T source, T destination, int weight, Boolean directed)
	{

		if (!map.containsKey(source))
			addVertex(source);

		if (!map.containsKey(destination))
			addVertex(destination);

		map.get(source).add(new weightedEdge<T>(source, destination, weight));

		if(directed)
		map.get(destination).add(new weightedEdge<T>(destination, source, weight));

	}

	@Override
	public int getVertexCount()
	{
        return map.keySet().size();
	}

	@Override
	public int getEdgesCount(Boolean directed)
	{
		int count = 0;

		for (T v : map.keySet()) {
			count += map.get(v).size();
		}
		if(directed)
			return count/2;
		return count;
	}

	@Override
	public Boolean hasVertex(T vertex)
	{
		if (map.containsKey(vertex))
			return true;
		else
            return false;
	}
	
	@Override
	public Boolean hasEdge(weightedEdge<T> edge)
	{
		if (map.get(edge.getSource()).contains(edge))
			return true;
		else
			return false;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (T vertex : map.keySet()) {
			builder.append(vertex.toString() + ": ");
			for (weightedEdge<T> w : map.get(vertex)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}

	@Override
	public Object[][] getAdjMatrix() {
		int V = getVertexCount();
		Object[][] adjMatrix = new Object[V][V];
		for(Object[] x: adjMatrix)
			Arrays.fill(x, 0);
		for(T i: map.keySet()){
			for(weightedEdge<T> x: map.get(i)){
				adjMatrix[(int) i][(int) x.getDestination()] = x.weight();
			}
		}
		return adjMatrix;
	}

	@Override
	public int[][] primAlgorithmMST() {
		int V = getVertexCount();

		int[][] MST = new int[V][3];

		int key = 0;

		Object[][] graph = getAdjMatrix();

		Object[] parent = new Object[V];

		int[] weights = new int[V];
		Arrays.fill(weights, Integer.MAX_VALUE);

		Boolean[] setMST = new Boolean[V];
		Arrays.fill(setMST, false);

		parent[0] = (int) -1;
		weights[0] = 0;
		
		for(int i = 0;i < V - 1; ++i){
			int U = selectMinIndex(weights, setMST);
			setMST[U] = true;

			for(int j = 0;j < V;++j){
				if((int) graph[U][j] != 0 && !(setMST[j]) && (int) graph[U][j] < weights[j]) {
					weights[j] = (int) graph[U][j];
					parent[j] = U;
				}
			}
		}

		for(int i = 1;i < V;++i){
			MST[key][0] = (int) parent[i];
			MST[key][1] = i;
			MST[key][2] = (int) graph[ (int) parent[i]][i];
			key++;
		}
				
		return MST;
	}

	private int selectMinIndex(int[] weights, Boolean[] setMST){
		int min = Integer.MAX_VALUE;
		int V = getVertexCount();
		int vertex = -1;
		for(int i = 0;i < V;++i){
			if(!setMST[i] && weights[i] < min){
		 		vertex = i;
				min = weights[i];
			}
		}
		return vertex;
	}
}