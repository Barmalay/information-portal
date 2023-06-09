# information_portal
Информационый портал с функцией контроля успеваемости студентов. 

Реализованы 3 роли (студент, преподаватель и деканат). Преподаватель может создавать ведомости по тем предметам и группам, у которых ведет и выставлять оценки.
Деканат является администратором и может для любой группы и предмета создавать ведомости и сохранять их от своего лица.
Студенты могут просматривать свою успеваемость и скачивать файл в формате Excel табеля успеваемости.
Также есть аутентификация и авторизация пользователей.
Карта информационного портала (оранжевым выделено куда могут заходить преподаватели и деканат, зеленым только студенты):

![структура инф-ого портала(ver-1)](https://github.com/Barmalay/information-portal/assets/118481958/aba539f4-3aa4-42db-9f30-2c07cc131ddf)

Technologies stack:

1. Java 17
2. Spring Boot
3. Spring MVC
4. Spring Security
5. Spring Data JPA
6. Hibernate
