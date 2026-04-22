# 📅 일정 관리 앱 (Schedule App)

## 📌 프로젝트 소개
Spring Boot를 활용한 일정 관리 백엔드 애플리케이션입니다.
유저 관리, 일정 CRUD, 댓글 기능, Cookie/Session 기반 로그인 인증을 구현했습니다.

---

## 🛠 기술 스택
| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot |
| ORM | Spring Data JPA |
| Database | MySQL |
| Security | BCrypt 암호화, Cookie/Session |
| Build Tool | Gradle |
| Tool | IntelliJ, Postman, Git |

---

## 📁 패키지 구조
```
src/main/java/com/example/schedule1app
├── auth
│   ├── controller
│   ├── dto
│   └── service
├── comment
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── gobal
│   ├── config
│   ├── entity
│   ├── excption
│   └── filter
├── schedule
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
└── user
    ├── controller
    ├── dto
    ├── entity
    ├── repository
    └── service
```

---

## 📊 ERD
![img.png](img.png)

---

## 📋 API 명세서

<details>
<summary>👤 유저 API</summary>

### 회원가입
| 항목 | 내용 |
|------|------|
| URL | `/users` |
| Method | `POST` |
| Status Code | `201 Created` |
| 인증 필요 | ❌ |

**Request Body**
```json
{
    "username": "동엽",
    "email": "dongyeob@email.com",
    "password": "12345678"
}
```

