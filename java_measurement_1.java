// using "+="

public class java_measurement_1 {
    public static String[][] makearray(int row){
        String[][] ar = new String[row][2];
        for (int i = 0; i < row; i++) {
            ar[i][0] = String.format("%7s", i+1).replace(" ", "0");
            ar[i][1] = "b";
        }
        return ar;
    }

    public static String nodivision(int row, String[][] ar){
        String ecode = "\n";
        String result = "";
        for (int i = 0; i < row; i++) {
            result += String.join(",", ar[i]) + ecode;
        }
        return result;
    }

    public static String division0(int row, int d, String[][] ar){
        String str = "";
        String result = "";
        String ecode = "\n";
        int ap = 0;
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                str += String.join(",", ar[j]) + ecode;
            }
            ap = i;
            result += str;
            str = "";
        }
        return result;
    }

    public static void division1(int row, int d, String[][] an, String[] ar){
        String str = "";
        String ecode = "\n";
        int ap = 0;
        int arp = 0;
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                str += String.join(",", an[j]) + ecode;
            }
            ap = i;
            ar[arp] = str;
            arp++;
            str = "";
        }
    }

    public static void division2(int row, int d, String[] an, String[] ar){
        ar = new String[an.length/d];
        String str = "";
        int ap = 0;
        int arp = 0;
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                str += an[j];
            }
            ap = i;
            ar[arp] = str;
            arp++;
            str = "";
        }
    }

    public static String division3(int row, int d, String[] an){
        String str = "";
        String result = "";
        int ap = 0;
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                str += an[j];
            }
            ap = i;
            result += str;
            str = "";
        }
        return result;
    }

    public static void main(String[] args){
        int theta = 10000;
        int omega = 3;
        int phi = 10;

        int row = theta;
        String[][] array = makearray(row);
        long start = 0;
        long time = 0;
        String all = "";
        if (omega == 0){
            start = System.currentTimeMillis();
            all = nodivision(row, array);
            time = System.currentTimeMillis() - start;
        }else if (omega == 1){
            start = System.currentTimeMillis();
            all = division0(row, phi, array);
            time = System.currentTimeMillis() - start;
        }else if (omega == 2){
            start = System.currentTimeMillis();
            String[] mdata = new String[row/phi];
            division1(row, phi, array, mdata);
            all = division3(mdata.length, phi, mdata);
            time = System.currentTimeMillis() - start;
        }else if (omega >= 3){
            start = System.currentTimeMillis();
            String[] mdata = new String[row/phi];
            String[] tdata = new String[row/phi];
            division1(row, phi, array, tdata);
            mdata = tdata;
            for (int k = 0; k < (omega - 2); k++) {
                division2(tdata.length, phi, mdata, tdata);
                mdata = new String[tdata.length];
                mdata = tdata;
            }
            all = division3(mdata.length, phi, mdata);
            time = System.currentTimeMillis() - start;
        }
        System.out.println("Processing time = " + time + " [ms]");
        System.out.println("result.length = " + all.length());
    }
}
