#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the stepPerms function below.
def stepPerms(n, stepVals):
    if n < 0 :
        return 0
    if n == 0 :
        return 1
    else :
        v1 = stepVals[n-1] if stepVals[n-1] != 0 else stepPerms(n-1, stepVals)
        v2 = stepVals[n-2] if stepVals[n-2] != 0 else stepPerms(n-2, stepVals)
        v3 = stepVals[n-3] if stepVals[n-3] != 0 else stepPerms(n-3, stepVals)
        val = (v1 + v2 + v3)%10000000007
        stepVals[n] = val
    return stepVals[n]

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = int(input())

    for s_itr in range(s):
        n = int(input())
        stepVals = [0] * (n+1)
        res = stepPerms(n, stepVals)

        fptr.write(str(res) + '\n')

    fptr.close()
