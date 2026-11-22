public class Dfs {
    public static String DM_DFS(int[][] GraphMatrix_1, int n) {
        boolean[] visited = new boolean[n + 1];
        IntStack stack = new IntStack(n);
        StringBuilder result = new StringBuilder();

        // 시작 정점 1을 방문하고 스택에 넣는다.
        visited[1] = true;
        stack.push(1);
        result.append(1);

        while (!stack.isEmpty()) {
            int current = stack.peek();
            int next = 0;

            // 방문하지 않은 이웃 중 가장 작은 번호 하나 선택
            for (int node = 1; node <= n; node++) {
                if (GraphMatrix_1[current][node] != 0 && !visited[node]) {
                    next = node;
                    break;
                }
            }

            if (next != 0) {
                // 다음 정점으로 깊게 들어간다.
                visited[next] = true;
                result.append(" – ").append(next);
                stack.push(next);
            } else {
                // 더 갈 곳이 없으면 이전 정점으로 돌아간다.
                stack.pop();
            }
        }

        return result.toString();
    }

    // 배열로 직접 구현한 스택
    private static class IntStack {
        private final int[] data;
        private int size;

        IntStack(int capacity) {
            data = new int[capacity];
            size = 0;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void push(int value) {
            if (size == data.length) {
                throw new IllegalStateException("스택이 가득 찼습니다.");
            }

            data[size] = value;
            size++;
        }

        int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("스택이 비어 있습니다.");
            }

            size--;
            return data[size];
        }

        int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("스택이 비어 있습니다.");
            }
            return data[size - 1];
        }
    }
}