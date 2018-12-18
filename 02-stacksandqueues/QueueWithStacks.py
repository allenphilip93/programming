class MyQueue(object):
    def __init__(self):
        self.enqueueStack = []
        self.dequeueStack = []
    
    def peek(self):
        if len(self.dequeueStack) == 0 :
            # copy more from enqueue stack
            while len(self.enqueueStack) > 0 :
                self.dequeueStack.append(self.enqueueStack.pop())
        return self.dequeueStack[-1]
        
    def pop(self):
        if len(self.dequeueStack) == 0 :
            # copy more from enqueue stack
            while len(self.enqueueStack) > 0 :
                self.dequeueStack.append(self.enqueueStack.pop())
        self.dequeueStack.pop()
        
    def put(self, value):
        self.enqueueStack.append(value)
        

queue = MyQueue()
t = int(input())
for line in range(t):
    values = map(int, input().split())
    values = list(values)
    if values[0] == 1:
        queue.put(values[1])        
    elif values[0] == 2:
        queue.pop()
    else:
        print(queue.peek())

