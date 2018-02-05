import java.io.BufferedWriter;

public class graphColoring
{
	int[][] adjacencyMatrix;
	Node listHead;
	int uncolorNode;
	int newColor;
	int numNode;
	
	public graphColoring(int n)
	{
		adjacencyMatrix = new int[n+1][n+1];
		uncolorNode = 0;
		newColor = 0;
		numNode = n;
		listHead = new Node();
	}
	
	public void loadMatrix(int k, int l)
	{
		adjacencyMatrix[k][l] = 1;
		adjacencyMatrix[l][k] = 1;
	}
		
	public void printNodeList(BufferedWriter outFile)
	{
		try
		{
			Node current = listHead;
			
			while(current.next != null)
			{
				outFile.write(current.ID + " : " + current.numEdges + " : " + current.color + "\n");
				current = current.next;
			}
			outFile.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void printMatrix(BufferedWriter outFile)
	{
		try
		{
			for(int i = 1; i < numNode + 1; i++)
			{
				for(int j = 1; j < numNode + 1; j++)
				{
					outFile.write(adjacencyMatrix[i][j] + " ");
				}
				outFile.write("\n");
			}
			outFile.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean findUncolorNode()
	{
		Node current = listHead.next;
		
		while(current.next != null && current.next.color == 1)
		{
			current = current.next;
		}
		
		if(current.next == null)
		{
			return false;
		}
		else
		{
			return true;
		}		
	}
    
    
    public void insertOneNode(Node newNode)
    {
        Node current = listHead;
        
        if(current.next == null)
        {
            current.next = newNode;
        }
        else
        {
            while((current.next != null) && (current.next.numEdges < newNode.numEdges))
            {
                current = current.next;
            }
            Node temp = current.next;
            current.next = newNode;
            newNode.next = temp;
        }
    }
    
    public void constructNodeList()
    {
        for(int i = 1; i < numNode + 1; i++)
        {
            int countEdges = 0;
            for(int j = 1; j < numNode + 1; j++)
            {
                if(adjacencyMatrix[i][j] == 1)
                {
                    countEdges++;
                }
            }
            Node newNode = new Node(i, countEdges);
            insertOneNode(newNode);
        }
        
    }

	
	public int checkAdjacent(int nodeID, int color)
	{
		for(int i = 1; i < numNode + 1; i++)
		{
			if(adjacencyMatrix[nodeID][i] == 1 && adjacencyMatrix[i][i] == color)
			{
				return 1;
			}
		}
		return 0;
	}
}
