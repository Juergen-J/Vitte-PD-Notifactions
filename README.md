# Проект Notification Service

## Описание проекта

**Notification Service** - это система для управления уведомлениями, включающая в себя подписку на сообщения, обработку уведомлений и управление пользователями. Проект построен с использованием Spring Framework и предоставляет функциональность для получения сообщений из очередей, обработки этих сообщений и отправки уведомлений пользователям.

## Запуск проекта

### Предварительные требования

- Java 11+
- Maven 3.6+
- Docker и Docker Compose

### Сборка проекта

1. Клонируйте репозиторий:

   ```sh
   git clone git@github.com:Juergen-J/Vitte-PD-Notifactions.git
   ```

2. Перейдите в директорию проекта:

   ```sh
   cd notification-service
   ```

3. Сборка проекта с помощью Maven:

   ```sh
   mvn clean install
   ```

### Запуск инфраструктуры с помощью Docker Compose

Проект включает в себя Docker Compose файл для запуска необходимых сервисов: MySQL и ActiveMQ.

1. Создайте файл `docker-compose.yml` в корне проекта со следующим содержимым:

   ```yaml
   version: '3.1'

   volumes:
     mysql:
       driver: local

   services:
     db:
       image: mysql:8.0.37
       container_name: mysql
       restart: always
       environment:
         MYSQL_DATABASE: mydb
         MYSQL_USER: user
         MYSQL_PASSWORD: password
         MYSQL_ROOT_PASSWORD: root_password
       ports:
         - '3306:3306'
       volumes:
         - mysql:/var/lib/mysql

     adminer:
       image: adminer
       restart: always
       ports:
         - 8091:8080

     activemq:
       image: webcenter/activemq
       container_name: activemq
       ports:
         - "61616:61616"
         - "8161:8161"
       environment:
         - ACTIVEMQ_ADMIN_LOGIN=admin
         - ACTIVEMQ_ADMIN_PASSWORD=admin
       restart: unless-stopped
   ```

2. Запустите Docker Compose:

   ```sh
   docker-compose up -d
   ```

### Запуск проекта

1. Убедитесь, что все необходимые контейнеры запущены (MySQL, ActiveMQ).
2. Запустите Spring Boot приложение:

   ```sh
   mvn spring-boot:run
   ```

   Или запустите скомпилированный JAR файл:

   ```sh
   java -jar target/notification-service-0.0.1-SNAPSHOT.jar
   ```

### Пример конфигурации

Создайте файл `application.yml` в директории `src/main/resources` со следующими настройками:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: user
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  activemq:
    broker-url: tcp://localhost:61616
    user: admin
    password: admin
```

### Пример отправки сообщения

Отправьте сообщение в очередь `new.notification` или `new.user` с помощью утилиты `curl`:

#### Отправка нового уведомления

```sh
curl -X POST -H "Content-Type: application/json" -d '{
  "userId": 1,
  "linkToConversation": "http://example.com/conversation/1"
}' http://localhost:8080/sendNotification
```

#### Отправка нового пользователя

```sh
curl -X POST -H "Content-Type: application/json" -d '{
  "userId": 1,
  "contacts": [
    {
      "notificationType": "EMAIL",
      "contactInfo": "user@example.com",
      "enabled": true
    }
  ]
}' http://localhost:8080/addUser
```

### Логи

При успешном получении и обработке сообщения, в консоли будут отображаться соответствующие логи:

```
Received message from topic: http://example.com/conversation/1
```

## Заключение

**Notification Service** - это надежная и расширяемая система для управления уведомлениями, интегрируемая с различными сервисами и приложениями. Проект легко настраивается и запускается, обеспечивая быструю и эффективную обработку уведомлений.