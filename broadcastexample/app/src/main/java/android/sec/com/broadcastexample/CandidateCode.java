package android.sec.com.broadcastexample;

/**
 * Created by shiv on 5/6/2017.
 */

import java.io.*;
import java.util.*;

public class CandidateCode {
    public static int encoded_msg(int input1[], String[] input2) {

        int N = input1[0];
        int M = input1[1];
        int Answer = 0;
        int mat[][] = new int[N][M];
        int visited[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0, k = 0; j < input2[i].length(); ) {
                mat[i][k] = input2[i].charAt(j);
                j = j + 2;
                k = k + 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 1 && visited[i][j] == 0) {
                    int count = findlargest(mat, visited, i, j, N, M);
                    if (count > Answer) {
                        Answer = count;
                    }
                }
            }
        }
        return Answer;
    }

    public static int findlargest(int mat[][], int visited[][], int x, int y, int N, int M) {

        if (x < 0 || x >= N || y < 0 || y >= M || mat[x][y] == 0 || visited[x][y] == 1) {
            return 0;
        }
        if (visited[x][y] == 0) {
            int count = 1;
            visited[x][y] = 1;
            count = count + findlargest(mat, visited, x + 1, y, N, M);
            count = count + findlargest(mat, visited, x, y + 1, N, M);
            count = count + findlargest(mat, visited, x - 1, y, N, M);
            count = count + findlargest(mat, visited, x, y - 1, N, M);
            return count;
        }
        return 0;
    }
}
