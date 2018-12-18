#!/bin/python3
# Lemma 1: The sum of the digits of any number N to base 10 is equal to N minus some multiple of 9.
# Lemma 2: The digit sum (the iterative sum of the digits) of any N to base 10 is equal to N minus the highest multiple of 9 that leaves a single digit.

import math
import os
import random
import re
import sys

# Complete the superDigit function below.
def superDigit(n, k):
    if len(n) == 1:
        return int(n[0])
    else :
        digitSum = 0
        for character in n :
            intVal = int(character)
            digitSum = digitSum + intVal
        return superDigit(str(digitSum * k), 1)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nk = input().split()

    n = nk[0]

    k = int(nk[1])

    result = superDigit(n, k)

    fptr.write(str(result) + '\n')

    fptr.close()