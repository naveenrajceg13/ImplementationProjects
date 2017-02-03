import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HexagonalProblem {

	  class Vertex
	  {
		  int name;
		  boolean flag;
		  String move;
		  List<Edge> adjList;
		  String endNode;
		  Vertex(int name,boolean flag,String move)
		  {
			  this.name=name;
			  this.flag=flag;
			  this.move=move;
			  adjList=new ArrayList<>();
			  endNode=null;
		  }
		  public void addEdge(Edge e)
		  {
			 adjList.add(e);  
		  }
		  
		  public String toString()
		  {
			 return String.valueOf(name);
		  }
	  }
	  class Edge
	  {
		  Vertex from;
		  Vertex to;
		  boolean flag;
		  Edge(Vertex from,Vertex to,boolean flag)
		  {
			  this.from=from;
			  this.to=to;
		  }
	  }
	  class Graph
	  {
		  List<Vertex> vertexList;
		  Graph()
		  {
			  vertexList=new ArrayList<>();
			  vertexList.add(new Vertex(0, true,null));
		  }
		  public void addEdge(Vertex v,Vertex u)
		  {
			   v.addEdge(new Edge(v,u,false));
		  }
	  }
	  public void setGraph(Graph g,int max)
	  {
		  for(int i=1;i<=(max*max);i++)
		  {
			  g.vertexList.add(new Vertex(i,false,null));
		  }
		  for(int i=1;i<=(max*max);i++)
		  {
			  if(i==1)
			  {
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
			  }
			  else if(i==(max*max))
			  {
				  g.vertexList.get(i).endNode="BOTH";
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
			  }
			  else if(i%max==0 && i/max==1)
			  {
				  g.vertexList.get(i).endNode="RED";
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max-1)));
			  }
			  else if(i%max==1 && i/max==(max-1))
			  {
				  g.vertexList.get(i).endNode="BLUE";
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max+1)));
			  }
			  else if(i/max==0)
			  {
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
			  }
			  else if(i%max==0)
			  {
				  g.vertexList.get(i).endNode="RED";
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max-1)));
			  }
			  else if(i/max==(max-1))
			  {
				  g.vertexList.get(i).endNode="BLUE";
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max+1)));
			  }
			  else if(i%max==1)
			  {
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max+1)));
			  }
			  else
			  {
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i-max+1)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max)));
				  g.addEdge(g.vertexList.get(i),g.vertexList.get((i+max-1)));
			  }
		  }
	  }
	  public void printAndCheck(Graph g)
	  {
		  for(Vertex v: g.vertexList)
		  {
			  if(v.name!=0)
			  {
				  System.out.println("------------"+v.name+" "+v.move+" "+v.endNode+" "+"------------ ");
				  for(Edge e: v.adjList)
				  {
					  System.out.println(e.to+" ");
				  }
			  }
			  System.out.println();
		  }
	  }
	  public boolean dfs(Vertex v,String find,String check)
	  {
		  if(v.endNode!=null && (v.endNode.equals(find) || v.endNode.equals("BOTH")))
			  return true;
		  else
		  {
			  for(Edge e: v.adjList)
			  {
				  if(e.to.move!=null && e.to.move.equals(check))
					  return dfs(e.to,find,check);
			  }
		  }
		  return false;
	  }
	  
	  public void PrintResult(Graph g,int max)
	  {
		  boolean blueWins=false;
		  boolean redWins=false;
		  for(int i=1;i<=max;i++)
		  {
			  if(g.vertexList.get(i).move!=null && g.vertexList.get(i).move.equals("BLUE"))
			  {
				  if(dfs(g.vertexList.get(i),"BLUE","BLUE"))
				  {
					  System.out.println("PERSIANS");
					  return ;
				  }
			  }
		  }
		  for(int i=1;i<=(max*max);i=i+max)
		  {
			  if(g.vertexList.get(i).move!=null && g.vertexList.get(i).move.equals("RED"))
			  {
				  if(dfs(g.vertexList.get(i),"RED","RED"))
				  {
					  System.out.println("ROMANS");
					  return ;
				  }
			  }
		  }
		  
		  System.out.println("NEITHER");
	  }
	  public void assignPositions(Graph g,Scanner s,int num,int max)
	  {
		  boolean flag=true;
		  for(int i=0;i<num;i++)
		  {
			  int row=s.nextInt()-1;
			  int col=s.nextInt();
			  int getIndex=(row*max)+col;
			  if(flag)
			    g.vertexList.get(getIndex).move="RED";
			  else
				g.vertexList.get(getIndex).move="BLUE";
			  flag=!flag;
		  }
	  }
	  public static void main(String args[])
	  {
		  HexagonalProblem hxPrg=new HexagonalProblem();
		  Graph g=hxPrg.new Graph();
		  Scanner s=new Scanner(System.in);
		  int max=s.nextInt();
		  int numInputs=s.nextInt();
		  hxPrg.setGraph(g, max);
		  hxPrg.assignPositions(g, s, numInputs, max);
		  hxPrg.PrintResult(g, max);
	  }
	  
}
