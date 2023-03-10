# π§Ί μ₯λ°κ΅¬λ 1λ¨κ³ - νμ κΈ°λ₯

## μ΄λ©μΌ μ€λ³΅ μ²΄ν¬

### HTTP Request

```jsx
GET /api/members/email-check?email=email@email.com HTTP/1.1
```

### HTTP Response - μ΄λ©μΌ μ€λ³΅ μ²΄ν¬ μ±κ³΅ μ

```jsx
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

{
  unique: "true"
}
```

### HTTP Response - μ΄λ©μΌ μ€λ³΅ μ²΄ν¬ μ€ν¨ μ

```jsx
HTTP/1.1 400 Bad Request

{
  message: ~~
}
```

- μ΄λ©μΌ νμμ΄ μλͺ»λμμ κ²½μ°

## νμ κ°μ

### HTTP Request

```jsx
POST /api/members HTTP/1.1
Content-Type: application/json

{
  "email" : "email@email.com",
  "nickname" : "λλ€μ",
  "password" : "password123!"
}
```

### HTTP Response - νμ κ°μ μ±κ³΅ μ

```jsx
HTTP/1.1 201 Created
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```

### HTTP Response - νμ κ°μ μ€ν¨ μ

```jsx
HTTP/1.1 400 Bad Request
```

- λλ½λ ν­λͺ©μ΄ μ‘΄μ¬ν  κ²½μ°
- μ€λ³΅λλ μ΄λ©μΌμ΄ μ‘΄μ¬ν  κ²½μ°
- μ΄λ©μΌ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    ```

- λλ€μ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    νκΈλ‘λ§ μ΅μ 1μ μ΅λ 5μ
    "^[γ±-γκ°-ν£]{1,5}$"
    ```

- λΉλ°λ²νΈ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    μ΅μ 8 μ, μ΅λ 20 μ μ΅μ νλμ λ¬Έμ, νλμ μ«μ λ° νλμ νΉμ λ¬Έμ
    "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$"
    ```
  
## λ‘κ·ΈμΈ

### HTTP Request

```jsx
POST /api/login HTTP/1.1
Content-Type: application/json

{
  "email" : "email@email.com",
  "password" : "password123!"
}
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

{
	"nickname" : "λλ€μ",
  "token" : "accessToken"
}
```

### HTTP Response - μ€ν¨ μ

```jsx
HTTP/1.1 400 Bad Request
```

- μλͺ»λ μ΄λ©μΌ νΉμ λΉλ°λ²νΈλ₯Ό μλ ₯ νμμ κ²½μ°
- μ΄λ©μΌ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    ```

- λλ€μ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    νκΈλ‘λ§ μ΅μ 1μ μ΅λ 5μ
    "^[γ±-γκ°-ν£]{1,5}$"
    ```

- λΉλ°λ²νΈ νμμ΄ μλͺ»λμμ κ²½μ°

    ```java
    μ΅μ 8 μ, μ΅λ 20 μ μ΅μ νλμ λ¬Έμ, νλμ μ«μ λ° νλμ νΉμ λ¬Έμ
    "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$"
    ```
  
## λΉλ°λ²νΈ νμΈ

### HTTP Request

```jsx
POST /api/members/password-check HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken

{
  "password" : "password123!"
}
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

{
  "success": "true"
}
```

## νμ μ λ³΄ μ‘°ν

### HTTP Request

```jsx
GET /api/members/me HTTP/1.1
Authorization: Bearer accessToken
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

{
  "email" : "email@email.com",
  "nickname" : "λλ€μ"
}
```

## νμ μ λ³΄ μμ 

### HTTP Request

```jsx
PATCH /api/members/me HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken

{
  "nickname" : "λ€λ₯Έλλ€μ"
}
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 204 No Content
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```

## λΉλ°λ²νΈ μμ 

### HTTP Request

```jsx
PATCH /api/members/password HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken

{
  "password" : "otherpassword123!"
}
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 204 No Content
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```

## νμ νν΄

### HTTP Request

```jsx
DELETE /api/members/me HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 204 No Content
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```

## κΈ°ν

### μΈμ¦ μλ¬

```jsx
HTTP/1.1 401 Unauthorized
```

- λ‘κ·ΈμΈμ΄ μλμ΄ μμ κ²½μ°
- ν ν° μκ°μ΄ λ§λ£ λμμ κ²½μ°
- μ­μ λ νμμ ν ν°μΌλ‘ μ κ·Όμ μλν  κ²½μ°

### κΈ°ν μλ¬

```jsx
HTTP/1.1 500 Internal Server Error
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

