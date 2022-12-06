
public interface graphInf <T> {
    public void addVertex(T vertex);
    public void addEdge(T source, T destination, int weight, Boolean directed);
    public int getVertexCount();
    public int getEdgesCount(Boolean directed);
    public Boolean hasVertex(T vertex);
    public Boolean hasEdge(weightedEdge<T> edge);
    public Object[][] getAdjMatrix();
    public int[][] primAlgorithmMST();
}