import java.util.*;
class Mypair{
        int node ;
        int  weight ;
        Mypair(int node , int weight){
            this.node = node ;
            this.weight = weight ;
        }
    }
public class city
{

    public static void bfs( ArrayList<ArrayList<Mypair>> adj 
    , int start , boolean[] visited , int[] cost 
    , int[] base_rate
    ,int[] ans, int current ,
    int immediate_parent ){
            visited[current] = true;
            for( Mypair p : adj.get(start)){
                    
                    if(!visited[p.node]){
                        // if(start == 1 ){
                        //     ans[start] = 0 ;
                        //     bfs(adj , start , visited, cost, base_rate, ans , p.node , start);
                        // }
                        // else {
                        //     if(ans[immediate_parent]+base_rate[immediate_parent] + p.weight*cost[p.node]< ans[immediate_parent] + p.weight*cost[immediate_parent]){
                        //         ans[current] = ans[immediate_parent]+base_rate[current] + p.weight*cost[p.node] ;
                        //         bfs(adj, current , visited , cost ,base_rate , ans , p.node , current );
                        //     }
                        //     else{
                        //         ans[current] = ans[immediate_parent] + p.weight*cost[immediate_parent] ;
                        //         bfs(adj , start , visited , cost , base_rate , ans , p.node , current );
                        //     }

                        // }
                    }
            }
            return ;
    }
    public static void bsfs( ArrayList<ArrayList<Mypair>> adj 
    , int start , boolean[] visited , int[] cost 
    , int[] base_rate
    ,int[] ans, int current ,
    int immediate_parent ){
        visited[current] = true;
        for( Mypair p : adj.get(current)){
                if(!visited[p.node]){
                    if(p.node == 1)
                    {
                        ans[p.node] = 0 ;
                        bsfs(adj,start,visited,cost,base_rate,ans ,p.node,current);
                    }
                    else{
                        ans[p.node] = ans[immediate_parent];
                        if(base_rate[immediate_parent]+cost[immediate_parent]*p.weight<cost[start]*p.weight){
                            ans[p.node] += base_rate[immediate_parent]+cost[immediate_parent]*p.weight;
                            bsfs(adj,immediate_parent,visited,cost,base_rate,ans ,p.node,current);
                        }
                        else{
                            ans[p.node] += cost[start]*p.weight;
                            bsfs(adj,start,visited,cost,base_rate,ans ,p.node,current);
                        }
                    }
                }
        }
        return ;
    }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        ArrayList<ArrayList<Mypair>> adj = new ArrayList<>();
        for( int i = 0 ; i <= vertices ; i ++){
            adj.add(new ArrayList<>());
        }
        for( int i = 1 ; i < vertices ; i ++){
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            int weight = scanner.nextInt();
            adj.get(node1).add(new Mypair(node2,weight));
            adj.get(node2).add(new Mypair(node1,weight));
        }
        int cost[] = new int[vertices+1];
        int base_rate[] = new int[vertices+1];
        boolean visited[] = new boolean[vertices+1];
        int ans[] = new int[vertices+1];
        for( int i = 1 ; i <= vertices ; i ++){
            cost[i] = scanner.nextInt();
            base_rate[i] = scanner.nextInt();
        }
        for(Mypair p : adj.get(1))
            bsfs(adj, 1 , visited , cost ,base_rate , ans , p.node  , 1);
        for( int i = 1 ; i <= vertices ; i ++)
            System.out.printf("%d %d ", i ,ans[i]);
        //System.out.println(s);
	}
}