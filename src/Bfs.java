public class Bfs {

    public static String DM_BFS(int[][] GraphMatrix_1, int n) {
        boolean[] visited = new boolean[n + 1];
        IntQueue queue = new IntQueue(n);
        StringBuilder result = new StringBuilder();

        // 시작 정점 1을 발견하고 큐에 넣는다.
        visited[1] = true;
        queue.enqueue(1);

        while (!queue.isEmpty()) {
            int current = queue.dequeue();

            // 방문 순서 기록
            if (result.length() > 0) {
                result.append(" – ");
            }
            result.append(current);

            // 번호가 작은 이웃부터 확인
            for (int next = 1; next <= n; next++) {
                if (GraphMatrix_1[current][next] != 0 && !visited[next]) {
                    // 큐에 중복으로 들어가지 않도록 넣기 전에 표시
                    visited[next] = true;
                    queue.enqueue(next);
                }
            }
        }

        return result.toString();
    }

    // 배열로 직접 구현한 큐
    private static class IntQueue {
        private final int[] data;
        private int front;
        private int rear;

        IntQueue(int capacity) {
            data = new int[capacity];
            front = 0;
            rear = 0;
        }

        boolean isEmpty() {
            return front == rear;
        }

        void enqueue(int value) {
            if (rear == data.length) {
                throw new IllegalStateException("큐가 가득 찼습니다.");
            }

            data[rear] = value;
            rear++;
        }

        int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("큐가 비어 있습니다.");
            }

            int value = data[front];
            front++;

            return value;
        }
    }
}