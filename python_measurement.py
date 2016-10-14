#!/usr/bin/env python
# -*- coding: UTF-8 -*-
import time


def MakeArray(row):
    theta = [0 for i in range(row)]
    for i in range(0, row):
        theta[i] = [str(i + 1).zfill(7), 'a']
    return theta


def Division_0(theta):
    ecode = '\n'
    string = ''
    for dat in theta:
        string += ','.join(dat) + ecode
    return string


def Division_1(theta, phi):
    ecode = '\n'
    string = ''
    result = ''
    AP = 0
    for i in range(phi, len(theta) + 1, phi):
        for j in range(AP, i):
            string += ','.join(theta[j]) + ecode
        AP = i
        result += string
        string = ''
    return result


def Division_2(theta, phi):
    ecode = '\n'
    string = ''
    AP = 0
    Array = [0 for i in range(int(len(theta) / phi))]
    ArrayP = 0
    for i in range(phi, len(theta) + 1, phi):
        for j in range(AP, i):
            string += ','.join(theta[j]) + ecode
        AP = i
        Array[ArrayP] = string
        ArrayP += 1
        string = ''
    return Array


def Division_3(theta, phi):
    string = ''
    AP = 0
    Array = [0 for i in range(int(len(theta) / phi))]
    ArrayP = 0
    for i in range(phi, len(theta) + 1, phi):
        for j in range(AP, i):
            string += theta[j]
        AP = i
        Array[ArrayP] = string
        ArrayP += 1
        string = ''
    return Array


def Division_4(theta, phi):
    string = ''
    result = ''
    AP = 0
    for i in range(phi, len(theta) + 1, phi):
        for j in range(AP, i):
            string += theta[j]
        AP = i
        result += string
        string = ''
    return result


theta = MakeArray(10000)
omega = 3
phi = 10

if omega == 0:
    start = time.time()
    result = Division_0(theta)
    Processing_time = time.time() - start

elif omega == 1:
    start = time.time()
    result = Division_1(theta, phi)
    Processing_time = time.time() - start

elif omega == 2:
    start = time.time()
    theta1 = Division_2(theta, phi)
    result = Division_4(theta1, phi)
    Processing_time = time.time() - start

elif omega >= 3:
    start = time.time()
    theta1 = Division_2(theta, phi)
    for p in range(0, (omega - 2)):
        theta2 = Division_3(theta1, phi)
        theta1 = theta2
    result = Division_4(theta1, phi)
    Processing_time = time.time() - start

print("Processing time = {0}".format(Processing_time) + " [s]")
print("result.length = {out}".format(out=len(result)))
