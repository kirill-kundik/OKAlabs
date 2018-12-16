package PW12;

public class SAP {

    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
	graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws InterruptedException {
	int length = -1;
	BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(graph, v);
	BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(graph, w);
	// StdOut.println(graph.V());
	for (int i = 0; i < graph.V(); i++) {
	    if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
		int temp = bfdp1.distTo(i) + bfdp2.distTo(i);
		if (temp < length || length == -1)
		    length = temp;
	    }
	}
	return length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) throws InterruptedException {
	int length = -1;
	int ancestor = -1;
	BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(graph, v);
	BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(graph, w);

	for (int i = 0; i < graph.V(); i++) {
	    if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
		int temp = bfdp1.distTo(i) + bfdp2.distTo(i);
		if ((temp < length) || (length == -1)) {
		    length = temp;
		    ancestor = i;
		}
	    }
	}
	return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
	int length = -1;
	BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(graph, v);
	BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(graph, w);
	// StdOut.println(graph.V());
	for (int i = 0; i < graph.V(); i++) {
	    if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
		int temp = bfdp1.distTo(i) + bfdp2.distTo(i);
		if (temp < length || length == -1)
		    length = temp;
	    }
	}
	return length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no
    // such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
	int length = -1;
	int ancestor = -1;
	BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(graph, v);
	BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(graph, w);

	for (int i = 0; i < graph.V(); i++) {
	    if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
		int temp = bfdp1.distTo(i) + bfdp2.distTo(i);
		if (temp < length || length == -1) {
		    length = temp;
		    ancestor = i;
		}
	    }
	}
	return ancestor;
    }
}