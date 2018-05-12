import java.util.ArrayList;
import java.util.LinkedList;

public class Component implements Comparable<Component> {
	LinkedList<Vertex> vertexList;
	
	public Component(String bfs_result) {
		this.vertexList = new LinkedList<>();
		processBFSResult(bfs_result);
	}
	
	private void processBFSResult(String bfs_result) {
		String[] list = bfs_result.split("\n");
		for(int i = 0; i < list.length;i++) {
			Vertex vertex = new Vertex(list[i].split("-")[0] );
			this.vertexList.add(vertex);
		}
	}

	public LinkedList<Vertex> getVertexList() {
		return this.vertexList;
	}
	
	@Override
	public int compareTo(Component o) {
	     if (this.vertexList.size() > o.getVertexList().size()) {
	          return -1;
	     }
	     if (this.vertexList.size() > o.getVertexList().size()) {
	          return 1;
	     }
		return 0;
	}
	
	@Override
	public String toString() {
		String response = "";
		for(int i = 0; i < this.vertexList.size();i++) {
			response += this.vertexList.get(i).getValue().toString() + " ";
		}
		response += "\n";
		return response;
	}
}
