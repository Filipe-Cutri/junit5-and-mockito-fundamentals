# JUnit 5 Course / Curso JUnit 5

[English](#english) | [Português (Brasil)](#português-brasil)

---

## English

### 📚 About

This repository contains practical examples and exercises for learning **JUnit 5** (Jupiter), the latest version of the most popular testing framework for Java. The project demonstrates unit testing best practices, including the use of Mockito for mocking dependencies.

### 🎯 Topics Covered

- **Basic JUnit 5 Tests**
  - Assertions (`assertEquals`, `assertTrue`, `assertNotNull`, etc.)
  - Lifecycle methods (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`)
  - Exception testing
  
- **Parameterized Tests**
  - `@ValueSource`
  - `@CsvSource`
  
- **Mocking with Mockito**
  - `@Mock` and `@InjectMocks` annotations
  - Behavior verification with `verify()`
  - Stubbing methods with `when().thenReturn()`
  
- **Design Patterns for Testing**
  - Builder Pattern for test objects
  - Repository Pattern
  - Service Layer testing
  
- **Domain-Driven Design (DDD)**
  - Domain entities (Usuario, Conta, Transacao)
  - Custom validation exceptions
  - Service layer with business logic

### 🛠️ Technologies

- **Java 11**
- **JUnit 5.10.2** (Jupiter)
- **Mockito 5.3.1**
- **Maven** (build tool)

### 📋 Prerequisites

- JDK 11 or higher
- Maven 3.6+
- IDE of your choice (IntelliJ IDEA, Eclipse, VS Code)

### 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Filipe-Cutri/Curso_JUnit5.git
   cd Curso_JUnit5
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run all tests:**
   ```bash
   mvn test
   ```

4. **Run specific test class:**
   ```bash
   mvn test -Dtest=CalculadoraTest
   ```

### 📁 Project Structure

```
src/
├── main/java/
│   ├── Calculadora.java                    # Simple calculator example
│   └── cursoJUnit5/Examples/
│       ├── domain/                         # Domain entities
│       │   ├── Conta.java
│       │   ├── Transacao.java
│       │   ├── Usuario.java
│       │   └── exceptions/
│       │       └── ValidationException.java
│       ├── infra/                          # Infrastructure layer
│       │   └── UsuarioMemoryRepository.java
│       └── service/                        # Service layer
│           ├── ContaService.java
│           ├── TransacaoService.java
│           ├── UsuarioService.java
│           ├── external/
│           │   ├── ClockService.java
│           │   └── ContaEvent.java
│           └── repositories/
│               ├── ContaRepository.java
│               ├── TransacaoDao.java
│               └── UsuarioRepository.java
└── test/java/
    ├── CalculadoraTest.java                # Basic JUnit 5 tests
    ├── CalculadoraMockTest.java            # Mockito examples
    └── cursoJUnit5/Examples/
        ├── domain/
        │   ├── ContaTest.java
        │   ├── UsuarioTest.java
        │   └── builders/                   # Builder pattern for tests
        │       ├── BuilderMaster.java
        │       ├── ContaBuilder.java
        │       ├── TransacaoBuilder.java
        │       └── UsuarioBuilder.java
        ├── infra/
        │   └── UserServiceComUserMemoryRepositoryTest.java
        └── service/
            ├── ContaServiceTest.java
            ├── TransacaoServiceTest.java
            └── UsuarioServiceTest.java
```

### 📝 Key Examples

#### Basic Assertions
```java
@Test
public void testSomar() {
    Assertions.assertEquals(5, calc.soma(2, 3));
    Assertions.assertTrue(calc.soma(2, 3) == 5);
}
```

#### Parameterized Tests
```java
@ParameterizedTest
@CsvSource(value = {
    "6, 2, 3",
    "6, -2, -3",
    "10, 3, 3.3333332538604736"
})
public void deveDividirCorretamente(int num, int den, double res) {
    float resultado = calc.dividir(num, den);
    Assertions.assertEquals(res, resultado);
}
```

#### Mocking with Mockito
```java
@Test
public void deveRetornarUsuarioPorEmail() {
    when(repository.getUserByEmail("mail@mail.com"))
        .thenReturn(Optional.of(umUsuario().agora()));
    
    Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
    Assertions.assertTrue(user.isPresent());
    
    verify(repository).getUserByEmail("mail@mail.com");
}
```

### 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests with improvements or new examples.

### 📄 License

This project is for educational purposes.

---

## Português (Brasil)

### 📚 Sobre

Este repositório contém exemplos práticos e exercícios para aprender **JUnit 5** (Jupiter), a versão mais recente do framework de testes mais popular para Java. O projeto demonstra boas práticas de testes unitários, incluindo o uso do Mockito para simular dependências.

### 🎯 Tópicos Abordados

- **Testes Básicos com JUnit 5**
  - Assertivas (`assertEquals`, `assertTrue`, `assertNotNull`, etc.)
  - Métodos de ciclo de vida (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`)
  - Testes de exceções
  
- **Testes Parametrizados**
  - `@ValueSource`
  - `@CsvSource`
  
- **Mocks com Mockito**
  - Anotações `@Mock` e `@InjectMocks`
  - Verificação de comportamento com `verify()`
  - Stubbing de métodos com `when().thenReturn()`
  
- **Padrões de Projeto para Testes**
  - Padrão Builder para objetos de teste
  - Padrão Repository
  - Testes da camada de serviço
  
- **Domain-Driven Design (DDD)**
  - Entidades de domínio (Usuario, Conta, Transacao)
  - Exceções customizadas de validação
  - Camada de serviço com lógica de negócio

### 🛠️ Tecnologias

- **Java 11**
- **JUnit 5.10.2** (Jupiter)
- **Mockito 5.3.1**
- **Maven** (ferramenta de build)

### 📋 Pré-requisitos

- JDK 11 ou superior
- Maven 3.6+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### 🚀 Como Começar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Filipe-Cutri/Curso_JUnit5.git
   cd Curso_JUnit5
   ```

2. **Compile o projeto:**
   ```bash
   mvn clean install
   ```

3. **Execute todos os testes:**
   ```bash
   mvn test
   ```

4. **Execute uma classe de teste específica:**
   ```bash
   mvn test -Dtest=CalculadoraTest
   ```

### 📁 Estrutura do Projeto

```
src/
├── main/java/
│   ├── Calculadora.java                    # Exemplo simples de calculadora
│   └── cursoJUnit5/Examples/
│       ├── domain/                         # Entidades de domínio
│       │   ├── Conta.java
│       │   ├── Transacao.java
│       │   ├── Usuario.java
│       │   └── exceptions/
│       │       └── ValidationException.java
│       ├── infra/                          # Camada de infraestrutura
│       │   └── UsuarioMemoryRepository.java
│       └── service/                        # Camada de serviço
│           ├── ContaService.java
│           ├── TransacaoService.java
│           ├── UsuarioService.java
│           ├── external/
│           │   ├── ClockService.java
│           │   └── ContaEvent.java
│           └── repositories/
│               ├── ContaRepository.java
│               ├── TransacaoDao.java
│               └── UsuarioRepository.java
└── test/java/
    ├── CalculadoraTest.java                # Testes básicos JUnit 5
    ├── CalculadoraMockTest.java            # Exemplos com Mockito
    └── cursoJUnit5/Examples/
        ├── domain/
        │   ├── ContaTest.java
        │   ├── UsuarioTest.java
        │   └── builders/                   # Padrão Builder para testes
        │       ├── BuilderMaster.java
        │       ├── ContaBuilder.java
        │       ├── TransacaoBuilder.java
        │       └── UsuarioBuilder.java
        ├── infra/
        │   └── UserServiceComUserMemoryRepositoryTest.java
        └── service/
            ├── ContaServiceTest.java
            ├── TransacaoServiceTest.java
            └── UsuarioServiceTest.java
```

### 📝 Exemplos Principais

#### Assertivas Básicas
```java
@Test
public void testSomar() {
    Assertions.assertEquals(5, calc.soma(2, 3));
    Assertions.assertTrue(calc.soma(2, 3) == 5);
}
```

#### Testes Parametrizados
```java
@ParameterizedTest
@CsvSource(value = {
    "6, 2, 3",
    "6, -2, -3",
    "10, 3, 3.3333332538604736"
})
public void deveDividirCorretamente(int num, int den, double res) {
    float resultado = calc.dividir(num, den);
    Assertions.assertEquals(res, resultado);
}
```

#### Mocks com Mockito
```java
@Test
public void deveRetornarUsuarioPorEmail() {
    when(repository.getUserByEmail("mail@mail.com"))
        .thenReturn(Optional.of(umUsuario().agora()));
    
    Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
    Assertions.assertTrue(user.isPresent());
    
    verify(repository).getUserByEmail("mail@mail.com");
}
```

### 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests com melhorias ou novos exemplos.

### 📄 Licença

Este projeto é para fins educacionais.
