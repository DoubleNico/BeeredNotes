# BeeredNotes
(WIP) Manage and create notes using Markdown and has login and register support, uses Mysql as database, uses .env

How to use app, it will redirect you to login page because you are not logged in, if you don't have account you can press register and do it, then it will transfer you to /login where you will insert username and passoword, after that it will send you to the app where you will find your notes and you can add, edit, view your notes.

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
