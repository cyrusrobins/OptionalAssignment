public class GraphAdjMatrix implements Graph {
    protected int[][] vertices;
    protected int v;
    public GraphAdjMatrix(int v){
        vertices = new int[v][v]; //make a matrix of the possible verticies
        this.v = v; //store the dimensions of the matrix
    }

    public void addEdge(int v1, int v2, int weight){ //set a value in the matrix to be the weight, and set it's reflected value as well
        vertices[v1][v2] = weight;
        vertices[v2][v1] = weight;
    }
    public int getEdge(int v1, int v2){ //return the weight value of an edge between specific verticies
        return vertices[v1][v2];
    }
    public int createSpanningTree(){
        Edge[] sortedWeights = new Edge[v*(v-1)/2]; //create an array for the maximum number of possible edges
        int arrSize = 0; //actual number of edges stored in the sorted weights
        for(int i=1;i<v;i++){ //start at 1 because there can be no edge at 0,0
            for(int j=0;j<i;j++){
                if(vertices[i][j]>0) { //if the weight is greater than 0, then there is an edge present
                    sortedWeights[arrSize] = new Edge(i, j, vertices[i][j]); //create a new edge to reflect this fact
                    arrSize++; //increment the counter
                }
            }
        }

        MergeSort.sort(sortedWeights,arrSize-1); //merge sort all of the edge values
        Edge[] usedEdges = new Edge[v-1]; //keep an array for all of the used edges in the spanning graph
        usedEdges[0] = sortedWeights[0]; //you will always use the smallest edge value, so start with that
        int usedEdgeIndex = 1; //counter to keep track of the index of the usedEdges array
        usedEdges[0].legalPlacement(usedEdges[0]); //Do this to initialize the value in the usedEdges instance data in the edge class
        while(usedEdgeIndex<v-1){ //while there aren't enough edges for a full graph
            for(int i=0;i<v-1;i++){ //iterate once for each item we will add to the used edges
                for(int j=0;j<arrSize;j++) {
                    //if the edge hasn't already been used in the used edges, and the placement is legal, and the number of edges hasn't maxed out
                    if (!Edge.edgeAlreadyUsed(sortedWeights[j]) && usedEdges[i].legalPlacement(sortedWeights[j]) && usedEdgeIndex<v-1) {
                        //add the next lowest weight term to the used edges
                        usedEdges[usedEdgeIndex] = sortedWeights[j];
                        usedEdgeIndex++;
                    }
                }
            }
        }
        int totalWeight = 0;
        emptyVerticies(); //empty the vertices of all of the edges
        for(Edge e: usedEdges) {
            vertices[e.v1][e.v2] = e.weight; //only add back the used edges
            totalWeight += e.weight; //count the total weight
        }
        return totalWeight; //return the weight
    }

    public void emptyVerticies(){ //method to zero out all of the values in verticies
        for(int i=0;i<v;i++){
            for (int j=0;j<v;j++){
                vertices[i][j] = 0;
            }
        }
    }

    public void addEdge(int v1, int v2){}
    public void topologicalSort(){}
}



