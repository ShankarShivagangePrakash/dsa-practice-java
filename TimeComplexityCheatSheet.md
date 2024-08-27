# Java Data Structures Time Complexity Cheat Sheet

## 1. List Implementations

| Data Structure         | Operation | Best Case | Worst Case | Notes |
|------------------------|-----------|-----------|------------|-------|
| **ArrayList**           | Add       | O(1)      | O(n)       | Best: When capacity is sufficient. Worst: When resizing is needed. |
|                        | Remove    | O(1)      | O(n)       | Best: Removing last element. Worst: Removing first element (shifts elements). |
|                        | Get       | O(1)      | O(1)       | Always O(1) since it’s indexed by array. |
|                        | Contains  | O(1)      | O(n)       | Best: If element is at the beginning. Worst: If element is not found. |
| **LinkedList**          | Add       | O(1)      | O(1)       | Always O(1) when adding at the head or tail. |
|                        | Remove    | O(1)      | O(n)       | Best: If removing at head or tail. Worst: If searching for element to remove. |
|                        | Get       | O(1)      | O(n)       | Best: First or last element. Worst: Element in the middle. |
|                        | Contains  | O(1)      | O(n)       | Best: If element is at the head. Worst: If element is not found. |
| **CopyOnWriteArrayList**| Add       | O(n)      | O(n)       | Always O(n) due to copying array. |
|                        | Remove    | O(n)      | O(n)       | Always O(n) due to copying array. |
|                        | Get       | O(1)      | O(1)       | Always O(1) since it’s indexed by array. |
|                        | Contains  | O(1)      | O(n)       | Best: If element is at the beginning. Worst: If element is not found. |

## 2. Set Implementations

| Data Structure          | Operation   | Best Case | Worst Case | Notes |
|-------------------------|-------------|-----------|------------|-------|
| **HashSet**              | Add         | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                         | Remove      | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                         | Contains    | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
| **LinkedHashSet**        | Add         | O(1)      | O(n)       | Similar to HashSet with predictable iteration order. |
|                         | Remove      | O(1)      | O(n)       | Similar to HashSet with predictable iteration order. |
|                         | Contains    | O(1)      | O(n)       | Similar to HashSet with predictable iteration order. |
| **TreeSet**              | Add         | O(log n)  | O(log n)   | Always O(log n) due to tree balancing. |
|                         | Remove      | O(log n)  | O(log n)   | Always O(log n) due to tree balancing. |
|                         | Contains    | O(log n)  | O(log n)   | Always O(log n) due to tree balancing. |
| **EnumSet**              | Add         | O(1)      | O(1)       | Very fast, backed by bit vectors. |
|                         | Remove      | O(1)      | O(1)       | Very fast, backed by bit vectors. |
|                         | Contains    | O(1)      | O(1)       | Very fast, backed by bit vectors. |
| **CopyOnWriteArraySet**  | Add         | O(n)      | O(n)       | Always O(n) due to copying array. |
|                         | Remove      | O(n)      | O(n)       | Always O(n) due to copying array. |
|                         | Contains    | O(1)      | O(n)       | Best: If element is at the beginning. Worst: If element is not found. |
| **ConcurrentSkipListSet**| Add         | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
|                         | Remove      | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
|                         | Contains    | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |

## 3. Queue Implementations

