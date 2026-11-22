public class Dijkstra {
    static final long INF = Long.MAX_VALUE; // 도달할 수 없는 정점의 거리를 나타내는 상수

    /**
     * 다익스트라 알고리즘을 이용하여 최단 경로와 경로의 길이를 출력하는 메서드.
     *
     * @param n 그래프를 구성하는 전체 정점의 개수
     * @param GraphMatrix 그래프의 인접 행렬
     */
    public static void ShortPath(int n, int[][] GraphMatrix) {
        long[] distance = new long[101]; // 시작 정점 1부터 각 정점까지의 현재 최단 거리를 저장하는 배열
        int[] previous = new int[101]; // 각 정점에 도달하기 직전에 거쳐 온 이전 정점을 저장하는 배열
        boolean[] selected = new boolean[101]; // 각 정점의 최단 거리가 확정되었는지 확인하는 배열

        for (int i = 1; i <= n; i++) {
            distance[i] = INF;
            previous[i] = -1;
            selected[i] = false;
        }

        distance[1] = 0;

        for (int count = 1; count <= n; count++) {
            int current = -1;
            long min = INF;

            for (int i = 1; i <= n; i++) {
                if (!selected[i] && distance[i] < min) {
                    min = distance[i];
                    current = i;
                }
            }

            if (current == -1) {
                break;
            }
            selected[current] = true;

            for (int i = 1; i <= n; i++) {
                if (GraphMatrix[current][i] > 0 && !selected[i]) {
                    long newDistance = distance[current] + GraphMatrix[current][i];

                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                        previous[i] = current;
                    }
                }
            }
        }

        /** 시작 정점 1을 제외한 모든 정점의 최단 경로와 길이를 출력 */
        for (int i = 2; i <= n; i++) {
            System.out.print("정점 [" + i + "]: ");
            if (distance[i] == INF) {
                System.out.println("도달할 수 없음");
                continue;
            }

            int[] path = new int[101]; // 최단 경로에 포함되는 정점을 저장하는 배열
            int pathCount = 0;
            int current = i;

            while (current != -1) {
                path[pathCount++] = current;
                current = previous[current];
            }

            for (int j = pathCount - 1; j >= 0; j--) {
                System.out.print(path[j]);
                if (j > 0) {
                    System.out.print(" - ");
                }
            }

            System.out.println(", 길이: " + distance[i]);
        }
    }
}
