#include <iostream>
#include <iomanip>
#include <vector>
#include <string>
#include <sstream>
#include <chrono>

using namespace std;
vector<vector<string>> basedata;
vector<string> mdata;
vector<string> tdata;


string divide0(int row, vector<vector<string>> basedata){
    string result = "";
    string ecode = "\n";
    for (int i = 0; i < row; i++){
        result += basedata[i][0] + "," + basedata[i][1] + ecode;
    }
    return result;
}


string divide1(int row, int phi, vector<vector<string>> basedata){
    string st = "";
    string result = "";
    string ecode = "\n";
    int ap = 0;
    for (int i = phi; i < (row + 1); i+=phi){
        for (int j = ap; j < i; j++){
            st += basedata[j][0] + "," + basedata[j][1] + ecode;
        }
        ap = i;
        result += st;
        st = "";
    }
    return result;
}


void divide2(int row, int phi, vector<vector<string>> basedata, vector<string>& tdata){
    string st = "";
    int ap = 0;
    int arp = 0;
    for (int i = phi; i < (row + 1); i+=phi){
        for (int j = ap; j < i; j++){
            st += basedata[j][0] + "," + basedata[j][1] + "\n";
        }
        ap = i;
        tdata[arp] = st;
        arp++;
        st = "";
    }
}


void divide3(int row, int phi, vector<string> mdata, vector<string>& tdata){
    tdata = vector<string>(mdata.size()/phi);
    string st = "";
    int ap = 0;
    int arp = 0;
    for (int i = phi; i < (row + 1); i+=phi){
        for (int j = ap; j < i; j++){
            st += mdata[j];
        }
        ap = i;
        tdata[arp] = st;
        arp++;
        st = "";
    }
}


string divide4(int row, int phi, vector<string> mdata){
    string st = "";
    string result = "";
    int ap = 0;
    for (int i = phi; i < (row + 1); i+=phi){
        for (int j = ap; j < i; j++){
            st += mdata[j];
        }
        ap = i;
        result += st;
        st = "";
    }
    return result;
}


int main() {
    const int theta = 10000;
    const int omega = 3;
    const int phi = 10;

    const int row = theta;
    const int col = 2;
    string all;
    chrono::system_clock::time_point start, end;
    basedata = vector<vector<string>>(row, vector<string>(col));
    mdata = vector<string>(row / phi);
    tdata = vector<string>(row / phi);
    stringstream tmp;

    for (int i=0; i<row; i++) {
        tmp << setfill('0') << setw(7) << right << (i + 1);
        basedata[i][0] = tmp.str();
        basedata[i][1] = "a";
        tmp.str("");
    }

    if (omega == 0) {
        start = chrono::system_clock::now();
        all = divide0(row, basedata);
        end = chrono::system_clock::now();
    }
    else if (omega == 1) {
        start = chrono::system_clock::now();
        all = divide1(row, phi, basedata);
        end = chrono::system_clock::now();
    }
    else if (omega == 2) {
        start = chrono::system_clock::now();
        divide2(row, phi, basedata, tdata);
        all = divide4(tdata.size(), phi, tdata);
        end = chrono::system_clock::now();
    }
    else if (omega >= 3) {
        start = chrono::system_clock::now();
        divide2(row, phi, basedata, tdata);
        mdata = tdata;
        for (int k = 0; k < (omega - 2); k++){
            divide3(mdata.size(), phi, mdata, tdata);
            mdata = vector<string>(tdata.size());
            mdata = tdata;
        }
        all = divide4(mdata.size(), phi, mdata);
        end = chrono::system_clock::now();
    }

    double mstime = chrono::duration_cast<chrono::milliseconds>(end - start).count();
    cout << "Processing time = " << mstime << " [ms]\n";
    cout << "result.length = " << all.size() << endl;

    vector<vector<string>>().swap(basedata);
    vector<string>().swap(mdata);
    vector<string>().swap(tdata);

    return 0;
}


