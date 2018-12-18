#!/bin/python3

import math
import os
import random
import re
import sys

class DoublyLinkedListNode:
    def __init__(self, node_data):
        self.data = node_data
        self.next = None
        self.prev = None

class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def insert_node(self, node_data):
        node = DoublyLinkedListNode(node_data)

        if not self.head:
            self.head = node
        else:
            self.tail.next = node
            node.prev = self.tail


        self.tail = node

def print_doubly_linked_list(node, sep):
    while node:
        if node:
            print (node.data)
        node = node.next

# Complete the sortedInsert function below.

#
# For your reference:
#
# DoublyLinkedListNode:
#     int data
#     DoublyLinkedListNode next
#     DoublyLinkedListNode prev
#
#
def sortedInsert(head, data):
    node = DoublyLinkedListNode(data)
    if head == None:
        return node
    curr = head
    while curr is not None:
        if curr.prev is None and curr.data >= data :
            node.next = curr
            curr.prev = node
            if curr == head :
                head = node
            break
        if curr.next is None and curr.data <= data :
            node.prev = curr
            curr.next = node
            break
        if curr.data > data :
            curr.prev.next = node
            curr.prev = node
            node.prev = curr.prev
            node.next = curr
            break
        curr = curr.next
    return head

if __name__ == '__main__':
    t = int(input())
    
    for t_itr in range(t):
        llist_count = int(input())

        llist = DoublyLinkedList()

        for _ in range(llist_count):
            llist_item = int(input())
            llist.insert_node(llist_item)

        data = int(input())

        llist1 = sortedInsert(llist.head, data)
        
        print ("OUTPUT")

        print_doubly_linked_list(llist1, ' ')