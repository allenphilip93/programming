# Binary tree implementation in python

# Node abstraction
class Node:
    def __init__(self, data):
        self.left = None
        self.right = None
        self.data = data

# Tree implementation
class Tree:
    def __init__(self):
        self.root = None
        print("Tree has been created!")

    def getRoot(self):
        return self.root
    
    def addNode(self, data):
        newNode = Node(data)
        if self.root == None:
            self.root = newNode
        else :
            queue = [self.root]
            node = self.root
            while node is not None:
                


if __name__ == "__main__" :
    tree = Tree()
    