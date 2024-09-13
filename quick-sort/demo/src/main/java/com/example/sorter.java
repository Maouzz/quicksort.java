package com.example;

public class sorter {

    public void sort(int [] list){
        /////////////overlist gernarator
        int overlistindex = 0;
        int [][] overlist = new int [list.length][list.length + 10];
        for (int i = 0; i < list.length; i++) {
            overlist[i][0] = list[i];
        }

        ///////////Main
        boolean listsolved = false;
        int k = 0;
        while (listsolved == false) {
            writer(overlist, overlistindex);
            overlistindex += 2;
            for (int i = 0; i < overlist.length; i++) {
                if (overlist[i][overlistindex - 1] == 1) {
                   k += 1;
                } 
                else{
                    k = 0;
                }
            }
            if (k >= overlist.length - 1) {
                listsolved = true;
            }
        }
       
        //////////Ouput
        for (int i = 0; i < overlistindex + 1; i++) {
            for (int t = 0; t < overlist.length; t++) {
                if (i==0) {
                    System.out.print("\u001B[31m[\u001B[0m" + overlist[t][i] +"\u001B[31m]\u001B[0m");
                }
                else{
                    if (i%2 ==0) {
                        System.out.print("\u001B[35m[\u001B[0m" + overlist[t][i] +"\u001B[35m]\u001B[0m");
                    }
                    else{
                        if (overlist[t][i] == 1) {
                            System.out.print("\u001B[32m[\u001B[0m" + overlist[t][i] +"\u001B[32m]\u001B[0m");
                        }
                        else{
                            System.out.print("\u001B[34m[" + overlist[t][i] +"]\u001B[0m");
                        } 
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < overlist.length; i++) {
            System.out.print("\u001B[36m[\u001B[0m" + overlist[i][overlistindex] +"\u001B[36m]\u001B[0m");
        }
    }

    ////////// Methodes
    private void writer(int[][] overlist, int overlistindex){
        int repeats = 1;
        int ones = 0;
        if (overlistindex != 0) {
            for (int i = 0; i < overlist.length; i++) { // couts how many repeats are needet to fully sort one row once 
                if(overlist[i][overlistindex - 1] == 1 && i + 1 < overlist.length){
                    repeats += 1;
                }
            }
        }
        for (int r = 0; r < repeats; r++) {
            int pvmin = 0;
            int pvmax = 0;
            int pvindex = 0;
            boolean intervall = false;
            int ä = -1;
            if (overlistindex != 0) {         
                int onesscaned = 0;
                for (int p = 0; p < overlist.length; p++) { // sets the starttingpoint where the row is still unsorted 
                    if(overlist[p][overlistindex - 1] == 1){
                        onesscaned += 1;
                        if (ones == onesscaned) {
                            ä = p; 
                        }
                    }
                }
            }   

            ///////intervallscout
            while (intervall == false) {
                if (overlistindex == 0) {
                    pvmin = 0;
                    pvmax = overlist.length;
                    intervall = true;
                }
                else{
                    for (int k = ä + 1; k < overlist.length; k++) {
                        if (overlist[k][overlistindex - 1] == 1 && (k - ä) > 1 ) {
                            intervall = true;
                            ones += 1;
                            pvmin = ä + 1;
                            pvmax = k;
                            break;
                        }
                        if ( k == overlist.length - 1) {
                            intervall = true;
                            ones += 1;
                            pvmin = ä + 1;
                            pvmax = k + 1;
                            break;
                        }
                    }
                }
            }
            int[] splitlist = listsplitter(overlist, overlistindex, pvmin, pvmax);
            int pv = splitlist[splitlist.length - 1]; 
            splitlist = listsorter(splitlist);
            for (int i = 0; i < splitlist.length - 1; i++) { // Overlist pvindex set
                if (splitlist[i] == pv) {
                    pvindex = i;
                }
            }
            if (splitlist[splitlist.length - 1] == pv) {
                pvindex = splitlist.length - 1;
            }
            int onesscaned = 0;
            int oneposition = 0;
            if (overlistindex != 0) {
                for (int p = 0; p < overlist.length; p++) { // looks for the set starttingpoint
                    if(overlist[p][overlistindex - 1] == 1){
                        onesscaned += 1;
                        if (ones - 1 == onesscaned) {
                            oneposition = p;
                            pvindex += 1;
                        }
                    }
                }
            }
            overlist[pvindex + oneposition][overlistindex + 1] = 1; // pvindex = overlist[][] == 1

            /////////Overlist write
            for (int i = 0; i < splitlist.length; i++) {
                overlist[pvmin + i][overlistindex + 2] = splitlist[i];
            }
        }
        if (overlistindex != 0) {
            int coutzero = 0;
            for (int t = 0; t < overlist.length; t++) {
                if (overlist[t][overlistindex - 1] == 0) {
                    coutzero += 1;
                    if (t + 1 == overlist.length) {
                        if(coutzero == 1){
                            overlist[t][overlistindex + 1] = 1;
                            overlist[t][overlistindex + 2] = overlist[t][overlistindex];
                        }
                    }
                    else{
                        if (coutzero == 1 && overlist[t + 1][overlistindex - 1] == 1) {
                            overlist[t][overlistindex + 1] = 1;
                            overlist[t][overlistindex + 2] = overlist[t][overlistindex];
                        }
                    }
                }
                if (overlist[t][overlistindex - 1] == 1) {
                    coutzero = 0;
                    overlist[t][overlistindex + 2] = overlist[t][overlistindex];
                    overlist[t][overlistindex + 1] = 1;
                }
            }
        }
    }

    private int [] listsorter(int [] list){
        int pv = list[list.length-1];  // pivot
        int ci = 0;  // current index
        int si = -1;  // swap index
        while (ci < list.length) {
            if (list[ci] > pv) {
                ci += 1;
            }
            if (ci >= list.length) {
                break;
            }
            if (list[ci] <= pv) {
                si += 1;
                if (ci > si) {
                    int z = list[si];
                    list[si] = list[ci];
                    list[ci] = z;
                }
                else{
                    ci += 1;
                }
            }
        }
        return list;
    }

    
    private int[] listsplitter(int [][]overlist,int overlistindex,int pvmin, int pvmax){
        int[]splitlist = new int[pvmax - pvmin];  
        for (int i = 0; i < pvmax - pvmin; i++) {
            splitlist[i] = overlist[pvmin + i][overlistindex];
        }
        return splitlist;
    }
}