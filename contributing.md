# Contributing to ECommercePrice

Thank you for your interest in contributing to **ECommercePrice**! Your contributions help improve this project and make it better for everyone. Whether you're fixing bugs, adding new features, improving documentation, or helping in any other way, we’re excited to have you on board.

## Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [How to Contribute](#how-to-contribute)
   - [Reporting Bugs](#reporting-bugs)
   - [Suggesting Features](#suggesting-features)
   - [Submitting Changes](#submitting-changes)
3. [Development Process](#development-process)
   - [Setting Up the Environment](#setting-up-the-environment)
   - [Building the Project](#building-the-project)
   - [Running Tests](#running-tests)
4. [Style Guides](#style-guides)
   - [Coding Standards](#coding-standards)
   - [Commit Messages](#commit-messages)
5. [Acknowledgments](#acknowledgments)


## Code of Conduct

By participating in this project, you agree to abide by the [Code of Conduct](CODE_OF_CONDUCT.md). Please read it to understand the expectations we have for all contributors.

## How to Contribute

### Reporting Bugs

If you find a bug in the project, please open an issue on our [GitHub Issues](https://github.com/username/ecommerceprice/issues) page. When reporting a bug, include:

- A clear and descriptive title.
- Steps to reproduce the issue.
- Expected and actual results.
- Any relevant screenshots, logs, or other information.

### Suggesting Features

We welcome feature requests! To suggest a new feature, please open an issue on our [GitHub Issues](https://github.com/username/ecommerceprice/issues) page. Provide a detailed explanation of the feature, its potential benefits, and any examples or mockups that might help illustrate your idea.

### Submitting Changes

1. Fork the repository.
2. Create a new branch from `main` (e.g., `feature/awesome-feature`).
3. Make your changes in the new branch.
4. Ensure your code follows our [Coding Standards](#coding-standards) and includes tests if applicable.
5. Commit your changes with a descriptive commit message.
6. Push your changes to your fork.
7. Open a pull request to the `main` branch of the original repository.

Please ensure that your pull request:

- Describes the change you are making.
- References any related issues.
- Includes relevant tests and documentation updates.

## Development Process

### Setting Up the Environment

To set up your local development environment, ensure you have:

- **Java 21** with **Spring Boot 3.4.2**.
- **Maven** (for dependency management and build tasks).
- **JDK 21**.
- **PostgreSQL** or **H2** for your local database.
- **IDE** (IntelliJ IDEA or Eclipse for Spring Boot).

Clone the repository and run the following command to build the project:

```bash
git clone https://github.com/username/ecommerceprice.git
cd ecommerceprice
./mvnw clean install
```

### Building the Project

To build the project, use:

```bash
./mvnw clean package
```

This will compile the code and package the application for deployment.

### Running Tests

To run the unit tests, use:

```bash
./mvnw test
```

This will execute all unit tests and generate reports for the results.

## Style Guides

### Coding Standards

Adhere to the following coding standards to maintain code quality and readability:

- **Java conventions** as per the [Oracle Java Coding Guidelines](https://www.oracle.com/java/technologies/javase/codeconventions-150003.pdf).
- Use **Spring Framework** best practices for controllers, services, and repositories.
- Write clean, concise, and well-documented code.
- Include comments where necessary to explain complex logic.

### Commit Messages

Write meaningful commit messages to make the history easier to understand:

- Use the present tense ("Add feature" not "Added feature").
- Use the imperative mood ("Fix bug in order processing" not "Fixes bug in order processing").
- Include a brief summary of the changes made.
- Reference issues and pull requests when applicable (e.g., `Fixes #123`).

## Acknowledgments

We appreciate your contributions! If your pull request is merged, you will be listed as a contributor in the project's [Contributors](https://github.com/username/ecommerceprice/graphs/contributors) section.

Thank you for helping make **ECommercePrice** better for everyone!
