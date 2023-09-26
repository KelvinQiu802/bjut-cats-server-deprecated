# BJUT Cats Server

## APIs

| HTTP Verbs | Endpoints                          | Action                         | Response Format |
| ---------- | ---------------------------------- | ------------------------------ | --------------- |
| GET        | /api/imageUploadToken              | To get a qiniu upload token    | JSON            |
| GET        | /api/jscode2session?js_code={CODE} | Get user openId                | JSON            |
| GET        | /api/cats                          | To get all cats                | JSON            |
| POST       | /api/cats                          | Create a cat                   | JSON            |
| DELETE     | /api/cats                          | Remove all cats from the table | JSON            |
| GET        | /api/users/{openId}                | Get user by openId             | JSON            |
| POST       | /api/users                         | Create an user                 | JSON            |
| POST       | /api/images                        | Add an image                   | JSON            |
| GET        | /api/images/{state}                | Get images by state            | JSON            |
| POST       | /api/likes                         | Add an image like              | JSON            |
| GET        | /api/likes?by={openId OR imageUrl} | Get image likes                | JSON            |
| GET        | /api/articles                      | Get all articles               | JSON            |

## Deployment

```shell
$ lsof -i :7070
$ kill xxx
$ nohup java -jar xxx.jar &
```

