#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the isBalanced function below.
def isBalanced(s):
    stack = []
    for bracket in s :
        if bracket in ['{', '(', '['] :
            stack.append(bracket)
        else :
            if len(stack) == 0 :
                return "NO"
            top = stack.pop()
            if (bracket == '}' and top == '{') or (bracket == ')' and top == '(') or (bracket == ']' and top == '[') :
                continue
            return "NO"
    if len(stack) == 0 :
        return "YES"
    return "NO"

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input())

    for t_itr in range(t):
        s = input()

        result = isBalanced(s)

        fptr.write(result + '\n')

    fptr.close()
