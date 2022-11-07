public class HopfieldNetwork {
    int[][] matrixW;
    int[][] samples;


    HopfieldNetwork(int[][]... samples) {
        this.samples = new int[samples.length][];
        int[][][] matrixArray = new int[samples.length][][];
        for (int sampleIndex = 0; sampleIndex < samples.length; sampleIndex++) {
            int[] sampleArray = convertMatrixToArray(samples[sampleIndex]);
            this.samples[sampleIndex] = sampleArray;
            matrixArray[sampleIndex] = transposeArrayIntoMatrix(sampleArray);
        }
        int[][] matrixW = new int[matrixArray[0][0].length][matrixArray[0][0].length];
        for (int i = 0; i < matrixW.length; i++) {
            for (int j = 0; j < matrixW.length; j++) {
                int sum = 0;
                for (int[][] ints : matrixArray) {
                    sum += ints[i][j];
                }
                matrixW[i][j] = sum;
            }
        }
        for (int i = 0; i < matrixW.length; i++) {
            matrixW[i][i] = 0;    // обнуляем диагональ
        }
        this.matrixW = matrixW;
    }

    int find(int[][] sample) {
        int[] sampleArray = convertMatrixToArray(sample);
        int[] x1;
        while(true){
            x1 = multiplyMatrixWithArray(matrixW, sampleArray);
            for (int i = 0; i < x1.length; i++) {
                x1[i] = x1[i] >= 0 ? 1 : -1;
            }
            for (int i = 0; i < samples.length; i++) {
                int[] sample1 = samples[i];
                if (areArraysEqual(sample1, x1)) {
                    return i;
                }
            }
            sampleArray = x1;
        }
    }

    private int[] convertMatrixToArray(int[][] matrix) {
        int[] sampleArray = new int[matrix.length * matrix.length];
        int index = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                sampleArray[index++] = anInt;
            }
        }
        return sampleArray;
    }

    private int[][] transposeArrayIntoMatrix(int[] array) {
        int[][] matrix = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                matrix[i][j] = array[i] * array[j];
            }
        }
        return matrix;
    }
    private boolean areArraysEqual(int[] array1, int[] array2){
        for (int j = 0; j < array1.length; j++) {
            if (array1[j] != array2[j]) {
                return false;
            }
        }
        return true;
    }
    private int[] multiplyMatrixWithArray(int[][] matrix, int[] array){
        int result[] = new int[array.length];
        for (int i = 0; i < matrix.length; i++) {
            int[] raw = matrix[i];
            int sum = 0;
            for (int j = 0; j < raw.length; j++) {
                sum += raw[j] * array[j];
            }
            result[i] = sum;
        }
        return result;
    }
}

