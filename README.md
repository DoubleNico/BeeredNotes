# BeeredNotes
(WIP) Manage and create notes using Markdown and has login and register support, uses Mysql as database, uses .env

Stuff to put in the .env:
```
SPRING_DATASOURCE_URL=jdbc:mysql://<ip>:<port>/notes?useSSL=false&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

Todo:
- Make markdown support
- Style the website
- Verifiy the fields content of the notes
- Add documentation
