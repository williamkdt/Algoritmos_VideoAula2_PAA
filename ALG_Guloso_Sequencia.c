#include <stdio.h>
#include <stdlib.h>
#define N 5

int VerDir (int grid[N][N], int i, int j){
    if(i >= N || j+1 >= N)
        return 0;
    else if((grid[i][j] == (grid[i][j+1])-1) || (grid[i][j] == (grid[i][j+1])+1)){
        return 1;
    }
    else
        return 0;
}

int VerBx (int grid[N][N], int i, int j){
    if(i+1 >= N || j >= N)
        return 0;
    else if((grid[i][j] == (grid[i+1][j])-1) || (grid[i][j] == (grid[i+1][j])+1)){
        return 1;
    }
    else
        return 0;
}

void SEQUENCIA(int grid[N][N]){

    int i=0, j=0, dir, bx, seq=0;

    while(j < N || i < N){

        if((VerDir(grid, i, j)) && (VerBx(grid, i, j))){
            dir = 0;
            bx = 0;
            if(VerDir(grid, i, j+1))
                dir++;
            if(VerBx(grid, i, j+1))
                dir++;
            if(VerDir(grid, i+1, j))
                bx++;
            if(VerBx(grid, i+1, j))
                bx++;

            printf("%d - ", grid[i][j]);
            seq++;
            if(bx > dir)
                i++;
            else if(dir > bx)
                j++;
            else if(j > i)
                i++;
            else
                j++;
        }
        else if(VerDir(grid, i, j)){
                printf("%d - ", grid[i][j]);
                seq++;
                j++;
            }
        else if (VerBx(grid, i, j)){
                printf("%d - ", grid[i][j]);
                seq++;
                i++;
            }
        else if(j < N && seq == 0){
                j++;
                if(i == N && j == N){
                    printf("%d - ", grid[i-1][j-1]);
                    printf("\n\n\tTamanho da Sequencia: %d",seq+1);
                    break;
                }
            }
        else if((j == N && i < N) && seq == 0){
            i++;
            j=0;
        }
        else{
            printf("%d - ", grid[i][j]);
            printf("\n\n\tTamanho da Sequencia: %d",seq+1);
            break;
        }
    }
}

int main(){

    int grid[N][N] = {
        { 7, 5, 2, 3, 1 },
        { 3, 4, 1, 4, 4 },
        { 1, 5, 6, 7, 8 },
        { 3, 4, 5, 8, 9 },
        { 3, 2, 2, 7, 6 }};

    printf("Sequencia Encontrada:\n\n");
    SEQUENCIA(grid);
    printf("\n\n");

    return 0;
}
