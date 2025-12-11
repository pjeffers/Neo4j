
### Analysis


###  Did you make any assumptions that helped you to simplify the code?
- assumed we were prioritising performance over thread safety
so used instance variables for value not found rather than 
performing the Skip Search twice (once during contains and again during insert)
- assumed we were storing only values rather than key/value pairs in order to simplify implementation
so comparisons are performed between values rather than keys
- preferred the use of primitives over Objects to store values as there are greater overheads when using Long rather than long. This avoids autoboxing/autounboxing.
- assumed list should be ordered on value, with lowest values near the head of the list.
- used a head node with value Long.MIN_VALUE to effect an entry point for the search
- assumed duplicate insertions result in no action and do not generate an exception or error
- assumed there was no need to explicitly track the level of a nodes (saves memory)

### What are the upsides and downsides of your implementation?
- Upsides: 
1) I fully implemented the SkipList interface you provided but added two additional methods for delete() and print() as they were useful for debugging. 
2) The code should be performant as it avoids unnecessary searches and repeat calculations.
3) The code keeps the memory usage lower by keeping nodes minimaland avoiding the use of objects where primitives can be used.
- Downsides:
1) The head points to start node below it rather than next, this complicates things a little, as deletion
or insertion of a start value becomes a special case adding to the complexity of the code. It may be simpler to use next instead. 
2) Code is not currently thread safe.
3) My implementation only allows for the insertion of values down to Long.MIN_VALUE+1 

### If you needed to scale your solution (say, to billions of entries, or production-readiness), what decisions would you make differently in your approach?
_For Scalability_
- Minimize object count and size
- Use primitives where possible
- perhaps refactor to store levels in single preallocated arrays.
- Consider parallel processing and making the list thread-safe
- Look into range queries to process segmented data in the list
- Investigate sharding/partitioning
- Consider using ConcurrentSkipListMap

_For production readiness_
- Maintain performance metrics?
- Perform periodic consistency checks
- Implement replication/backup snapshots etc
- Define recovery procedures.
- 


### Do you have any feedback on the exercise in general?

It was difficult!