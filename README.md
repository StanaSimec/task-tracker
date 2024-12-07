# CLI Task Tracker

### Solution for the [task-tracker](https://roadmap.sh/projects/task-tracker) project from [roadmap.sh](https://roadmap.sh).

## Setup
```
javac TaskTracker.java
```

## How to use

### Add a new task
```
java TaskTracker add "Make coffee"
```

### Update a task
```
java TaskTracker update 1 "Make dinner"
```

### Delete a task
```
java TaskTracker delete 1
```

### Mark a task as in progress
```
java TaskTracker mark-in-progress 1
```

### Mark a task as done
```
java TaskTracker mark-done 1
```

### List all tasks
```
java TaskTracker list
```

### List tasks by status
```
java TaskTracker list todo
java TaskTracker list in-progress
java TaskTracker list done
```
