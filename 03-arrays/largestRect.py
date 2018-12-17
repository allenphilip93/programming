#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the largestRectangle function below.
def largestRectangle(h):
    # stack to maintain all unsolved subproblems
    stack = []
    # maxSumVal
    maxSumVal = 0

    for index in range(len(h)):
        # if stack is empty, push and continue
        if len(stack) == 0 :
            stack.append(index)
            continue

        # if not check with top element
        curr = stack[-1]
        # if top elem is larger than curr elem then few subproblems gets solved
        while h[curr] > h[index] and len(stack) > 0:
            print(stack)
            stack.pop()
            leftIndex = 0 if len(stack) == 0 else (stack[-1]+1)
            sumVal = h[curr] * (index - leftIndex)
            maxSumVal = max(sumVal, maxSumVal)
            print ("(leftindx, curr, rightIdx)")
            print (leftIndex, curr, index)
            print ("Sum = " + str(sumVal))
            curr = leftIndex - 1
        # append current index to the stack
        stack.append(index)
    print (stack)
    while len(stack) > 0 :
        curr = stack.pop()
        leftIndex = 0 if len(stack) == 0 else (stack[-1]+1)
        sumVal = h[curr] * (len(h) - leftIndex)
        print ("(leftindx, curr, rightIdx)")
        print (leftIndex, curr, len(h))
        maxSumVal = max(sumVal, maxSumVal)
        curr = leftIndex - 1
    return maxSumVal



if __name__ == '__main__':
    n = int(input())

    h = list(map(int, input().rstrip().split()))

    result = largestRectangle(h)

    print (result)
