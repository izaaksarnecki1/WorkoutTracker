# Workout Tracking Application

## Description
This is a simple workout application developed for the INF101 course at UiB.

The application is built using Java Swing and AWT frameworks and utilizes a sqlite3 database to store user information, workout details, and exercises. The database integration is achieved through the [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) library.

The user interface and functionality are optimized for macOS, ensuring a better user experience on that platform.

Upon launching the app, users are presented with two options: login and signup. Depending on their choice, they need to provide a username and password, which are securely hashed and stored in the database. After logging in or signing up, users can edit their profile with personal information, add workouts, and review their previous workouts.

Adding workouts involves naming the workout and specifying a date, followed by adding exercises. Each exercise includes a name, number of sets, reps, and weight. Users can easily rename workouts by pressing the save button.

For reviewing previous workouts, users can navigate through their workouts displayed in chronological order, with the newest workouts appearing first. Within this menu, users can seamlessly switch between their workouts.

I have created a short youtube video of how the app is used: [Workout Tracker App in Java by Izaak Sarnecki]()