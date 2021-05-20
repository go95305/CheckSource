# Check Source 설정 가이드
---



## 🛠️ FrontEnd 환경 세팅

---

### 1. Backend 서버 접근 설정 변경

```
// .\exec\frontend\api\http.js

// 기존 backend 서버 접근에서
const instance = axios.create({
	baseURL: "http://checksource.io:8080",
});

//아래와 같이 변경
const instance = axios.create({
	baseURL: "http://localhost:8080",
});
```

### 2. Vue.js 실행

```
cd .\exec\frontend  //frontend 폴더 접근
npm install         //프로젝트에 요구되는 패키지 설치
npm run serve       //프로젝트 실행
```

## 🛠️ Backend 환경 세팅

---

### 1. Data Base 세팅

- Maria DB 설치
- port : 3306

### 2. Spring Boot 세팅

```
#IDE **세팅**
Workspace .\exec\backend      //workspace 설정
해당 IDE에서 checksource 프로젝트 import
sts에서 실행할 경우, 자체적으로 lombok 설치 후 import 시킬 것
```

```
**#db서버 변경시**
.\exec\backend\checksource\src\main\resources\application.yml
spring:
	datasource:
		url: 연결할 DB url
		(ex. jdbc:mariadb://localhost:3306/checksource?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul)
		username: 설정했을시 수정
		password: 설정했을시 수정

// db를 설치한 서버의 IP:port 적거나 로컬에서 테스트할 경우 localhost
```

### 3. Spring Boot 실행

```
#**Spring boot 서버 실행**
// gradle update
// 실행
// JPA 실행하면 DB 스키마 및 엔터티 자동 세팅
```

### 4. DataBase dump파일 실행

```
**# 데이터베이스 초기 데이터 insert**
exec\database\dump.sql 
// 해당 위치의 DB dump 파일을 실행시켜 insert 
```

## **📚기술스택**

---

### **OS**

- Ubuntu : 20.04

### **Frontend**

- Vue.js : 2.6.11

### **Backend**

- Spring Boot: 2.4.5

### **DB**

- MariaDB: 10.2

### **CI/CD**

- Jenkins : 2.290
- docker : 20.10.6