{
  "message" : "...",
}
```

# π§Ί μ₯λ°κ΅¬λ 2λ¨κ³ - μ₯λ°κ΅¬λ/μ£Όλ¬Έ API λ³κ²½νκΈ°

## μν λ¦¬μ€νΈ μ‘°ν

### HTTP Request

```jsx
GET /api/products?page={page}&limit={limit} HTTP/1.1
```

- page = νμ¬ νμ΄μ§ λ²νΈ
- limit = ν νμ΄μ§μ νμν  μνμ κ°μ

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 Ok
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
x-total-count: {μν κ°μ}

[	{
	"id" : 1,
	"name" : "κ·Έλ¦΄",
	"price" : 100,
	"imageUrl" : "https~~",
	"stock" : 1,
}, {
	"id" : 2,
	"name" : "μμ λ±",
	"price" : 200,
	"imageUrl" : "https~~",
	"stock" : 5,
} ]
```

## μ₯λ°κ΅¬λ μν μΆκ°

### HTTP Request

```jsx
POST /api/carts/products HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken

{
  "id" : 1,
  "quantity" : 1
}
```

### HTTP Response - μ₯λ°κ΅¬λ μν μΆκ° μ±κ³΅ μ

```jsx
HTTP/1.1 200 Ok
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

[	{
	"product" : {
		"id" : 1,
		"name" : "κ·Έλ¦΄",
		"price" : 100,
		"imageUrl" : "https~~",
		"stock" : 1,
	},
	"quantity" : 1
}, {
	"product" : {
		"id" : 2,
		"name" : "μμ λ±",
		"price" : 200,
		"imageUrl" : "https~~",
		"stock" : 5,
	},
	"quantity" : 1
} ]
```

### HTTP Response - μ₯λ°κ΅¬λ μν μΆκ° μ€ν¨ μ

```jsx
HTTP/1.1 400 Bad Request
```

- μνμ΄ νμ  λμμ κ²½μ°
- μνμ΄ μ‘΄μ¬νμ§ μμ κ²½μ°(μν idκ° μ‘΄μ¬νμ§ μλ κ²½μ°)

## μ₯λ°κ΅¬λ μ‘°ν

### HTTP Request

```jsx
GET /api/carts HTTP/1.1
Authorization: Bearer accessToken
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 Ok
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

[	{
	"product" : {
		"id" : 1,
		"name" : "κ·Έλ¦΄",
		"price" : 100,
		"imageUrl" : "https~~",
		"stock" : 1,
	},
	"quantity" : 1
}, {
	"product" : {
		"id" : 2,
		"name" : "μμ λ±",
		"price" : 200,
		"imageUrl" : "https~~",
		"stock" : 5,
	},
	"quantity" : 1
} ]
```

## μ₯λ°κ΅¬λ μλ λ³κ²½

### HTTP Request

```jsx
PATCH /api/carts/products HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken

{
  "id" : 1,
	"quantity" : 1,
}
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 Ok
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

[	{
	"product" : {
		"id" : 1,
		"name" : "κ·Έλ¦΄",
		"price" : 100,
		"imageUrl" : "https~~",
		"stock" : 1,
	},
	"quantity" : 1
}, {
	"product" : {
		"id" : 2,
		"name" : "μμ λ±",
		"price" : 200,
		"imageUrl" : "https~~",
		"stock" : 5,
	},
	"quantity" : 1
} ]
```

## μ₯λ°κ΅¬λ μν μ κ±°

### HTTP Request

```jsx
DELETE /api/carts/products?id=1 HTTP/1.1
Content-Type: application/json
Authorization: Bearer accessToken
```

### HTTP Response - μ±κ³΅ μ

```jsx
HTTP/1.1 200 Ok
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json

[	{
	"product" : {
		"id" : 1,
		"name" : "κ·Έλ¦΄",
		"price" : 100,
		"imageUrl" : "https~~",
		"stock" : 1,
	},
	"quantity" : 1
}, {
	"product" : {
		"id" : 2,
		"name" : "μμ λ±",
		"price" : 200,
		"imageUrl" : "https~~",
		"stock" : 5,
	},
	"quantity" : 1
} ]
```
