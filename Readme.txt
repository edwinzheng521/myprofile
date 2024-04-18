Group Members Contributions:
Kat Ragle: profile and home page, likes, following, setup for database_setup.sql
Edwin Zheng: post creation, comments, setup for database_setup.sql, dml.sql
Aysha Amreen: bookmarks, hashtags, populating database_setup.sql, readme.txt, video

Instructions on how to run the project:
1. Run the database_setup.sql file in mysql-server to create the database and all 
    the associated tables, and to populate with initial meaningful data.
2. Navigate to the directory with the pom.xml using the terminal
3. Use following command in the terminal:
    mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=8081'
4. Open the browser and navigate to the following URL: http://localhost:8081/
5. Create an account and login.

External resources to learn concepts:
Learning the cast() function:
https://navicat.com/en/company/aboutus/blog/1754-data-type-conversion-in-mysql-8#:~:text=The%20CAST()%20Function,%2C%20SIGNED%2C%20UNSIGNED%20data%20types.