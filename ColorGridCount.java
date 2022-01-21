import java.awt.*;
import java.util.Random;

public class ColorGridCount {
    static int cols = 4;
    static int rows = 4;
    static final int visited[][] = new int [cols][rows];
    static final int result[][] = new int [cols][rows];
    static int count;

    public static void main(String args[]){
        //int row = 4, col =5;
        createGame(cols, rows);
    }

    public static void createGame(int cols,int rows){
        int array[][] = new int[cols][rows];
      //  final Color[] colors = new Color[]
       //         {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE,Color.BLACK};
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int min = 1;
                int max = 4;

                Random random = new Random();

                int value = random.nextInt(max + min) + min;
                array[i][j] =value;
                System.out.print(value+" ");
            }
            System.out.println();
        }
        largestConnectedColor(array);
    }

    static Color color(int num){
       // Color[] color = new Color[]
           //     {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE,Color.BLACK};
        Color color = null;
        switch(num) {
            case 1:
               color = Color.RED;
                System.out.println("The largest connected color is RED");
                break;
            case 2:
                color = Color.BLUE;
                System.out.println("The largest connected color is BLUE");
                break;
            case 3:
                color = Color.GREEN;
                System.out.println("The largest connected color is GREEN");
                break;
            case 4:
                color = Color.WHITE;
                System.out.println("The largest connected color is WHITE");
                break;
            case 5:
                color = Color.YELLOW;
                System.out.println("The largest connected color is YELLOW");
                break;
            default:
                // code block
        }

        return color;
    }

    static void beginTraverse()
    {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = 0;
        count =0;
    }

    static void calculateResult(int key, int array[][])
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (visited[i][j] ==1 &&
                        array[i][j] == key)
                    result[i][j] = visited[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }




    static void bfs(int x, int y, int i,
                    int j, int array[][])
    {

        if (x != y)
            return;

        visited[i][j] = 1;
        count++;

        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((checkValidity(i + y_move[u], j + x_move[u], x, array)) == true)
                bfs(x, y, i + y_move[u],
                        j + x_move[u], array);
    }
    static boolean checkValidity(int x, int y, int key, int array[][])
    {

        if (x < rows && y < cols &&
                x >= 0 && y >= 0)
        {
            if (visited[x][y] == 0 &&
                    array[x][y] == key)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public static void largestConnectedColor(int array[][]){
        int minValue = Integer.MIN_VALUE;

        for (int i = 0; i < cols; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                beginTraverse();

                if (j + 1 < cols)
                    bfs(array[i][j], array[i][j + 1], i, j, array);

                if (count >= minValue)
                {
                    minValue = count;
                    calculateResult(array[i][j], array);
                }
                beginTraverse();

                if (i + 1 < rows)
                    bfs(array[i][j],
                            array[i + 1][j], i, j, array);

                if (count >= minValue)
                {
                    minValue = count;
                    calculateResult(array[i][j], array);
                }
            }
        }
        Color maxColor = color(minValue);
        System.out.println ("The largest connected color number is " + minValue );
    }
}
