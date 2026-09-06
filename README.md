# TradeVault - Stock Trading Platform

## Overview

TradeVault is a console-based Java application that simulates a basic stock trading environment.

The system allows users to view market data, search for stocks, buy and sell shares, manage their portfolio, track transactions, and monitor portfolio performance.

The project was developed as part of the **CodeAlpha Java Programming Internship – Task 3**.

---

## Features

- View stock market data
- Search for stocks using stock symbols
- Buy stocks
- Sell stocks
- Manage user cash balance
- Track owned stocks through a portfolio
- Calculate total invested amount
- Calculate current portfolio value
- Calculate profit or loss
- Calculate portfolio return percentage
- View complete transaction history
- Update simulated market prices
- View account summary
- Store data using File I/O
- Restore saved data when the application starts
- Validate user input

---

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- File I/O
- Java Date & Time API
- Exception Handling
- VS Code
- Git & GitHub

---

## OOP Concepts Used

### Encapsulation

Classes use private fields with public methods to access and modify data safely.

### Classes and Objects

The project uses separate classes to represent different entities:

- `Stock`
- `User`
- `Holding`
- `Transaction`
- `Portfolio`
- `TradingPlatform`

### Abstraction

Trading operations such as buying, selling, portfolio calculation, and data management are organized into separate methods.

### Composition

The `TradingPlatform` works with objects such as `User`, `Portfolio`, `Stock`, and `Transaction` to build the complete trading system.

---

## Project Structure

```text
CodeAlpha_TradeVault
│
├── Main.java
├── Stock.java
├── User.java
├── Holding.java
├── Transaction.java
├── Portfolio.java
├── TradingPlatform.java
├── .gitignore
└── README.md
