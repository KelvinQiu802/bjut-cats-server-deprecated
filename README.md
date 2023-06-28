# BJUT Cats Server

## APIs

| HTTP Verbs | Endpoints                          | Action                         | Response Format |
| ---------- | ---------------------------------- | ------------------------------ | --------------- |
| GET        | /api/imageUploadToken              | To get an qiniu upload token   | JSON            |
| GET        | /api/jscode2session?js_code={CODE} | Get user openId                | JSON            |
| GET        | /api/cats                          | To get all cats                | JSON            |
| POST       | /api/cats                          | Create a cat                   | JSON            |
| DELETE     | /api/cats                          | Remove all cats from the table | JSON            |
| GET        | /api/users/{openId}                | Get user by openId             | JSON            |
| POST       | /api/users                         | Create a user                  | JSON            |
| POST       | /api/images                        | Add a image                    | JSON            |
| GET        | /api/images/{state}                | Get images by state            | JSON            |