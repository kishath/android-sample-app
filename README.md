# Architecture

This application is built using Compose, Hilt, Retrofit, Kotlin Coroutines and Flow with the use of Clean Architecture. 

Clean Architecture is a design approach in Android that employs SOLID principles. It separates the application into different layers, each with a single responsibility, in adherence to the Single Responsibility Principle (SRP). The Open/Closed Principle (OCP) is achieved using interfaces and abstractions, allowing for extension without modification. The Liskov Substitution Principle (LSP) is observed by enabling substitution of any component with another that implements the same interface or abstraction. The Interface Segregation Principle (ISP) is achieved by segregating interfaces into smaller, more specific ones, and depending on only the interfaces needed. The Dependency Inversion Principle (DIP) is observed by depending on abstractions instead of concrete implementations, resulting in a more maintainable, testable, and scalable codebase.

## The structure of the application

![package_diagram_latest drawio](https://user-images.githubusercontent.com/3756119/231451876-9c25d392-cdf7-4d2f-8216-12f2226d48bd.png)

- **:app** - The main target of the app which implements the feature modules. 
- **:feature_xy** - A sample feature module which will have it's own DI, UI, Data and Domain sub-directories followed by the relevant UI and unit tests for that specific feature. The contents of these sub-directories are illustrated on the right side of the above image. 
- **:shared-core** - The module which has the core functionality, shared logic and instantiation of common configuration.  
- **:shared-design-system** - The modules which comprises of the common UI components, shared Composables, styles and themes common to the whole app. 

## Why was Clean Architecture used? 

### Performance
The pattern enables developers to create well-structured and optimized code where a clear separation of concerns between different layers across feature modules is strictly maintained. This makes sure that changes made in one place has minimum affect to other locations and the pattern keeps code duplication at a minimum while enabling developers to easily identify and fix performance bottlenecks, without affecting the entire application.

### Readability
Clean architecture also promotes readability by separating code into distinct layers with clear responsibilities. Each layer has a clear and specific purpose, making it easier for developers to understand and navigate through the code. Also, with the addition of feature modules and the consistent structure inside each of these modules, developers can easily understand how the features are built, what they do, what they depend on and how to extend or modify them. 
 
### Maintainability
As the architecture is layered and split into feature modules, it makes it easier to update over time as multiple developers can easily start working on different features without affecting one another. This separation also makes sure to reduces the development time and the risk of introducing bugs or breaking existing functionality. The test coverage in each of these layers and feature modules also makes it easier to manage over time as any breaking change would cause the tests to fail. 

### Testability
Another advantage of using Clean architecture is the easier testability. As the code is split into layers and feature modules with a loose coupled pattern, where the developers can easily test a module as one whole unit without it being dependent on any other module. This gives the assurance that any feature is tested separately for UI and units before being included in the app. Furthermore, this pattern enables easier execution of integration and flow tests once a feature is ready to be included.

### Scalability
As explained in the maintainability and readability aspect of this pattern, the separation of features into feature module and the separation of layers with clear responsibilities enables the developers to add or remove features in the application with ease without affecting existing functionality. This structure also enables teams of multiple sizes work on multiple features at the same time. Over time, if a need arises for code to be shared over multiple features, or UI to be made common, it could be easily made part of the core or the design system respectively. 

### Simplicity
As highlighted in the above points, the separation of concerns, splitting features into modules, clear and consistent structure inside each of the modules, separation of common logic and design elements into core modules, uni-directional data flow, easier extendibility and maintainability makes this pattern simpler to use over time compared to other patterns and also reduce the time and effort required to modify, develop and deploy the application.

## Disadvantages of using this pattern
- With the features modules growing over time, core and design system will have logic and components which will be only common to few modules but not all. 
- Working on a feature will require additional setting up to be done in DI, repositories and data sources before starting work on it which may become repetitive. 
- Navigation across features could become tricky and will require additional logic such as using a navigator.

# How to run the application

> Open local.properties & Set 'base_url="https://fakestoreapi.com/"'

# Screens

<img src="https://user-images.githubusercontent.com/3756119/231513140-009b6443-5bde-4f7d-a271-bbbbcda2b7fc.png" width=300/>
