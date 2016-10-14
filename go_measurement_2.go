// using "[]byte"

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
	st := ""
	ecode := "\n"
	bu := len(array) * 10 * 2
	mem1 := make([]byte, 0, bu)
	for i := range array {
		st = strings.Join(array[i], ",") + ecode
		mem1 = append(mem1, st...)
	}
	return string(mem1)
}

func division1(an [][]string, row int, d int) string {
	st := ""
	ecode := "\n"
	ap := 0
	bu := row * 10 * 2
	mem1 := make([]byte, 0, bu)
	mem2 := make([]byte, 0, bu)
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			st = strings.Join(an[j], ",") + ecode
			mem1 = append(mem1, st...)
		}
		ap = i
		mem2 = append(mem2, string(mem1)...)
		mem1 = make([]byte, 0, bu)
	}
	return string(mem2)
}

func division2(row int, d int, an [][]string, ar []string) {
	st := ""
	ecode := "\n"
	ap := 0
	arp := 0
	bu := row * 10 * 2
	mem1 := make([]byte, 0, bu)
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			st = strings.Join(an[j], ",") + ecode
			mem1 = append(mem1, st...)
		}
		ap = i
		ar[arp] = string(mem1)
		arp++
		mem1 = make([]byte, 0, bu)
	}
}

func division3(row int, d int, an []string, ar []string) {
	ar = make([]string, len(an)/d)
	ap := 0
	arp := 0
	bu := row * 10 * 2
	mem1 := make([]byte, 0, bu)
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			mem1 = append(mem1, string(an[j])...)
		}
		ap = i
		ar[arp] = string(mem1)
		arp++
		mem1 = make([]byte, 0, bu)
	}
}

func division4(row int, d int, an []string) string {
	ap := 0
	bu := row * 10 * 2
	mem1 := make([]byte, 0, bu)
	mem2 := make([]byte, 0, bu)
	for i := d; i < row+1; i += d {
		for j := ap; j < i; j++ {
			mem1 = append(mem1, string(an[j])...)
		}
		ap = i
		mem2 = append(mem2, string(mem1)...)
		mem1 = make([]byte, 0, bu)
	}
	return string(mem2)
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
