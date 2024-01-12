# Projekt systemu rekrutacji na Politechnice Wrocławskiej

## Stworzenie bazy danych
1. Uruchomić np. `MySQL Command Line Client`
2. Stworzyć bazę danych i użytkownika:
    > mysql> create database *recrutation_system_db*;
3. Przed pierwszym uruchomieniem edytować plik `src/main/resources/application.properties`:
    > spring.jpa.hibernate.ddl-auto=update  
      spring.datasource.url=jdbc:mysql://localhost:*PORT*/recrutation_system_db  
      spring.datasource.username=*NAZWA*  
      spring.datasource.password=*HASŁO*  
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
      #spring.jpa.show-sql: true
      spring.jpa.defer-datasource-initialization=true
      spring.sql.init.mode=always

4. Po pierwszym uruchomieniu edytować poniższy plik:
   > spring.jpa.hibernate.ddl-auto=none (lub zostawić na update)  
   spring.datasource.url=jdbc:mysql://localhost:*PORT*/recrutation_system_db  
   spring.datasource.username=*NAZWA*  
   spring.datasource.password=*HASŁO*  
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
   #spring.jpa.show-sql: true
   spring.jpa.defer-datasource-initialization=false
   spring.sql.init.mode=never

TODO:  
- [ ] ...  
- [ ] ...  
- [x] ...  

