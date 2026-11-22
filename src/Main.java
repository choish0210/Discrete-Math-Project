import java.io.IOException;
import java.util.List;

/**
 * 그래프 파일을 읽고 DFS, BFS, Dijkstra 알고리즘을 실행하는 클래스.
 */

public class Main {

    public static void main(String[] args) {

        try {
            // input1.txt: DFS와 BFS 실행
            List<GraphFileReader.GraphData> traversalGraphs =
                    GraphFileReader.readGraphs("input1.txt");

            System.out.println("1. 그래프 탐방 수행 결과");

            int graphNumber = 1;

            for (GraphFileReader.GraphData graph : traversalGraphs) {
                int n = graph.getVertexCount();
                int[][] matrix = graph.getGraphMatrix();

                System.out.println();
                System.out.println("그래프 [" + graphNumber + "]");
                System.out.println("----------------------------");

                System.out.println("깊이 우선 탐색");
                System.out.println(Dfs.DM_DFS(matrix, n));

                System.out.println("너비 우선 탐색");
                System.out.println(Bfs.DM_BFS(matrix, n));

                System.out.println("============================");
                graphNumber++;
            }

            // input2.txt: Dijkstra 실행
            List<GraphFileReader.GraphData> shortestPathGraphs =
                    GraphFileReader.readWeightedGraphs("input2.txt");

            System.out.println();
            System.out.println("2. 최단 경로 구하기 수행 결과");

            graphNumber = 1;

            for (GraphFileReader.GraphData graph : shortestPathGraphs) {
                int n = graph.getVertexCount();
                int[][] matrix = graph.getGraphMatrix();

                System.out.println();
                System.out.println("그래프 [" + graphNumber + "]");
                System.out.println("----------------------------");
                System.out.println("시작점: 1");

                Dijkstra.ShortPath(n, matrix);

                System.out.println("============================");
                graphNumber++;
            }

        } catch (IOException e) {
            System.out.println("입력 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("입력 데이터 오류: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("프로그램 실행 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
