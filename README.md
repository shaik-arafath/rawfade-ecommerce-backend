# RawFade E-commerce Backend (Spring Boot + MySQL + JWT)

Endpoints:
- `POST /api/auth/signup` {email,password,fullName}
- `POST /api/auth/login` {email,password} -> returns JWT
- `GET /api/products` list products
- `GET /api/products/{slug}` single product
- `GET /api/banners` list home banners
- `GET /api/texts/{key}` get site text by key (e.g., HOME_HERO_TITLE)
- `POST /api/cart/add?productId=1&qty=1` (Authorization: Bearer <token>)
- Admin (Authorization: Bearer <token> w/ ROLE_ADMIN)
  - `GET/POST/PUT/DELETE /api/admin/products`
  - `GET/POST /api/admin/categories`
  - `GET/POST/PUT/DELETE /api/admin/banners`
  - `GET/POST/PUT/DELETE /api/admin/texts`

## Setup

1. Create MySQL DB & user:
```sql
CREATE DATABASE rawfade CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'rawfade_user'@'%' IDENTIFIED BY 'CHANGE_ME';
GRANT ALL PRIVILEGES ON rawfade.* TO 'rawfade_user'@'%';
FLUSH PRIVILEGES;
```

2. Edit `src/main/resources/application.properties` for DB creds and set a long random `security.jwt.secret`.

3. Build & run:
```bash
mvn -v # needs Java 17+
mvn clean package
java -jar target/rawfade-ecommerce-backend-0.0.1-SNAPSHOT.jar
```

Default admin (auto-created):
- Email: `admin@rawfadeclothing.com`
- Password: `Admin@123` (change it after login!)

## Deploy on Hostinger VPS (Ubuntu)

```bash
sudo apt update && sudo apt install -y openjdk-17-jdk mysql-server nginx
# copy JAR to /opt/rawfade/app.jar
sudo useradd -r -s /bin/false rawfade
sudo mkdir -p /opt/rawfade && sudo chown -R rawfade: /opt/rawfade
sudo cp target/rawfade-ecommerce-backend-0.0.1-SNAPSHOT.jar /opt/rawfade/app.jar

# systemd service
cat | sudo tee /etc/systemd/system/rawfade.service <<'EOF'
[Unit]
Description=RawFade Spring Boot app
After=network.target

[Service]
User=rawfade
WorkingDirectory=/opt/rawfade
ExecStart=/usr/bin/java -jar /opt/rawfade/app.jar
SuccessExitStatus=143
Restart=always
Environment=JAVA_OPTS=
[Install]
WantedBy=multi-user.target
EOF

sudo systemctl daemon-reload
sudo systemctl enable --now rawfade
sudo systemctl status rawfade
```

Nginx reverse proxy (domain `rawfadeclothing.com`):
```nginx
server {
    listen 80;
    server_name rawfadeclothing.com www.rawfadeclothing.com;
    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Then add HTTPS (Certbot) and harden as needed.
