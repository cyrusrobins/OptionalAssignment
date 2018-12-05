import java.util.ArrayList;

public class Edge {
    protected int v1, v2, weight; //instance data
    public ArrayList<Edge> edges = new ArrayList<Edge>(); //list of all edges
    public static ArrayList<Edge> usedEdges = new ArrayList<Edge>(); //list of used edges

    public Edge(int v1, int v2, int weight){ //constructor
        this.v1 = v2;
        this.v2 = v1;
        this.weight = weight;
        edges.add(this); //add the new object to the array
    }

    public static boolean edgeAlreadyUsed(Edge e){ //checks if an edge is already in the used edges
        for(Edge g: usedEdges) {
            if (g.v1 == e.v1 && g.v2 == e.v2 && g.weight == e.weight)
                return true;
        }
        return false;
    }

    public int compareTo(Edge e){ //compare weights of edges
        if(this.weight>e.weight)
            return 1;
        else if(this.weight<e.weight)
            return -1;
        else
            return 0;
    }

    public boolean searchForLink(int v1, int v2){ //checks for the linking edge between 2 edges to avoid having an unnecessary edge
        for(Edge e: usedEdges){
            if(e.v1 == v1)
                if(e.v2 == v2)
                    return true;
                else
                    return searchForLink(e.v2,v2); //recursively check for the linking member needed if one of the vertices matches, which would create a larger cycle
            else if(e.v1 == v2)
                if(e.v2 == v1)
                    return true;
                else
                    return searchForLink(e.v2,v1);
        }
        return false;
    }

    public boolean legalPlacement(Edge e){ //check if an edge would make an illegal loop in the graph
        if(usedEdges.size()==0){ //if there are no used edges yet, automatically add it
            usedEdges.add(e);
            return true;
        }
        //check different cases to see if two edges that share a vertex have a third partner already in the used edges that would make a loop
        else if(this.v2 == e.v1){
            if(!searchForLink(e.v2,this.v1)){
                usedEdges.add(e);
                return true;
            }
            return false;
        }
        else if(this.v2 == e.v2){
            if(!searchForLink(e.v1,this.v1)){
                usedEdges.add(e);
                return true;
            }
            return false;
        }
        else if(this.v1 == e.v1){
            if(!searchForLink(e.v2,this.v2)){
                usedEdges.add(e);
                return true;
            }
            return false;
        }
        else if(this.v1 == e.v2){
            if(!searchForLink(e.v1,this.v2)){
                usedEdges.add(e);
                return true;
            }
            return false;
        }
        else{ //if they share no vertices then they can't make a loop, so add it
            usedEdges.add(e);
            return true;
        }
    }
}
