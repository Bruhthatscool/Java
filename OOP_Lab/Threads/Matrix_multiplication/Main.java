// Multiply 2 matrices using threads, with each row of the resultant matrix computed by separate threads.

import java.io.*;

class Main{

        public static void main(String args[]) throws IOException{

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                //input rows and columns
                int r1, c1, r2, c2;

                do{

                        System.out.println("Enter no of rows and columns of \nA : ");
                        r1 = Integer.parseInt(br.readLine());
                        c1 = Integer.parseInt(br.readLine());

                        System.out.println("B : ");
                        r2 = Integer.parseInt(br.readLine());
                        c2 = Integer.parseInt(br.readLine());

                }while(c1 != r2);

                //create matrices A and B
                Matrix A = new Matrix(r1, c1);
                Matrix B = new Matrix(r2, c2);

                A.read();
                B.read();

                //matrix C
                Matrix C = new Matrix(A, B);

                for(int i = 0; i < r1; i++){

                        //start separate thread for each row of A
                        Thread t = new Thread(C);
                        C.row = i;
                        t.start();
                        try{
                                //suspend main thread till current thread ends
                                t.join();
                        }catch(Exception e){
                                System.out.println(e.getMessage());
                        }

                }

                //outputs
                System.out.println("\nA = ");
                A.display();

                System.out.println("B = ");
                B.display();

                System.out.println("\nA x B = C = ");
                C.display();

        }

}

class Matrix implements Runnable{

        int matrix[][], r, c;

        Matrix A, B;
        int row;

        //constructor for A and B
        Matrix(int r, int c){

                this.r = r;
                this.c = c;
                matrix = new int[r][c];
        }

        //constructor for C
        Matrix(Matrix A, Matrix B){

                this.A = A;
                this.B = B;
                r = A.r;
                c = B.c;
                matrix = new int[r][c];

        }

        //method to read A and B
        void read() throws IOException{

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter "+ r +"x"+ c +" elements : ");
                for(int i = 0; i < r; i++){

                        for(int j = 0; j < c; j++){

                                matrix[i][j] = Integer.parseInt(br.readLine());

                        }

                }

        }

        //thread - matrix multiplication method  for a single row
        public void run(){

                for(int j = 0; j < c; j++){

                        matrix[row][j] = 0;

                        for(int k = 0; k < A.c; k++){

                                matrix[row][j] += A.matrix[row][k] * B.matrix[k][j];

                        }

                }

        }

        //method to display matrix
        void display(){

                for(int i = 0; i < r; i++){

                        for(int j = 0; j < c; j++){

                                System.out.print(matrix[i][j] +" ");

                        }
                        System.out.println();

                }

        }
}
