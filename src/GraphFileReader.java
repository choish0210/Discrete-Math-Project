import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 그래프 입력 파일을 읽어 인접 행렬로 변환하는 클래스.
 *
 * input1 형식:
 * 정점 개수
 * 정점번호 인접정점 인접정점 ...
 *
 * input2 형식:
 * 정점 개수
 * 정점번호 인접정점 가중치 인접정점 가중치 ...
 *
 * 한 파일에 여러 그래프가 있으면 위 형식이 반복된다.
 */
public class GraphFileReader {

    // DFS/BFS용 입력 파일 읽기
    public static List<GraphData> readGraphs(String fileName)
            throws IOException {
        return readGraphs(fileName, false);
    }

    // Dijkstra용 입력 파일 읽기
    public static List<GraphData> readWeightedGraphs(String fileName)
            throws IOException {
        return readGraphs(fileName, true);
    }

    private static List<GraphData> readGraphs(String fileName, boolean weighted)
            throws IOException {

        List<GraphData> graphs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                int n;
                try {
                    n = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            fileName + " " + lineNumber + "번째 줄: 정점 개수가 올바르지 않습니다.");
                }

                if (n < 1 || n > 100) {
                    throw new IllegalArgumentException(
                            fileName + " " + lineNumber + "번째 줄: 정점 개수는 1~100이어야 합니다.");
                }

                int[][] matrix = new int[n + 1][n + 1];

                for (int i = 1; i <= n; i++) {
                    line = reader.readLine();
                    lineNumber++;

                    if (line == null) {
                        throw new IllegalArgumentException(
                                fileName + ": 그래프의 인접 정보가 부족합니다.");
                    }

                    line = line.trim();
                    if (line.isEmpty()) {
                        i--;
                        continue;
                    }

                    String[] values = line.split("\\s+");

                    int vertex;
                    try {
                        vertex = Integer.parseInt(values[0]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                fileName + " " + lineNumber + "번째 줄: 정점 번호가 올바르지 않습니다.");
                    }

                    if (vertex < 1 || vertex > n) {
                        throw new IllegalArgumentException(
                                fileName + " " + lineNumber + "번째 줄: 정점 번호가 범위를 벗어났습니다.");
                    }

                    if (weighted && (values.length - 1) % 2 != 0) {
                        throw new IllegalArgumentException(
                                fileName + " " + lineNumber + "번째 줄: 인접 정점과 가중치는 쌍으로 입력해야 합니다.");
                    }

                    if (weighted) {
                        for (int j = 1; j < values.length; j += 2) {
                            int adjacent = Integer.parseInt(values[j]);
                            int weight = Integer.parseInt(values[j + 1]);

                            if (adjacent < 1 || adjacent > n || weight < 0) {
                                throw new IllegalArgumentException(
                                        fileName + " " + lineNumber + "번째 줄: 인접 정점 또는 가중치가 올바르지 않습니다.");
                            }

                            matrix[vertex][adjacent] = weight;
                        }
                    } else {
                        for (int j = 1; j < values.length; j++) {
                            int adjacent = Integer.parseInt(values[j]);

                            if (adjacent < 1 || adjacent > n) {
                                throw new IllegalArgumentException(
                                        fileName + " " + lineNumber + "번째 줄: 인접 정점 번호가 범위를 벗어났습니다.");
                            }

                            matrix[vertex][adjacent] = 1;
                        }
                    }
                }

                graphs.add(new GraphData(n, matrix));
            }
        }

        if (graphs.isEmpty()) {
            throw new IllegalArgumentException(fileName + "에 그래프 데이터가 없습니다.");
        }

        return graphs;
    }

    public static class GraphData {
        private final int vertexCount;
        private final int[][] graphMatrix;

        public GraphData(int vertexCount, int[][] graphMatrix) {
            this.vertexCount = vertexCount;
            this.graphMatrix = graphMatrix;
        }

        public int getVertexCount() {
            return vertexCount;
        }

        public int[][] getGraphMatrix() {
            return graphMatrix;
        }
    }
}
