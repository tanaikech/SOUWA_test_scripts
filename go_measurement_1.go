// using "+="

package main

import (
	"fmt"
	"strings"
	"time"
)

func makearray(array [][]string) {
	for i := range array {
		array[i][0] = fmt.Sprintf("%07d", i+1)
		array[i][1] = "a"
	}
}

func division0(array [][]string) string {
	all := ""
	ecode := "\n"
	for i := range array {
		all += strings.Join(array[i], ",") + ecode
	}
	return all
}

func division1(an [][]string, row int, d int) string {
	str := ""
	result := ""
	ecode := "\n"
	ap := 0
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			str += strings.Join(an[j], ",") + ecode
		}
		ap = i
		result += str
		str = ""
	}
	return result
}

func division2(row int, d int, an [][]string, ar []string) {
	str := ""
	ecode := "\n"
	ap := 0
	arp := 0
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			str += strings.Join(an[j], ",") + ecode
		}
		ap = i
		ar[arp] = str
		arp++
		str = ""
	}
}

func division3(row int, d int, an []string, ar []string) {
	ar = make([]string, len(an)/d)
	str := ""
	ap := 0
	arp := 0
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			str += an[j]
		}
		ap = i
		ar[arp] = str
		arp++
		str = ""
	}
}

func division4(row int, d int, an []string) string {
	str := ""
	result := ""
	ap := 0
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			str += an[j]
		}
		ap = i
		result += str
		str = ""
	}
	return result
}

func main() {
	theta := 10000
	omega := 3
	phi := 10

	var result string
	row := theta
	basearray := make([][]string, row)
	for i := range basearray {
		basearray[i] = make([]string, 2)
	}
	makearray(basearray)

	start := time.Now()
	end := time.Now()

	switch {
	case omega == 0:
		start = time.Now()
		result = division0(basearray)
		end = time.Now()
	case omega == 1:
		start = time.Now()
		result = division1(basearray, row, phi)
		end = time.Now()
	case omega == 2:
		start = time.Now()
		mdata := make([]string, len(basearray)/phi)
		division2(row, phi, basearray, mdata)
		result = division4(len(mdata), phi, mdata)
		end = time.Now()
	case omega >= 3:
		start = time.Now()
		mdata := make([]string, len(basearray)/phi)
		tdata := make([]string, len(basearray)/phi)
		division2(row, phi, basearray, tdata)
		mdata = tdata
		for k := 0; k < (omega - 2); k++ {
			division3(len(tdata), phi, mdata, tdata)
			mdata = make([]string, len(tdata))
			mdata = tdata
		}
		result = division4(len(mdata), phi, mdata)
		end = time.Now()
	}
	fmt.Printf("Processing time = %f [s]\n", (end.Sub(start)).Seconds())
	fmt.Println(len(result))
}
