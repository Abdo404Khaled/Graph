public class weightedEdge<T>
{
    private T source;
    private T destination;
    private int weight;

    public weightedEdge(T source, T destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getSource(){
        return source;
    }

    public T getDestination(){
        return destination;
    }

    public int weight(){
        return weight;
    }

    @Override
    public String toString(){
        return "(d: " + destination + ", w: " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
  
        if (o == this) {
            return true;
        }

        if (!(o instanceof weightedEdge))
            return false;

        
        weightedEdge<T> c = (weightedEdge<T>) o;
         
        return source == c.source && destination == c.destination && weight == c.weight;
    }
}