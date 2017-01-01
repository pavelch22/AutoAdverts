Tags: Java, JDBC, MySQL, SQL, Servlet, Tomcat, JSP, JSTL, JavaScript, jQuery, AJAX, JSON,
HTML, CSS, Log4j, Maven


It's a web app that supports:
- user login/registration;
- user roles (user, moderator, admin);
- user profile:
    - user (editing contact information, change password, own adverts management);
    - moderator (.. + adverts moderation);
    - admin (.. + grand/revoke moderator permissions, ban(delete) users, autocatalog editing);
- auto adverts creation;
- showing moderated adverts (sort, search etc.) and detail view;
- adverts moderation (each advert will be moderated before publication);
- autocatalog (with CRUD operations).


Installation:
1) Configure database (follow the instructions in the resources/db/autoadverts_db_setup_mysql.txt);
2) Install Tomcat (https://tomcat.apache.org/download-90.cgi) and deploy autoadverts.war.