# split-and-pay
DevDays Fall 2023. Hackathon project.

- [split-and-pay](#split-and-pay)
    * [Backend](#backend)
        + [Installation](#installation)
        + [Api](#api)

## Backend

### Installation
1. Clone repository
```bash 
git clone https://github.com/timatifey/split-and-pay.git
cd split-and-pay/backend
```
2. Build the docker image 
```bash
docker build -t splitandpay/backend:latest .
```
3. Create config (for example)
```yml
server:
  port: ${port:8010}
  shutdown: graceful

spring:
  application:
    name: split-and-pay-backend-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/split-and-pay
      auto-index-creation: true

logging:
  file:
    name: ${log.dir:/tmp}/split-and-pay-backend-service.main.log

```
4. Create .env file (for example)
```
CONFIG_DIR=/etc/split-and-pay/backend/
```
5. Run docker-compose
```bash
docker-compose -f docker-compose.yml up -d
```

### Api

You can see all api methods on [this wiki](https://github.com/timatifey/split-and-pay/wiki/endpoints) page
