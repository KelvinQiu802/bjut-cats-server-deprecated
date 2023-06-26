# BJUT Cats Server

## APIs

| HTTP Verbs | Endpoints             | Action                         | Response Format |
| ---------- | --------------------- | ------------------------------ | --------------- |
| GET        | /api/imageUploadToken | To get an qiniu upload token   | JSON            |
| GET        | /api/cats             | To get all cats                | JSON            |
| POST       | /api/cats             | Create a cat                   | JSON            |
| DELETE     | /api/cats             | Remove all cats from the table | JSON            |