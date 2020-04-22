# TranslatorCatcher

## Tech Stack
- buildSrc
- Dagger 2
- Retrofit
- RxJava
- Mockk
- Chucker

## Demo
* Test [me](demo/app-dev-debug.apk)

## How much time was invested
- Around 8 hours

## How was the time distributed
- Concept: 1 hour
- Domain layer / Use Cases: 5 hours
- UI: 2 hours
- Tests: 1 hour

## Decisions made to to solve certain aspects of the game
- Architecture: Clean architecture inspired from [android10](https://github.com/android10/Android-CleanArchitecture-Kotlin)
- Game logic
- DI: it's small app recommended to use Koin but I used Dagger 2 to have the benefit of gaining time using the annotation processor

## Decisions made because if restricted time
- Dropped the idea to have a good looking design
- Did not persist fetching words to database
- Lack of tests, did only ViewModel unit test
- Did not use multi module project solution
- No Dagger SubComponent :( and No scooping
- No UI testing

## What would be the first thing to improve or add if there had been more time
- Change the challenge code document to improve time and to let developers to go nuts with the challenge
- Cover unit tests of use case, Mappers...
- Persistent fetched words to a DB
- Add Canary leaks to detect possible memory leaks
- Take in count possibility or words overlapping example German words
- Add user profile with a dashboard for high score
- Work with PM/UX to improve the UI/UX
