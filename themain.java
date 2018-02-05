import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class themain 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			Scanner inFile = new Scanner(new FileReader(args[0]));
			BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));		
			
			if(args.length < 1) 
		    {
		        System.out.println("Error to open file");
		        System.exit(1);
		    }
			
			int numNodes = inFile.nextInt();
					
			graphColoring graphColor = new graphColoring(numNodes);
			int i, j;
		
			while(inFile.hasNext())
			{
			 	i = inFile.nextInt();
		    	j = inFile.nextInt();
		    	graphColor.loadMatrix(i, j);
		    	Node newNode = new Node(i, j);
		    	
			}
			graphColor.constructNodeList();
			graphColor.printNodeList(outFile);
			graphColor.newColor = 0;
			graphColor.printMatrix(outFile);
			
			while(graphColor.findUncolorNode())
			{
				graphColor.newColor++; 
			
				Node current = graphColor.listHead.next;
				
				while(current != null)
				{
					if(current.color == 0 && graphColor.checkAdjacent(current.ID, graphColor.newColor) == 0)
					{	
						graphColor.adjacencyMatrix[current.ID][current.ID] = graphColor.newColor;
						current.color = graphColor.newColor;
						current = current.next;
					}
					else
					{
						current = current.next;
					}
				}
			}
			
			outFile.write("Number of colors used is: " + graphColor.newColor + "\n");

			
			graphColor.printNodeList(outFile);
			graphColor.printMatrix(outFile);
			
			inFile.close();
			outFile.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