**Response Body (성공)**
```json
{
    "id": 1,
    "username": "동엽",
    "email": "dongyeob@email.com",
    "createdAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `400` | `비밀번호는 8글자 이상이어야 합니다.` | 비밀번호 8글자 미만 |
| `400` | `이메일 형식이 올바르지 않습니다.` | 이메일 형식 오류 |
| `400` | `이름은 필수입니다.` | username 빈값 |
| `400` | `유저명은 4글자 이내여야 합니다.` | username 4글자 초과 |

---

### 유저 전체 조회
| 항목 | 내용 |
|------|------|
| URL | `/users` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ❌ |

**Response Body (성공)**
```json
[
    {
        "id": 1,
        "username": "동엽",
        "email": "dongyeob@email.com",
        "createdAt": "2026-04-19T01:51:10",
        "modifiedAt": "2026-04-19T01:51:10"
    }
]
```

---

### 유저 단건 조회
| 항목 | 내용 |
|------|------|
| URL | `/users/{userId}` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ❌ |

**Response Body (성공)**
```json
{
    "id": 1,
    "username": "동엽",
    "email": "dongyeob@email.com",
    "createdAt": "2026-04-19T01:51:10",
    "modifiedAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 존재하지 않는 유저 |

---

### 유저 수정
| 항목 | 내용 |
|------|------|
| URL | `/users/{userId}` |
| Method | `PATCH` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Request Body**
```json
{
    "username": "동엽수정",
    "email": "dongyeob@email.com",
    "password": "12345678"
}
```

**Response Body (성공)**
```json
{
    "id": 1,
    "username": "동엽수정",
    "email": "dongyeob@email.com",
    "modifiedAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 존재하지 않는 유저 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 유저 삭제
| 항목 | 내용 |
|------|------|
| URL | `/users/{userId}` |
| Method | `DELETE` |
| Status Code | `204 No Content` |
| 인증 필요 | ✅ |

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 존재하지 않는 유저 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

</details>

---

<details>
<summary>🔐 인증 API</summary>

### 로그인
| 항목 | 내용 |
|------|------|
| URL | `/auth/login` |
| Method | `POST` |
| Status Code | `200 OK` |
| 인증 필요 | ❌ |

**Request Body**
```json
{
    "email": "dongyeob@email.com",
    "password": "12345678"
}
```

**Response Body (성공)**
```
200 OK (body 없음, 세션 생성)
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 이메일 없음 |
| `401` | `비밀번호가 일치하지 않습니다.` | 비밀번호 불일치 |

---

### 로그아웃
| 항목 | 내용 |
|------|------|
| URL | `/auth/logout` |
| Method | `POST` |
| Status Code | `200 OK` |
| 인증 필요 | ❌ |

**Response Body (성공)**
```
200 OK (body 없음, 세션 삭제)
```

</details>

---

<details>
<summary>📅 일정 API</summary>

### 일정 생성
| 항목 | 내용 |
|------|------|
| URL | `/schedules` |
| Method | `POST` |
| Status Code | `201 Created` |
| 인증 필요 | ✅ |

**Request Body**
```json
{
    "title": "할일 제목",
    "contents": "할일 내용",
    "userId": 1
}
```

**Response Body (성공)**
```json
{
    "id": 1,
    "title": "할일 제목",
    "contents": "할일 내용",
    "userId": 1,
    "username": "동엽",
    "createdAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 존재하지 않는 유저 |
| `400` | `제목은 필수입니다.` | title 빈값 |
| `400` | `내용은 필수입니다.` | contents 빈값 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 일정 전체 조회
| 항목 | 내용 |
|------|------|
| URL | `/schedules` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Response Body (성공)**
```json
[
    {
        "id": 1,
        "title": "할일 제목",
        "contents": "할일 내용",
        "userId": 1,
        "username": "동엽",
        "commentCount": 1,
        "createdAt": "2026-04-19T01:51:10",
        "modifiedAt": "2026-04-19T01:51:10"
    }
]
```

---

### 일정 단건 조회
| 항목 | 내용 |
|------|------|
| URL | `/schedules/{scheduleId}` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Response Body (성공)**
```json
{
    "id": 1,
    "title": "할일 제목",
    "contents": "할일 내용",
    "userId": 1,
    "username": "동엽",
    "commentCount": 1,
    "createdAt": "2026-04-19T01:51:10",
    "modifiedAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 일정을 찾을 수 없습니다.` | 존재하지 않는 일정 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 일정 수정
| 항목 | 내용 |
|------|------|
| URL | `/schedules/{scheduleId}` |
| Method | `PATCH` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Request Body**
```json
{
    "title": "수정된 제목",
    "contents": "수정된 내용"
}
```

**Response Body (성공)**
```json
{
    "id": 1,
    "title": "수정된 제목",
    "contents": "수정된 내용",
    "modifiedAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 일정을 찾을 수 없습니다.` | 존재하지 않는 일정 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 일정 삭제
| 항목 | 내용 |
|------|------|
| URL | `/schedules/{scheduleId}` |
| Method | `DELETE` |
| Status Code | `204 No Content` |
| 인증 필요 | ✅ |

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 일정을 찾을 수 없습니다.` | 존재하지 않는 일정 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 일정 페이징 조회
| 항목 | 내용 |
|------|------|
| URL | `/schedules/paged?page=0&size=10` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Response Body (성공)**
```json
{
    "content": [
        {
            "title": "할일 제목",
            "contents": "할일 내용",
            "commentCount": 1,
            "createdAt": "2026-04-19T01:51:10",
            "modifiedAt": "2026-04-19T01:51:10",
            "username": "동엽"
        }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "size": 10,
    "number": 0
}
```

</details>

---

<details>
<summary>💬 댓글 API</summary>

### 댓글 생성
| 항목 | 내용 |
|------|------|
| URL | `/comments` |
| Method | `POST` |
| Status Code | `201 Created` |
| 인증 필요 | ✅ |

**Request Body**
```json
{
    "contents": "댓글 내용",
    "scheduleId": 1
}
```

**Response Body (성공)**
```json
{
    "id": 1,
    "contents": "댓글 내용",
    "userId": 1,
    "scheduleId": 1,
    "createdAt": "2026-04-19T01:51:10",
    "modifiedAt": "2026-04-19T01:51:10"
}
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `404` | `해당 유저를 찾을 수 없습니다.` | 존재하지 않는 유저 |
| `404` | `해당 일정을 찾을 수 없습니다.` | 존재하지 않는 일정 |
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

---

### 댓글 전체 조회
| 항목 | 내용 |
|------|------|
| URL | `/comments?scheduleId=1` |
| Method | `GET` |
| Status Code | `200 OK` |
| 인증 필요 | ✅ |

**Response Body (성공)**
```json
[
    {
        "id": 1,
        "contents": "댓글 내용",
        "userId": 1,
        "scheduleId": 1,
        "createdAt": "2026-04-19T01:51:10",
        "modifiedAt": "2026-04-19T01:51:10"
    }
]
```

**Response Body (실패)**
| 상태코드 | 메시지 | 설명 |
|---------|--------|------|
| `401` | `로그인이 필요합니다.` | 미로그인 상태 |

</details>