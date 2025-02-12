# SWE-Spring-2025-Project
## Project Overview
This project is part of a semester-long assignment to build a compute engine that takes an integer as input, processes it according to a defined computation, and outputs a result. The project consists of three key APIs:

1. **Network API** – Defines interaction between the user and compute engine.
2. **Process API** – Handles reading and writing data to storage.
3. **Conceptual API** – Manages internal compute engine operations.

## Computation: Digital Root Persistence
The compute engine will determine the **Digital Root Persistence** of a given input integer. This computation involves repeatedly summing the digits of a number until a single-digit result is obtained, and counting the number of steps required. This is CPU-intensive because large numbers require multiple iterations of digit summation.

### **Example**
| Input  | Output (Persistence) |
|--------|---------------------|
| 9875   | 3                   |
| 39     | 5                |
| 9999   | 7                 |
| 123456 | 4                  |


### **Explanation**
- The user provides a **positive integer**.
- The compute engine repeatedly sums its digits until a **single-digit result** is obtained.
- The number of steps taken is the **Digital Root Persistence**.
- Example: If the input is **9875**, the steps are:
  - **9875** → 9+8+7+5 = **29**
  - **29** → 2+9 = **11**
  - **11** → 1+1 = **2** (Final single-digit result)
  - **Persistence = 3**

## System Architecture
The following diagram illustrates the high-level architecture of the system:

![System Diagram (COMING SOON!)


