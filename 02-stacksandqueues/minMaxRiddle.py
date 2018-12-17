#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the riddle function below.
def riddle(arr):
    l = len(arr)
    res = [0] * l
    for index in range(l):
        if index > 0 :
            newArr = []
            for idx in range(len(arr)-1):
                newArr.append(min(arr[idx], arr[idx+1]))
            if len(newArr) > 0 :
                arr = newArr
        res[index] = max(arr)
    return res

if __name__ == '__main__':
    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = riddle(arr)

    print (res)
