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
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/split-and-pay
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
