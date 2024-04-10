# Workout Tracking Application
---
## Password Hashing:
- [SHA-256 and SHA3-256 Hashing in Java](https://www.baeldung.com/sha-256-hashing-java)
```
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>33.1.0-jre</version>
</dependency>
```
```
String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
```
---
## Sources for inspiration and information:
- [Ying-Lai FitJournal](https://github.com/Ying-Lai/FitJournal)
- [Workout tracker code review](https://codereview.stackexchange.com/questions/79196/exercise-tracker)
- [Model-view-presenter](https://en.wikipedia.org/wiki/Model–view–presenter)
