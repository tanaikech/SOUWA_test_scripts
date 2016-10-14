// using "StringBuilder"

public class java_measurement_2 {
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
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < row; i++) {
            data.append(String.join(",", ar[i]) + ecode);
        }
        return data.toString();
    }

    public static String division0(int row, int d, String[][] ar){
        String ecode = "\n";
        int ap = 0;
        String result = "";
        StringBuilder data1 = new StringBuilder();
        StringBuilder data2 = new StringBuilder();
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                data1.append(String.join(",", ar[j]) + ecode);
            }
            ap = i;
            data2.append(data1.toString());
            data1.setLength(0);
        }
        return data2.toString();
    }

    public static void division1(int row, int d, String[][] an, String[] ar){
        String ecode = "\n";
        int ap = 0;
        int arp = 0;
        StringBuilder data = new StringBuilder();
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                data.append(String.join(",", an[j]) + ecode);
            }
            ap = i;
            ar[arp] = data.toString();
            arp++;
            data.setLength(0);
        }
    }

    public static void division2(int row, int d, String[] an, String[] ar){
        ar = new String[an.length/d];
        int ap = 0;
        int arp = 0;
        StringBuilder data = new StringBuilder();
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                data.append(an[j]);
            }
            ap = i;
            ar[arp] = data.toString();
            arp++;
            data.setLength(0);
        }
    }

    public static String division3(int row, int d, String[] an){
        int ap = 0;
        StringBuilder data1 = new StringBuilder();
        StringBuilder data2 = new StringBuilder();
        for (int i = d; i < (row+1); i+=d) {
            for (int j = ap; j < i; j++) {
                data1.append(an[j]);
            }
            ap = i;
            data2.append(data1.toString());
            data1.setLength(0);
        }
        return data2.toString();
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