| Data Structure          | Operation   | Best Case | Worst Case | Notes |
|-------------------------|-------------|-----------|------------|-------|
| **PriorityQueue**        | Offer       | O(log n)  | O(log n)   | Always O(log n) due to heap properties. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), retrieves top element. |
|                         | Poll        | O(log n)  | O(log n)   | Always O(log n) due to heap properties. |
|                         | Remove      | O(log n)  | O(n)       | Worst: Searching for element to remove. |
| **LinkedList**           | Offer       | O(1)      | O(1)       | Always O(1) for queue operations. |
|                         | Peek        | O(1)      | O(1)       | Always O(1) for queue operations. |
|                         | Poll        | O(1)      | O(1)       | Always O(1) for queue operations. |
|                         | Remove      | O(1)      | O(n)       | Worst: When removing an element not at the front. |
| **ArrayDeque**           | Offer       | O(1)      | O(1)       | Resizable array provides amortized O(1). |
|                         | Peek        | O(1)      | O(1)       | Always O(1) for deque operations. |
|                         | Poll        | O(1)      | O(1)       | Always O(1) for deque operations. |
|                         | Remove      | O(1)      | O(n)       | Worst: Removing an element not at the front. |
| **ConcurrentLinkedQueue**| Offer       | O(1)      | O(1)       | Always O(1), designed for concurrency. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), designed for concurrency. |
|                         | Poll        | O(1)      | O(1)       | Always O(1), designed for concurrency. |
|                         | Remove      | O(1)      | O(n)       | Worst: When removing an element not at the front. |
| **ArrayBlockingQueue**   | Offer       | O(1)      | O(1)       | Always O(1), but may block if full. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), but may block if empty. |
|                         | Poll        | O(1)      | O(1)       | Always O(1), but may block if empty. |
|                         | Remove      | O(1)      | O(n)       | Worst: When removing an element not at the front. |
| **SynchronousQueue**     | Offer       | O(1)      | O(1)       | Always O(1), direct handoff, may block. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), direct handoff, may block. |
|                         | Poll        | O(1)      | O(1)       | Always O(1), direct handoff, may block. |
|                         | Remove      | O(1)      | O(n)       | Worst: When removing an element not at the front. |
| **DelayQueue**           | Offer       | O(log n)  | O(log n)   | Always O(log n) due to heap structure. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), retrieves top element. |
|                         | Poll        | O(log n)  | O(log n)   | Always O(log n) due to heap structure. |
|                         | Remove      | O(log n)  | O(n)       | Worst: Searching for an element to remove. |
| **LinkedBlockingQueue**  | Offer       | O(1)      | O(1)       | Always O(1), but may block if full. |
|                         | Peek        | O(1)      | O(1)       | Always O(1), but may block if empty. |
|                         | Poll        | O(1)      | O(1)       | Always O(1), but may block if empty. |
|                         | Remove      | O(1)      | O(n)       | Worst: When removing an element not at the front. |

## 4. Map Implementations

| Data Structure            | Operation     | Best Case | Worst Case | Notes |
|---------------------------|---------------|-----------|------------|-------|
| **HashMap**                | Get           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | ContainsKey   | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Put           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Remove        | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
| **LinkedHashMap**          | Get           | O(1)      | O(n)       | Similar to HashMap with predictable iteration order. |
|                           | ContainsKey   | O(1)      | O(n)       | Similar to HashMap with predictable iteration order. |
|                           | Put           | O(1)      | O(n)       | Similar to HashMap with predictable iteration order. |
|                           | Remove        | O(1)      | O(n)       | Similar to HashMap with predictable iteration order. |
| **TreeMap**                | Get           | O(log n)  | O(log n)   | Always O(log n) due to tree structure. |
|                           | ContainsKey   | O(log n)  | O(log n)   | Always O(log n) due to tree structure. |
|                           | Put           | O(log n)  | O(log n)   | Always O(log n) due to tree structure. |
|                           | Remove        | O(log n)  | O(log n)   | Always O(log n) due to tree structure. |
| **ConcurrentHashMap**      | Get           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | ContainsKey   | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Put           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Remove        | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
| **ConcurrentSkipListMap**  | Get           | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
|                           | ContainsKey   | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
|                           | Put           | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
|                           | Remove        | O(log n)  | O(log n)   | Always O(log n) due to skip list structure. |
| **EnumMap**                | Get           | O(1)      | O(1)       | Very fast, backed by array. |
|                           | ContainsKey   | O(1)      | O(1)       | Very fast, backed by array. |
|                           | Put           | O(1)      | O(1)       | Very fast, backed by array. |
|                           | Remove        | O(1)      | O(1)       | Very fast, backed by array. |
| **WeakHashMap**            | Get           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | ContainsKey   | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Put           | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
|                           | Remove        | O(1)      | O(n)       | Best: No hash collisions. Worst: When hash collisions occur. |
