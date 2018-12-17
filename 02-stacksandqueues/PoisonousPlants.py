#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the poisonousPlants function below.
def poisonousPlants(p):
    count = 0
    while len(p) > 0 :
        p_temp = []
        foundDead = False
        p_temp.append(p[0])
        for idx in range(1, len(p)):
            if p[idx-1] >= p[idx] :
                p_temp.append(p[idx])
            else :
                foundDead = True
                continue
        p = p_temp
        if foundDead :
            count = count + 1
        else :
            break
    return count


if __name__ == '__main__':
    n = int(input())

    p = list(map(int, input().rstrip().split()))

    result = poisonousPlants(p)

    print (result)
