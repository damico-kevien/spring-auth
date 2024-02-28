# Spring 3.2.3: Auth

### Before start

- Run `docker-compose up`
- Log in MySQL DB 
- Run query in `db/schema.sql`

### Testing Auth

- Run AuthApplication
- Go to `endpoints/auth.http`
- Run **CREATE USER**
- Run **LOGIN**
- Copy _jwt_ and _refreshToken_ values
- Past _jwt_ and _refreshToken_ values inside `endpoints/http-client.env.json`
- Run **TEST** in `endpoints/test.http`

Expected: *Hello! You're authorized!*

### Testing Logout
- Go to `endpoints/auth.http`
- Copy _jwt_ and _refreshToken_ values
- Past _jwt_ and _refreshToken_ values inside body
- Run **LOGOUT**
- Run **TEST** in `endpoints/test.http`

Expected: 401